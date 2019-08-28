package com.shangyong.thjdq.service.impl;

import com.shangyong.common.entity.RestResult;
import com.shangyong.common.utils.LocalDateUtil;
import com.shangyong.rest.feign.*;
import com.shangyong.thcore.vo.*;
import com.shangyong.thjdq.config.properties.JdqCommonProperties;
import com.shangyong.thjdq.dto.InvokeJdqRequestDto;
import com.shangyong.thjdq.enums.JdqPushEnum;
import com.shangyong.thjdq.service.JdqOrderPushService;
import com.shangyong.thjdq.service.JdqSignService;
import com.shangyong.thjdq.vo.BankCardInfoVo;
import com.shangyong.thjdq.vo.FeedbackOrderStatusVo;
import com.shangyong.thjdq.vo.RepaymentPlanVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ybds on 2019-03-24.
 */
@Service
public class JdqOrderPushServiceImpl implements JdqOrderPushService {

    private static final Logger log = LoggerFactory.getLogger(JdqOrderPushServiceImpl.class);

    @Autowired
    private BaseCloudHystrixService baseCloudHystrixService;
    @Autowired
    private JdqCommonProperties jdqCommonProperties;
    @Autowired
    private OrderBankCloudHystrixService orderBankCloudHystrixService;
    @Autowired
    private OrderBorrowCloudHystrixService orderBorrowCloudHystrixService;
    @Autowired
    private OrderRepaymentCloudHystrixService orderRepaymentCloudHystrixService;
    @Autowired
    private JdqSignService jdqSignService;
    @Autowired
    private OrderCloudHystrixService orderCloudHystrixService;

    @Override
    public boolean pushOrderStateByStatus(String jdqOrderId) {

        try {

            // 查询订单
            RestResult<OrderLoanVo> orderLoanVoRestResult = orderCloudHystrixService.orderSearch(jdqCommonProperties.getRiskAppId(), jdqOrderId);
            log.info("查询订单结果:{}",orderLoanVoRestResult);
            if (orderLoanVoRestResult == null || !orderLoanVoRestResult.isSuccess() || orderLoanVoRestResult.getData() == null || orderLoanVoRestResult.getData().getBody() == null) {
                log.error("订单不存在 {}", jdqOrderId);
                return true;
            }

            OrderLoanVo orderLoanVo = orderLoanVoRestResult.getData().getBody();
            int status = orderLoanVo.getStatus();
            int repaymentStatus = orderLoanVo.getRepaymentStatus();
            String localOrderId = orderLoanVo.getOrderId();

            // 获取借点钱订单信息
            FeedbackOrderStatusVo feedbackOrderStatusVo = findFeedbackOrderStatusVoByStatus(status, repaymentStatus, jdqOrderId, localOrderId);
            if (feedbackOrderStatusVo == null) {
                log.error("构建借点钱订单推送对象失败");
                return false;
            }
            log.info("构建借点钱订单推送对象 jdqOrderId:{} {}", feedbackOrderStatusVo.getJdq_order_id(), feedbackOrderStatusVo);

            // 构建借点钱订单推送对象
            InvokeJdqRequestDto invokeJdqRequestDto = jdqSignService.channelBuildInvokeJdqRequest(JdqPushEnum.ORDER_STATE_CALL, feedbackOrderStatusVo);
            if (invokeJdqRequestDto == null) {
                log.error("构建借点钱订单推送对象失败 {}", feedbackOrderStatusVo);
                return false;
            }

            // 发送借点钱订单推送请求
            if (jdqSignService.sendJdqRequest(invokeJdqRequestDto) == null) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据订单状态推送借点钱订单状态 异常 {}", e);
        }

        return false;
    }

