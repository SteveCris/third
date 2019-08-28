package com.shangyong.thjdq.controller;

import com.alibaba.fastjson.JSONObject;
import com.shangyong.common.entity.RestResult;
import com.shangyong.rest.feign.OrderBankCloudHystrixService;
import com.shangyong.rest.feign.OrderBorrowCloudHystrixService;
import com.shangyong.rest.feign.OrderCloudHystrixService;
import com.shangyong.rest.feign.OrderRepaymentCloudHystrixService;
import com.shangyong.thcore.dto.OrderBankH5Dto;
import com.shangyong.thcore.dto.OrderBorrowH5Dto;
import com.shangyong.thcore.dto.OrderRepaymentH5Dto;
import com.shangyong.thcore.util.ThOrderStateUtils;
import com.shangyong.thcore.vo.OrderBankH5Vo;
import com.shangyong.thcore.vo.OrderBorrowH5Vo;
import com.shangyong.thcore.vo.OrderLoanVo;
import com.shangyong.thcore.vo.OrderRepaymentH5Vo;
import com.shangyong.thjdq.config.properties.JdqCommonProperties;
import com.shangyong.thjdq.dto.*;
import com.shangyong.thjdq.entity.UserInfo;
import com.shangyong.thjdq.enums.ResponseCode;
import com.shangyong.thjdq.handler.exception.JdqSignException;
import com.shangyong.thjdq.service.JdqOrderPushService;
import com.shangyong.thjdq.service.JdqSignService;
import com.shangyong.thjdq.service.UserInfoService;
import com.shangyong.thjdq.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 借点钱h5跳转接口数据转换
 * Created by ybds on 2019-03-22.
 */
@Api(tags = "zbb-郑斌斌-借点钱h5跳转接口数据转换")
@RestController
public class H5ForwardController {

    private static final Logger log = LoggerFactory.getLogger(H5ForwardController.class);

    @Autowired
    private JdqSignService jdqSignService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private JdqCommonProperties jdqCommonProperties;
    @Autowired
    private OrderBankCloudHystrixService orderBankCloudHystrixService;
    @Autowired
    private OrderCloudHystrixService orderCloudHystrixService;
    @Autowired
    private OrderRepaymentCloudHystrixService orderRepaymentCloudHystrixService;
    @Autowired
    private OrderBorrowCloudHystrixService orderBorrowCloudHystrixService;
    @Autowired
    private JdqOrderPushService jdqOrderPushService;