    @Override
    public FeedbackOrderStatusVo findFeedbackOrderStatusVoByStatus(int status, int repaymentStatus, String jdqOrderId, String localOrderId) {

        // 本地订单状态 status:（0初始化10待审核20审核中30审核失败40待绑卡50绑卡中60待签约70签约中80待放款90放款中100放款失败110待还款120还款中130已结清140逾期1000订单取消）
        // 本地订单还款状态 repaymentStatus: （0初始化10待还款20已结清30已逾期40逾期结清）
        // 借点钱订单状态 订单状态：0:待审核，1:已取消，2:审核失败，4:待签约，5:签约失败，6:待放款，7:已放款，8:已还清，10:逾期还清，13:已逾期，15:放款失败（最终状态）,16:额度失效，22：待绑卡，24：待开户，25：待授权

        // :还款状态（0初始化10待还款20已结清30已逾期40逾期结清）

        FeedbackOrderStatusVo feedbackOrderStatusVo = new FeedbackOrderStatusVo();
        feedbackOrderStatusVo.setJdq_order_id(jdqOrderId);
        // 是否可换卡 1-是，0-否
        feedbackOrderStatusVo.setChange_card_flag(0);
        // 0-不需要存管账户提款，1-需要去存管账户提款，用户已经去存管提款了，不需要再次去提时需再传0过来。放款后必传
        feedbackOrderStatusVo.setWithdraw_flag(0);
        // 1-支持还款账户于还款日进行自动划扣(如果机构支持主动还款，用户也可进行主动还款)。 2-不支持还款账户于还款日进行自动划扣(机构必须支持用户主动还款)。 放款后必传
        feedbackOrderStatusVo.setAutopay_flag(1);
        try {

            // 本地订单状态转换借点钱订单状态
            int jdqOrderStatus = convertJdqOrderStatus(status, repaymentStatus, jdqOrderId);
            if (jdqOrderStatus == 99999) {
                log.info("订单状态无需推送 status:{} repaymentStatus:{} jdqOrderId:{}", status, repaymentStatus, jdqOrderId);
                return null;
            }

            feedbackOrderStatusVo.setStatus(jdqOrderStatus);

            String appId = jdqCommonProperties.getRiskAppId();
            // 当订单状态为40:待绑卡及以上时 获取贷款信息
            if (status >= 40 && status != 1000) {
                //获取贷款信息
                findApprovalLoanInfo(appId, feedbackOrderStatusVo);
            }

            // 当订单状态为60:待签约及以上时 获取用户放款/还款银行卡
            if (status >= 60 && status != 1000) {
                // 获取用户放款/还款银行卡
                findBankList(appId, localOrderId, feedbackOrderStatusVo);
            }

            // 当订单状态为80:待放款及以上时 获取确认借款贷款信息
            if (status >= 80 && status != 1000) {
                // 获取确认借款贷款信息
                findLoanInfo(appId, localOrderId, feedbackOrderStatusVo);
            }

            // 当订单状态为110:待还款及以上时 获取还款计划
            if (status >= 110 && status != 1000) {
                // 获取还款计划
                findRepaymentPlan(status, repaymentStatus, appId, localOrderId, feedbackOrderStatusVo);
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据订单状态推送借点钱订单状态 异常 {}", e);
        }

        return feedbackOrderStatusVo;
    }

    @Override
    public FeedbackOrderStatusVo findApprovalLoanInfo(String appId, FeedbackOrderStatusVo feedbackOrderStatusVo) {

        RestResult<ProductConfigVo> productConfigVoRestResult = baseCloudHystrixService.getProductConfigVo(jdqCommonProperties.getRiskAppId());
        if (productConfigVoRestResult == null || !productConfigVoRestResult.isSuccess()) {
            log.error("获取贷款信息失败 appId:{} feedbackOrderStatusVo:{}", appId, feedbackOrderStatusVo);
            return feedbackOrderStatusVo;
        }

        ProductConfigVo productConfigVo = productConfigVoRestResult.getData().getBody();
        if (productConfigVo == null) {
            log.error("获取贷款信息为空 appId:{} feedbackOrderStatusVo:{}", appId, feedbackOrderStatusVo);
            return feedbackOrderStatusVo;
        }
        log.info("获取贷款信息:{}", productConfigVo);
        // 审批金额，单位元，审批通过后必传
        feedbackOrderStatusVo.setApproval_amount(productConfigVo.getPrice() == null?"0":productConfigVo.getPrice().toString());
        // 审批期数，审批通过后必传
        feedbackOrderStatusVo.setApproval_periods(productConfigVo.getPeriods());
        // 审批每期天数，审批通过后必传 （月按30计算）
        feedbackOrderStatusVo.setApproval_period_days(productConfigVo.getCycle());
        return feedbackOrderStatusVo;
    }

    @Override
    public FeedbackOrderStatusVo findLoanInfo(String appId, String localOrderId, FeedbackOrderStatusVo feedbackOrderStatusVo) {

        RestResult<OrderBorrowVo>  orderCreditVoRestResult = orderBorrowCloudHystrixService.orderBorrowSearch(appId, localOrderId);
        if (orderCreditVoRestResult == null || !orderCreditVoRestResult.isSuccess()) {
            log.error("获取确认借款贷款信息失败 appId:{} localOrderId:{} feedbackOrderStatusVo:{}", appId, localOrderId, feedbackOrderStatusVo);
            return feedbackOrderStatusVo;
        }

        OrderBorrowVo orderBorrowVo = orderCreditVoRestResult.getData().getBody();
        if (orderBorrowVo == null) {
            log.error("获取确认借款贷款信息为空 appId:{} localOrderId:{} feedbackOrderStatusVo:{}", appId, localOrderId, feedbackOrderStatusVo);
            return feedbackOrderStatusVo;
        }
        log.info("获取确认借款贷款信息:{}", orderBorrowVo);
        // 确认借款/合同金额，单位元，确认借款后必传
        feedbackOrderStatusVo.setLoan_amount((orderBorrowVo.getBorrowAmount() == null?new BigDecimal("0"):orderBorrowVo.getBorrowAmount().add(orderBorrowVo.getPreBorrowAmount() ==null?new BigDecimal("0"):orderBorrowVo.getPreBorrowAmount())).toString());
        // 确认借款/合同期数，确认借款后必传
        feedbackOrderStatusVo.setLoan_periods(orderBorrowVo.getPeriods());
        // 确认借款/合同每期天数，确认借款后必传（月按30计算）
        feedbackOrderStatusVo.setLoan_period_days(orderBorrowVo.getCycle());
        // 实际到手金额，确认借款后必传
        feedbackOrderStatusVo.setCard_amount(orderBorrowVo.getBorrowAmount().toString());
        return feedbackOrderStatusVo;
    }

    @Override
    public FeedbackOrderStatusVo findBankList(String appId, String localOrderId, FeedbackOrderStatusVo feedbackOrderStatusVo) {

        RestResult<OrderBankVo> orderBankRestResult = orderBankCloudHystrixService.orderBankSearch(appId, localOrderId);
        if (orderBankRestResult == null || !orderBankRestResult.isSuccess()) {
            log.error("获取用户放款/还款银行卡失败 appId:{} localOrderId:{} feedbackOrderStatusVo:{}", appId, localOrderId, feedbackOrderStatusVo);
            return feedbackOrderStatusVo;
        }

        if (orderBankRestResult.getData() == null || orderBankRestResult.getData().getBody() == null) {
            log.error("获取用户放款/还款银行卡列表为空 appId:{} localOrderId:{} feedbackOrderStatusVo:{}", appId, localOrderId, feedbackOrderStatusVo);
            return feedbackOrderStatusVo;
        }

        OrderBankVo orderBankVo = orderBankRestResult.getData().getBody();
        log.info("获取用户放款/还款银行卡:{}", orderBankVo);
        List<BankCardInfoVo> bankCardInfoVoList = new ArrayList<>();
        BankCardInfoVo bankCardInfoVo = new BankCardInfoVo();
        // 卡类型：1-借记卡，2-信用卡
        bankCardInfoVo.setCard_type(orderBankVo.getCardType());
        // 银行代码
        bankCardInfoVo.setBank_code(orderBankVo.getBankCode());
        // 银行卡号
        bankCardInfoVo.setCard_no(orderBankVo.getBankCardNo());
        // 银行名
        bankCardInfoVo.setBank_name(orderBankVo.getBankName());
        bankCardInfoVoList.add(bankCardInfoVo);

        feedbackOrderStatusVo.setBank_card_info(bankCardInfoVoList);
        return feedbackOrderStatusVo;
    }

    @Override
    public FeedbackOrderStatusVo findRepaymentPlan(int status, int repaymentStatus, String appId, String localOrderId, FeedbackOrderStatusVo feedbackOrderStatusVo) {


        RestResult<OrderRepaymentPlanVo>  orderRepaymentPlanVoRestResult;
        // 已结清
        if (status == 130) {
            orderRepaymentPlanVoRestResult = orderRepaymentCloudHystrixService.orderRepaymentPlanSuccessSearch(appId, localOrderId);
        } else {
            orderRepaymentPlanVoRestResult = orderRepaymentCloudHystrixService.orderRepaymentPlanSearch(appId, localOrderId);
        }

        if (orderRepaymentPlanVoRestResult == null || !orderRepaymentPlanVoRestResult.isSuccess()) {
            log.error("获取还款计划 appId:{} localOrderId:{} feedbackOrderStatusVo:{}", appId, localOrderId, feedbackOrderStatusVo);
            return feedbackOrderStatusVo;
        }

        OrderRepaymentPlanVo orderRepaymentPlanVo = orderRepaymentPlanVoRestResult.getData().getBody();
        if (orderRepaymentPlanVo == null) {
            log.error("获取还款计划为空 appId:{} localOrderId:{} feedbackOrderStatusVo:{}", appId, localOrderId, feedbackOrderStatusVo);
            return feedbackOrderStatusVo;
        }
        log.info("获取还款计划:{}", orderRepaymentPlanVo);
        List<RepaymentPlanVo> repaymentPlanVoList = new ArrayList<>();
        RepaymentPlanVo repaymentPlanVo = new RepaymentPlanVo();
        // 实际还款日期 ，格式：yyyy-MM-dd注：在实际还款完成必传
        repaymentPlanVo.setTrue_repayment_time(orderRepaymentPlanVo.getActualrepayDate());
        // 计划还款日期，格式：yyyy-MM-dd
        repaymentPlanVo.setPlan_repayment_time(LocalDateUtil.dateToString(LocalDateUtil.parseToDate(orderRepaymentPlanVo.getRepayDate(),"yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd"));
        // 本期账单金额中的本金，单位元，数值
        repaymentPlanVo.setAmount(orderRepaymentPlanVo.getPrincipal());
        // 前期费用(如果机构前期费用扣取失败，需要用户主动支付，需更新此字段)，单位元，数值
        repaymentPlanVo.setMember_fee(new BigDecimal("0"));
        // 本期手续（利息）费，单位元，数值
        repaymentPlanVo.setPeriod_fee(orderRepaymentPlanVo.getInterestFee()==null?new BigDecimal("0"):orderRepaymentPlanVo.getInterestFee());
        // 已还金额，单位元，还清后传已还总金额
        if ("2".equals(orderRepaymentPlanVo.getStatus())) {
            repaymentPlanVo.setRepaid_amount((orderRepaymentPlanVo.getPrincipal().add(orderRepaymentPlanVo.getTotalInterestFee()==null?new BigDecimal(0):orderRepaymentPlanVo.getTotalInterestFee())));
        }
        // 期数
        repaymentPlanVo.setPeriod(1);
        // 还款计划状态：1-待还款，2-结清，4-逾期
        // 本地订单状态 status:（0初始化10待审核20审核中30审核失败40待绑卡50绑卡中60待签约70签约中80待放款90放款中100放款失败110待还款120还款中130已结清140逾期1000订单取消）
        // 本地订单还款状态 repaymentStatus: （0初始化10待还款20已结清30已逾期40逾期结清）
        repaymentPlanVo.setStatus(1);
        if (repaymentStatus == 20 || repaymentStatus == 40) {
            repaymentPlanVo.setStatus(2);
        } else if (repaymentStatus == 30) {
            repaymentPlanVo.setStatus(4);
        }
        // 逾期罚款，单位元，数值
        repaymentPlanVo.setOverdue_fee(orderRepaymentPlanVo.getTotalPenaltyInterestFee()==null?new BigDecimal("0"):orderRepaymentPlanVo.getTotalPenaltyInterestFee());
        // 逾期天数(如：10)
        repaymentPlanVo.setOverdue_day(orderRepaymentPlanVo.getOverDueDays()==null?0:orderRepaymentPlanVo.getOverDueDays());
        // 是否逾期，0-未逾期，1-逾期
        repaymentPlanVo.setOverdue(0);
        if (repaymentStatus == 30 || repaymentStatus == 40) {
            repaymentPlanVo.setOverdue(1);
        }
        repaymentPlanVoList.add(repaymentPlanVo);
        feedbackOrderStatusVo.setRepayment_plan(repaymentPlanVoList);
        return feedbackOrderStatusVo;
    }

    @Override
    public int convertJdqOrderStatus(int localOrderStatus, int repaymentStatus, String jdqOrderId) {

        switch (localOrderStatus) {
            // 0:初始化
            case 0:
                // 不做任何处理，直接返回
                return 99999;
            // 10:待审核
            case 10:
                // 20:审核中
            case 20:
                // 推送借点钱订单状态 -> 0:待审核
                return 0;
            // 30:审核失败
            case 30:
                // 推送借点钱订单状态 -> 2:审核失败
                return 2;
            // 40:待绑卡
            case 40:
                // 50:绑卡中
            case 50:
                // 推送借点钱订单状态 -> 22:待绑卡
                return 22;
            // 60:待签约
            case 60:
                // 推送借点钱订单状态 -> 4:待签约
                return 4;
            // 70:签约中
            case 70:
                // 当前暂时无此状态
                return 99999;
            // 80:待放款
            case 80:
                // 推送借点钱订单状态 -> 6:待放款
                return 6;
            // 90:放款中
            case 90:
                // 当前暂时无此状态
                return 99999;
            // 100:放款失败
            case 100:
                // 推送借点钱订单状态 -> 15:放款失败
                return 15;
            // 110:待还款
            case 110:
                // 推送借点钱订单状态 -> 7:已放款
                return 7;
            // 120:还款中
            case 120:
                // 当前暂时无此状态
                return 99999;
            // 130:已结清
            case 130:

                if(repaymentStatus == 20) {
                    // 还款状态为 20：已结清 推送借点钱订单状态 -> 8:已还清
                    return 8;
                } else if(repaymentStatus == 40) {
                    // 还款状态为 40：逾期结清 推送借点钱订单状态 -> 10:逾期还清
                    return 10;
                } else {
                    log.error("订单状态为已结清，但是还款状态不为20或40 借点钱订单号:{}", jdqOrderId);
                    throw new RuntimeException("订单状态为130:已结清，但是还款状态不为20:已结清或40:逾期结清 借点钱订单号:"+ jdqOrderId);
                }
            // 140:逾期
            case 140:
                // 推送借点钱订单状态 -> 13:已逾期
                return 13;
            // 1000:订单取消
            case 1000:
                // 推送借点钱订单状态 -> 1:已取消
                return 1;
            default:
                break;

        }
        throw new RuntimeException("订单状态不存在 status:"+localOrderStatus+",jdqOrderId:" + jdqOrderId);
    }

}