    /**
     * 接收跳转机构绑卡接口
     * @param jdqInvokeChannelDto
     * @return
     */
    @ApiOperation("接收跳转机构绑卡接口")
    @RequestMapping(value="/bindCardExt", method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response<BindCardExtVo> bindCardExt(@RequestBody JdqInvokeChannelDto jdqInvokeChannelDto) {

        try {
            // 借点钱数据解密
            if(!jdqSignService.channelParseJdqInvokeRequest(jdqInvokeChannelDto)) {
                throw new JdqSignException(ResponseCode.BUSINESS_DATA_NOT_EXIST);
            }

            if (jdqInvokeChannelDto.getData() == null || StringUtils.isEmpty(jdqInvokeChannelDto.getData())) {
                throw new JdqSignException(ResponseCode.BUSINESS_DATA_NOT_EXIST);
            }

            log.info("跳转机构绑卡接口 data {}", jdqInvokeChannelDto.getData());

            BindCardExtDto bindCardExtDto  = JSONObject.parseObject(jdqInvokeChannelDto.getData(), BindCardExtDto.class);
            UserInfo userInfo = userInfoService.selectByJdqOrderId(bindCardExtDto.getJdq_order_id());

            // 查询订单 查看订单是否正常
            RestResult<OrderLoanVo> orderLoanVoRestResult = orderCloudHystrixService.orderSearch(jdqCommonProperties.getRiskAppId(), userInfo.getJdqOrderId());
            log.info("查询订单结果:{}",orderLoanVoRestResult);
            if (orderLoanVoRestResult == null || !orderLoanVoRestResult.isSuccess()) {
                return ResponseCode.FAILED.toResponse("订单不存在");
            }

            // 过滤卡状态
            int status = orderLoanVoRestResult.getData().getBody().getStatus();
            if(!ThOrderStateUtils.ifCanBindCard(status)) {
                log.error("订单状态不对，不允许绑卡 status:{}", status);
                return ResponseCode.FAILED.toResponse("订单状态不对，不允许绑卡");
            }

            //订单状态（0初始化10待审核20审核中30审核失败40待绑卡50绑卡中60待签约70签约中80待放款90放款中100放款失败110待还款120还款中130已结清140逾期1000订单取消）
            // 数据转换
            OrderBankH5Dto orderBankH5Dto = new OrderBankH5Dto();
            orderBankH5Dto.setAppName(String.valueOf(jdqCommonProperties.getRiskAppName()));
            orderBankH5Dto.setSuccessReturnUrl(bindCardExtDto.getSuccess_return_url());
            orderBankH5Dto.setFailReturnUrl(bindCardExtDto.getFail_return_url());

            // 获得银行绑定h5链接
            RestResult<OrderBankH5Vo> orderBankH5VoRestResult = orderBankCloudHystrixService.bindH5Search(jdqCommonProperties.getRiskAppId(), userInfo.getLocalOrderId(), orderBankH5Dto);
            log.info("获得银行绑定h5链接result:{}", orderBankH5VoRestResult);
            if (orderBankH5VoRestResult == null || !orderBankH5VoRestResult.isSuccess()) {
                return ResponseCode.FAILED.toResponse();
            }

            BindCardExtVo bindCardExtVo = new BindCardExtVo();
            bindCardExtVo.setBind_status(orderBankH5VoRestResult.getData().getBody().getStatus());
            bindCardExtVo.setUrl(orderBankH5VoRestResult.getData().getBody().getH5Url());
            return ResponseCode.OK.toResponse(bindCardExtVo);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("接收跳转机构绑卡接口 异常 {}", e);
        }

        return ResponseCode.FAILED.toResponse();
    }

    /**
     * 接收跳转机构确认借款接口
     * @param jdqInvokeChannelDto
     * @return
     */
    @ApiOperation("接收跳转机构确认借款接口")
    @RequestMapping(value="/confirmLoanExt", method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response<ConfirmLoanExtVo> confirmLoanExt(@RequestBody JdqInvokeChannelDto jdqInvokeChannelDto) {

        try {
            // 借点钱数据解密
            if(!jdqSignService.channelParseJdqInvokeRequest(jdqInvokeChannelDto)) {
                throw new JdqSignException(ResponseCode.BUSINESS_DATA_NOT_EXIST);
            }

            if (jdqInvokeChannelDto.getData() == null || StringUtils.isEmpty(jdqInvokeChannelDto.getData())) {
                throw new JdqSignException(ResponseCode.BUSINESS_DATA_NOT_EXIST);
            }

            log.info("跳转机构确认借款接口 data {}", jdqInvokeChannelDto.getData());

            ConfirmLoanExtDto confirmLoanExtDto  = JSONObject.parseObject(jdqInvokeChannelDto.getData(), ConfirmLoanExtDto.class);
            UserInfo userInfo = userInfoService.selectByJdqOrderId(confirmLoanExtDto.getJdq_order_id());

            // 查询订单 查看订单是否正常
            RestResult<OrderLoanVo> orderLoanVoRestResult = orderCloudHystrixService.orderSearch(jdqCommonProperties.getRiskAppId(), userInfo.getJdqOrderId());
            log.info("查询订单结果:{}",orderLoanVoRestResult);
            if (orderLoanVoRestResult == null || !orderLoanVoRestResult.isSuccess()) {
                return ResponseCode.FAILED.toResponse("订单不存在");
            }

            //订单状态（0初始化10待审核20审核中30审核失败40待绑卡50绑卡中60待签约70签约中80待放款90放款中100放款失败110待还款120还款中130已结清140逾期1000订单取消）
            int status = orderLoanVoRestResult.getData().getBody().getStatus();

            // 校验能否借款
            if(!ThOrderStateUtils.ifCanConfirmLoan(status)) {
                log.error("该订单状态为 status:{} 无法跳转到确认借款H5链接地址", status);
                return ResponseCode.FAILED.toResponse("订单状态不对");
            }

            // 数据转换
            OrderBorrowH5Dto orderBorrowH5Dto = new OrderBorrowH5Dto();
            orderBorrowH5Dto.setAppName(String.valueOf(jdqCommonProperties.getRiskAppName()));
            orderBorrowH5Dto.setSuccessReturnUrl(confirmLoanExtDto.getSuccess_return_url());
            orderBorrowH5Dto.setFailReturnUrl(confirmLoanExtDto.getFail_return_url());

            // 获得借款h5链接
            RestResult<OrderBorrowH5Vo> orderBorrowH5VoRestResult = null;
            if (ThOrderStateUtils.ifCanPreSignature(status)) {
                // 待前置签约
                orderBorrowH5VoRestResult = orderBorrowCloudHystrixService.sureorderH5Search(jdqCommonProperties.getRiskAppId(), userInfo.getLocalOrderId(), orderBorrowH5Dto);
            } else if (ThOrderStateUtils.ifCanPendingSigning(status)) {
                // 待后置签约
                orderBorrowH5VoRestResult = orderBorrowCloudHystrixService.withdrawH5Search(jdqCommonProperties.getRiskAppId(), userInfo.getLocalOrderId(), orderBorrowH5Dto);
            } else {
                return ResponseCode.FAILED.toResponse();
            }

            log.info("获得借款h5链接result:{}", orderBorrowH5VoRestResult);
            if (orderBorrowH5VoRestResult == null || !orderBorrowH5VoRestResult.isSuccess()) {
                return ResponseCode.FAILED.toResponse();
            }
            if (orderBorrowH5VoRestResult.getData().getBody().getStatus() == 2) {
                return ResponseCode.FAILED.toResponse("您未借款");
            }

            ConfirmLoanExtVo confirmLoanExtVo = new ConfirmLoanExtVo();
            confirmLoanExtVo.setUrl(orderBorrowH5VoRestResult.getData().getBody().getH5Url());
            return ResponseCode.OK.toResponse(confirmLoanExtVo);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("接收跳转机构确认借款接口 异常 {}", e);
        }

        return ResponseCode.FAILED.toResponse();
    }

    /**
     * 接收订单状态查询接口
     * @param jdqInvokeChannelDto
     * @return
     */
    @ApiOperation("接收订单状态查询接口")
    @RequestMapping(value="/pullOrderStatus", method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response<FeedbackOrderStatusVo> pullOrderStatus(@RequestBody JdqInvokeChannelDto jdqInvokeChannelDto) {

        try {
            // 借点钱数据解密
            if(!jdqSignService.channelParseJdqInvokeRequest(jdqInvokeChannelDto)) {
                throw new JdqSignException(ResponseCode.BUSINESS_DATA_NOT_EXIST);
            }

            if (jdqInvokeChannelDto.getData() == null || StringUtils.isEmpty(jdqInvokeChannelDto.getData())) {
                throw new JdqSignException(ResponseCode.BUSINESS_DATA_NOT_EXIST);
            }

            log.info("订单状态查询接口 data {}", jdqInvokeChannelDto.getData());

            PullOrderStatusDto pullOrderStatusDto  = JSONObject.parseObject(jdqInvokeChannelDto.getData(), PullOrderStatusDto.class);

            // 查询订单
            RestResult<OrderLoanVo> orderLoanVoRestResult = orderCloudHystrixService.orderSearch(jdqCommonProperties.getRiskAppId(), pullOrderStatusDto.getJdq_order_id());
            log.info("查询订单结果:{}",orderLoanVoRestResult);
            if (orderLoanVoRestResult == null || !orderLoanVoRestResult.isSuccess() || orderLoanVoRestResult.getData() == null || orderLoanVoRestResult.getData().getBody() == null) {
                log.error("订单不存在 {}",pullOrderStatusDto.getJdq_order_id());
                return ResponseCode.FAILED.toResponse("订单不存在");
            }

            FeedbackOrderStatusVo feedbackOrderStatusVo = jdqOrderPushService.findFeedbackOrderStatusVoByStatus(orderLoanVoRestResult.getData().getBody().getStatus(),orderLoanVoRestResult.getData().getBody().getRepaymentStatus(),orderLoanVoRestResult.getData().getBody().getOtherOrderId(),orderLoanVoRestResult.getData().getBody().getOrderId());
            if (feedbackOrderStatusVo == null) {
                log.info("订单状态不对,不返回订单信息 {}", orderLoanVoRestResult.getData().getBody());
                return ResponseCode.FAILED.toResponse("订单状态不对，不返回订单信息");
            }

            log.info("订单状态 jdqOrderId:{} {}",feedbackOrderStatusVo.getJdq_order_id(), feedbackOrderStatusVo);
            return ResponseCode.OK.toResponse(feedbackOrderStatusVo);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("接收订单状态查询接口 异常 {}", e);
        }
        return ResponseCode.FAILED.toResponse();
    }

    /**
     * 接收跳转机构还款接口
     * @param jdqInvokeChannelDto
     * @return
     */
    @ApiOperation("接收跳转机构还款接口")
    @RequestMapping(value="/repaymentExt", method= RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response<RepaymentExtVo> repaymentExt(@RequestBody JdqInvokeChannelDto jdqInvokeChannelDto) {

        try {
            // 借点钱数据解密
            if(!jdqSignService.channelParseJdqInvokeRequest(jdqInvokeChannelDto)) {
                throw new JdqSignException(ResponseCode.BUSINESS_DATA_NOT_EXIST);
            }

            if (jdqInvokeChannelDto.getData() == null || StringUtils.isEmpty(jdqInvokeChannelDto.getData())) {
                throw new JdqSignException(ResponseCode.BUSINESS_DATA_NOT_EXIST);
            }

            log.info("跳转机构还款接口 data {}", jdqInvokeChannelDto.getData());

            RepaymentExtDto repaymentExtDto  = JSONObject.parseObject(jdqInvokeChannelDto.getData(), RepaymentExtDto.class);
            UserInfo userInfo = userInfoService.selectByJdqOrderId(repaymentExtDto.getJdq_order_id());

            // 查询订单
            RestResult<OrderLoanVo> orderLoanVoRestResult = orderCloudHystrixService.orderSearch(jdqCommonProperties.getRiskAppId(), userInfo.getJdqOrderId());
            log.info("查询订单结果:{}",orderLoanVoRestResult);
            if (orderLoanVoRestResult == null || !orderLoanVoRestResult.isSuccess()) {
                return ResponseCode.FAILED.toResponse("订单不存在");
            }

            //订单状态（0初始化10待审核20审核中30审核失败40待绑卡50绑卡中60待签约70签约中80待放款90放款中100放款失败110待还款120还款中130已结清140逾期1000订单取消）
            int status = orderLoanVoRestResult.getData().getBody().getStatus();
            // 校验能否还款
            if(!ThOrderStateUtils.ifCanRepayment(status)) {
                log.error("订单状态不对，不允还款 status:{}", status);
                return ResponseCode.FAILED.toResponse("订单状态不对，不允还款");
            }

            // 数据转换
            OrderRepaymentH5Dto orderBorrowH5Dto = new OrderRepaymentH5Dto();
            orderBorrowH5Dto.setAppName(String.valueOf(jdqCommonProperties.getRiskAppName()));
            orderBorrowH5Dto.setSuccessReturnUrl(repaymentExtDto.getSuccess_return_url());
            orderBorrowH5Dto.setFailReturnUrl(repaymentExtDto.getFail_return_url());

            // 获取还款h5链接
            RestResult<OrderRepaymentH5Vo> orderRepaymentH5VoRestResult = orderRepaymentCloudHystrixService.repaymentH5Search(jdqCommonProperties.getRiskAppId(), userInfo.getLocalOrderId(), orderBorrowH5Dto);
            log.info("获取还款h5链接result:{}", orderRepaymentH5VoRestResult);
            if (orderRepaymentH5VoRestResult == null || !orderRepaymentH5VoRestResult.isSuccess()) {
                return ResponseCode.FAILED.toResponse();
            }

            RepaymentExtVo repaymentExtVo = new RepaymentExtVo();
            repaymentExtVo.setUrl(orderRepaymentH5VoRestResult.getData().getBody().getH5Url());
            return ResponseCode.OK.toResponse(repaymentExtVo);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("接收跳转机构还款接口 异常 {}", e);
        }

        return ResponseCode.FAILED.toResponse();
    }

}
