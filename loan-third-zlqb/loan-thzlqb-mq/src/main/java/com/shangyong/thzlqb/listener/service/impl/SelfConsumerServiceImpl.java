package com.shangyong.thzlqb.listener.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.shangyong.loan.autoconfigure.redis.BatchRedisTemplate;
import com.shangyong.rest.feign.OrderUserCloudHystrixService;
import com.shangyong.thzlqb.bo.CoreAuditUserInfoBo;
import com.shangyong.thzlqb.dto.UserInfoDto;
import com.shangyong.thzlqb.entity.ZlqbUserDevice;
import com.shangyong.thzlqb.entity.ZlqbUserInfo;
import com.shangyong.thzlqb.service.*;
import com.shangyong.thzlqb.utils.BeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.shangyong.backend.dto.AddressBookDto;
import com.shangyong.backend.dto.ApplicationAllDto;
import com.shangyong.backend.dto.ApplicationDto;
import com.shangyong.backend.dto.CuCustomerCompanyDto;
import com.shangyong.backend.dto.CuIcePersonDto;
import com.shangyong.backend.dto.CuPlatformCustomerDto;
import com.shangyong.backend.dto.DataReportDto;
import com.shangyong.backend.dto.FaceRecognitionScoreDto;
import com.shangyong.backend.fegin.ApplicationCloudHystrixService;
import com.shangyong.backend.fegin.ReportCloudHystrixService;
import com.shangyong.backend.fegin.UploadCloudHystrixService;
import com.shangyong.common.entity.RestResult;
import com.shangyong.loan.ext.exception.CalfException;
import com.shangyong.loan.ext.util.IdCardUtil;
import com.shangyong.loan.ext.util.IdUtil;
import com.shangyong.loan.ext.util.ResultUtil;
import com.shangyong.rest.feign.BaseCloudHystrixService;
import com.shangyong.rest.feign.OrderCloudHystrixService;
import com.shangyong.thcore.vo.ProductConfigVo;
import com.shangyong.thzlqb.bo.OrderSimpleBo;
import com.shangyong.thzlqb.contants.CoreContants;
import com.shangyong.thzlqb.contants.UuidPrefix;
import com.shangyong.thzlqb.entity.ZlqbLinkman;
import com.shangyong.thzlqb.entity.ZlqbOrderReview;
import com.shangyong.thzlqb.enums.ZlqbJobCategoryEnum;
import com.shangyong.thzlqb.enums.ZlqbOrderReviewStatusEnum;
import com.shangyong.thzlqb.enums.ZlqbOrderStatusEnum;
import com.shangyong.thzlqb.enums.ZlqbProfessionTypeEnum;
import com.shangyong.thzlqb.enums.ZlqbResultEnum;
import com.shangyong.thzlqb.event.PushEvent;
import com.shangyong.thzlqb.listener.service.SelfConsumerService;
import com.shangyong.thzlqb.send.Sender;
import com.shangyong.thzlqb.utils.JacksonUtil;
import com.shangyong.thzlqb.utils.OSSFileUtil;
import com.shangyong.thzlqb.zlqb.utils.ZlqbUtil;
import org.springframework.util.CollectionUtils;

@Service
public class SelfConsumerServiceImpl implements SelfConsumerService {

    private Logger logger = LoggerFactory.getLogger(SelfConsumerServiceImpl.class);

    protected static final List<String> list = Arrays.asList("frontFile", "backFile", "natureFile");

    protected static final List<String> nodeList = Arrays.asList("basicInfo", "companyInfo", "identifyInfo",
            "deviceInfo", "address_book", "operator");

    protected static final List<String> parmList = Arrays.asList("validDate", "nation", "permanentAddress",
            "commanyCity", "addressProvince", "companyName", "issuedBy", "address", "companyAddress", "idCard","houseAddress","isMarry",
            "commanyNature","profession","addressCity","addressArea","income","zmScore","confidence");

    @Autowired
    private CoreOrderService coreOrderService;

    @Autowired
    private MongodbService mongodbService;

    @Autowired
    private ZlqbUserService zlqbUserService;

    @Autowired
    private ZlqbOrderReviewService zlqbOrderReviewService;

    @Autowired
    private OrderCloudHystrixService orderCloudHystrixService;

    @Autowired
    private ReportCloudHystrixService reportCloudHystrixService;

    @Autowired
    private UploadCloudHystrixService uploadCloudHystrixService;

    @Autowired
    private Sender sender;

    @Autowired
    private ApplicationCloudHystrixService applicationCloudHystrixService;

    @Autowired
    private BaseCloudHystrixService baseCloudHystrixService;

    @Autowired
    private OrderUserCloudHystrixService orderUserCloudHystrixService;

    @Autowired
    private BatchRedisTemplate batchRedisTemplate;

    @Autowired
    private ZlqbOrderStatusService zlqbOrderStatusService;

    protected  final AtomicInteger incream =new AtomicInteger();

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public boolean processPushTime(PushEvent rytPushEvent) {
        String orderNo = rytPushEvent.getOrderNo();
        if (!coreOrderService.updateOrderStatus(orderNo, ZlqbOrderStatusEnum.DATA_INVIDATION.getValue(),
                ZlqbOrderStatusEnum.VOID_DB.getValue(), false, null)) {
            return coreOrderService.checkStatus(orderNo, ZlqbOrderStatusEnum.DATA_INVIDATION.getValue());
        }

        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            @Override
            public void afterCommit() {
                sender.sendMq("/third", "ex.push.zlqb.fall", "push.rKey", rytPushEvent, true);
            }
        });
        ObjectNode objectNode = mongodbService.getData(CoreContants.PUSH_COLLECTION, orderNo);
        if (Objects.isNull(objectNode)) {
            logger.error("查询mongoDB数据为空 orderNo--》{}", orderNo);
            throw new CalfException(ZlqbResultEnum.MONGODB_SEARCH_NULL.getMessage());
        }
        JsonNode node = objectNode.get(nodeList.get(2));
        if (Objects.isNull(node)) {
            logger.error("查询mongoDB数据中用户的身份证相关参数为空  orderNo--》{}", orderNo);
            throw new CalfException(ZlqbResultEnum.NULL_ERROR.getMessage());
        }
        // 更新身份证url地址 natureFile 手持身份证要是人脸识别照片
        list.forEach(info -> {
            handleNodeFile(node, info, orderNo);
        });

        // 运营商的数据 因为助力钱包 报文数据不是url；并且不是生效时间来空值
        if (mongodbService.updateData(CoreContants.PUSH_COLLECTION, orderNo, objectNode)) {
            return true;
        } else {
            logger.error("更新mongdb失败，订单号：{}", orderNo);
            throw new CalfException(ZlqbResultEnum.MONGODB_UPDATE_ERROR.getMessage());
        }

    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public boolean processPushFall(PushEvent pushEvent) {

        String orderNo = pushEvent.getOrderNo();
        OrderSimpleBo simpleBo = coreOrderService.getOrderSimpleBo(orderNo);
        if (simpleBo == null) {
            logger.error("订单号{}，查不到对应信息", orderNo);
            return true;
        }

        if (!coreOrderService.updateOrderStatus(orderNo, ZlqbOrderStatusEnum.INSERT_DB.getValue(),
                ZlqbOrderStatusEnum.DATA_INVIDATION.getValue(), false, null)) {
            return coreOrderService.checkStatus(orderNo, ZlqbOrderStatusEnum.INSERT_DB.getValue());
        }

        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            @Override
            public void afterCommit() {
                sender.sendMq("/third", "ex.push.zlqb.audit", "push.rKey", pushEvent, true);
            }
        });
        // 获取mongo信息
        ObjectNode objectNode = mongodbService.getData(CoreContants.PUSH_COLLECTION, orderNo);

        ProductConfigVo configVo = ResultUtil
                .checkAndGet(baseCloudHystrixService.getProductConfigVo(ZlqbUtil.getAppid()));



        ZlqbUserInfo userInfo = sealUserInfoDto(objectNode, orderNo);
        // 用户信息新增
        zlqbUserService.insertDto(userInfo);

        ZlqbUserDevice zlqbUserDevice =sealUserDevice(objectNode,orderNo);
        //新增用户的设备信息表
        zlqbUserService.insertDeviceDto(zlqbUserDevice);

        // 紧急联系人新增
        zlqbUserService.saveBatchLinkMan(sealLinkManArray(objectNode, orderNo));
        // 初始化订单审核表记录
        zlqbOrderReviewService.saveZlqbReviewDto(sealZlqbOrderReviewDto(orderNo, configVo));

        // 推送订单至待审核
        RestResult<Void> toWaitResult = orderCloudHystrixService.orderToWaitAudit(ZlqbUtil.getAppid(),
                simpleBo.getOrderId());

        if (!ResultUtil.check(toWaitResult)) {
            // 进行回滚操作
            throw new CalfException(ZlqbResultEnum.REMOTE_ERROR.getMessage());
        }

        return true;
    }


    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public boolean processPushAudit(PushEvent pushEvent) {

        //用redis缓存一天的数据


        String orderNo = pushEvent.getOrderNo();

        ZlqbUserInfo one = zlqbUserService.selectOne(orderNo);

        if (Objects.nonNull(one) && batchRedisTemplate.isRepeatClick(CoreContants.REDIS_BUSINESS_KEY + one.getIdCard(), TimeUnit.DAYS.toSeconds(1))) {
            {
                logger.error("存在重复推单校验情况----》idCard:{}", one.getIdCard());
                throw new CalfException(ZlqbResultEnum.CLICK_REPEAT.getMessage());
            }
        }
        //通
        final String oneIdCard = one==null?"":one.getIdCard();

        String bankCardNo = zlqbUserService.getBankCardByIdCard(oneIdCard);
        //如果是重借
        if(StringUtils.isNotEmpty(bankCardNo))
        {
            zlqbOrderStatusService.concelBankInfoAndPushOrder(orderNo,bankCardNo);
        }

        if (!coreOrderService.updateOrderStatus(orderNo, ZlqbOrderStatusEnum.PUSH_CHECK.getValue(),
                ZlqbOrderStatusEnum.INSERT_DB.getValue(), false, null)) {
            return coreOrderService.checkStatus(orderNo, ZlqbOrderStatusEnum.PUSH_CHECK.getValue());
        }

        // 获取mongo信息
        ObjectNode objectNode = mongodbService.getData(CoreContants.PUSH_COLLECTION, orderNo);
        if (checkNodeParm(objectNode)) {
            logger.error("订单推审核单元时mongoDB数据查询时为空 orderNo --》{}", orderNo);
            throw new CalfException(ZlqbResultEnum.MONGODB_SEARCH_NULL.getMessage());
        }
        // 获取通讯录
        ArrayNode contactsArrayNode = (ArrayNode) objectNode.get("address_book");
        String contactsjson = contactsArrayNode.toString().replaceAll("\"name\"", "\"contactName\"")
                .replaceAll("\"mobile\"", "\"contactPhone\"").replaceAll(" ", "");
        // 上传通讯录信息
        AddressBookDto addressBookDto = new AddressBookDto();
        addressBookDto.setAppName(ZlqbUtil.getAppName());
        String idCard = objectNode.get(nodeList.get(0)).get(parmList.get(9)).asText();
        addressBookDto.setCustomerId(ZlqbUtil.getCustomerId(idCard));
        addressBookDto.setJsonInfo(JacksonUtil.parse(contactsjson, ArrayNode.class));
        RestResult<String> uploadAddressBookResult = uploadCloudHystrixService.uploadAddressBook(addressBookDto);
        logger.debug("通讯录请求参数 {} 相应结果 {}", addressBookDto, uploadAddressBookResult);
        ResultUtil.checkAndGet(uploadAddressBookResult);
        // 上报运营上报文数据
        DataReportDto dataReportDto = new DataReportDto();
        dataReportDto.setAppSerialNumber(orderNo);
        dataReportDto.setTaskType("09001");
        JsonNode operator = objectNode.get("operator");
        String jsonNode = "";
        if (Objects.nonNull(operator)) {
            jsonNode = operator.get("report") == null ? "" : operator.get("report").toString();
        }
        dataReportDto.setJsonInfo(jsonNode);
        RestResult<Boolean> operatorInfoUploadResult = reportCloudHystrixService.operatorInfoUpload(dataReportDto);
        logger.debug("运营商请求参数 {} 相应结果 {}", dataReportDto, operatorInfoUploadResult);
        ResultUtil.checkAndGet(operatorInfoUploadResult);

        boolean value = operatorInfoUploadResult.getData().getBody().booleanValue();
        if (!value) {
            throw new CalfException(ZlqbResultEnum.UPLOAD_OPERATOR_ERROR.getMessage());
        }
        // 上传申请单模板数据
        ApplicationAllDto applicationAllDto = new ApplicationAllDto();
        // 设置上传通讯录 时 返回 bu_thridpart_report 表中的id
        applicationAllDto.setThirdPartAddressBookId(uploadAddressBookResult.getData().getBody());
        // 平台申请单对象bean
        ApplicationDto beanDto = sealApplicationDto(objectNode, orderNo);
        applicationAllDto.setApplicationBean(beanDto);
        // 平台客户所属公司信息bean
        CuCustomerCompanyDto companyDto = sealCuCustomerCompanyDto(objectNode);
        applicationAllDto.setCuCustomerCompany(companyDto);
        // 平台客户信息表bean
        CuPlatformCustomerDto customerDto = sealCuPlatformCustomerDto(objectNode);
        applicationAllDto.setCuPlatformCustomer(customerDto);
        // 平台客户紧急联系人bean
        List<CuIcePersonDto> cuIcePersonList = sealCuicePersonList(objectNode);
        applicationAllDto.setCuIcePersonList(cuIcePersonList);

        // 平台客户客户人脸识别评分记录bean 人脸不一定用到
        FaceRecognitionScoreDto faceRecognitionScoreDto = new FaceRecognitionScoreDto();
        faceRecognitionScoreDto.setFaceUrl(objectNode.get(nodeList.get(2)).get(list.get(2)).asText());
        faceRecognitionScoreDto.setFrontUrl(objectNode.get(nodeList.get(2)).get(list.get(0)).asText());
        faceRecognitionScoreDto.setBackUrl(objectNode.get(nodeList.get(2)).get(list.get(1)).asText());
        JsonNode identNode = objectNode.get(nodeList.get(2));
        faceRecognitionScoreDto.setFaceAuthenticationScore(null==identNode.get(parmList.get(18))?"":identNode.get(parmList.get(18)).asText());
        applicationAllDto.setFaceRecognitionScore(faceRecognitionScoreDto);
        // 平台客户其它账号信息bean
        applicationAllDto.setCuCustomerOtherList(new ArrayList<>());
        // 芝麻 行业信息
        applicationAllDto.setIndustryDetailsList(new ArrayList<>());

        RestResult<Void> result = applicationCloudHystrixService.saveApplication(applicationAllDto);

        logger.debug("申请单模板请求参数 {} 相应结果 {}", applicationAllDto, result);

        return ResultUtil.check(result);
    }

    @Override
    public boolean refreshCustomeInfoFromMongoDB(String status) {
        Long startTime =System.currentTimeMillis();
        logger.info("刷新mongodb数据开始---》当前时间{}",startTime);
        Long num =  zlqbUserService.selectCount(Byte.valueOf(status));
        if(Objects.isNull(num) || num.compareTo(0L)==0){
          return false;
        }
        int startNum = 0;
        // 每次查询条数20条数据更新
        int pageSize = 20;
        // 查询几次
        int count;
        if (num % pageSize == 0) {
            count = (num.intValue() / pageSize);
        } else {
            count = (num.intValue() / pageSize) + 1;
        }
        for (int i = 0; i < count; i++) {
            List<ZlqbUserInfo> dataList =
                    zlqbUserService.selectAllPageInfoByLimit(Byte.valueOf(status),startNum, pageSize);
            if (CollectionUtils.isEmpty(dataList)) {
                continue;
            }
            dataList.forEach(info->{handleRefreshMongdoDB(info);});
        }
        final long endTime = System.currentTimeMillis();
        logger.info("刷新mongodb数据结束---》当前时间{},消耗的时间--》{},失败的次数 {}",endTime,endTime-startNum,incream.get());
        return true;
    }

    private void handleRefreshMongdoDB(ZlqbUserInfo zlqbUserInfo) {
        if(Objects.isNull(zlqbUserInfo)){
            return;
        }
        String orderNo = zlqbUserInfo.getOrderNo();
        ObjectNode objectNode = mongodbService.getData(CoreContants.PUSH_COLLECTION, orderNo);
        if(Objects.isNull(objectNode)){
            return;
        }

        ZlqbUserInfo userInfo = sealUserInfoDto(objectNode, orderNo);

        UserInfoDto userInfoDto = BeanUtil.do2bo(userInfo, UserInfoDto.class);

        boolean flag= zlqbUserService.refreshUserInfoFromMongoDB(userInfoDto);
        if(!flag){
            incream.incrementAndGet();
            logger.info("刷新数据失败;请手动刷新该条数据 订单号-- {}",orderNo);
        }
        ZlqbUserDevice zlqbUserDevice =sealUserDevice(objectNode, orderNo);
        zlqbUserService.insertDeviceDto(zlqbUserDevice);
    }

    // ************************************私有方法*************************************

    private ZlqbOrderReview sealZlqbOrderReviewDto(String orderNo, ProductConfigVo configVo) {
        // 周期
        final Integer cycle = configVo.getCycle();
        // 期数
        final Integer periods = configVo.getPeriods();
        // 金额
        BigDecimal price = configVo.getPrice();
        ZlqbOrderReview review = new ZlqbOrderReview();
        review.setLoanPeriod(periods);
        review.setLoanTerms(cycle);
        review.setOrderNo(orderNo);
        review.setTotalPrincipal(price);
        review.setApproveAmount(price);
        review.setCreateTime(new Date());
        review.setExt1("300");
        // 审核中订单状态
        review.setStatus(ZlqbOrderReviewStatusEnum.REVIEW_IN.getValue());
        return review;

    }

    // 校验参数
    private boolean checkNodeParm(ObjectNode objectNode) {
        if (Objects.isNull(objectNode)) {
            return true;
        }
        for (String parm : nodeList) {
            if (Objects.isNull(objectNode.get(parm))) {
                logger.debug("出现空值情况的参数值是--》{}", parm);
                return true;
            }
        }
        return false;
    }

    private List<CuIcePersonDto> sealCuicePersonList(ObjectNode objectNode) {
        JsonNode basicInfo = objectNode.get(nodeList.get(0));
        List<CuIcePersonDto> cuIcePersonList = new LinkedList<>();
        CuIcePersonDto firstDto = new CuIcePersonDto("father", basicInfo.get("firstName").asText(),
                basicInfo.get("firstPhone").asText(), "1");
        CuIcePersonDto secondDto = new CuIcePersonDto("friend", basicInfo.get("secondName").asText(),
                basicInfo.get("secondPhone").asText(), "2");
        cuIcePersonList.add(firstDto);
        cuIcePersonList.add(secondDto);
        return cuIcePersonList;
    }

    private CuPlatformCustomerDto sealCuPlatformCustomerDto(ObjectNode objectNode) {
        JsonNode basicNode = objectNode.get(nodeList.get(0));
        String idCard = basicNode.get(parmList.get(9)).asText();
        // 平台客户信息表bean
        CuPlatformCustomerDto cuPlatformCustomerDto = new CuPlatformCustomerDto();
        cuPlatformCustomerDto.setAge(IdCardUtil.getAgeByIdCard(idCard));
        cuPlatformCustomerDto.setAppName(ZlqbUtil.getAppName());
        cuPlatformCustomerDto.setBirth(IdCardUtil.getBirthByIdCard(idCard));

        cuPlatformCustomerDto.setCertCode(idCard);
        cuPlatformCustomerDto.setCertType("1");

        cuPlatformCustomerDto.setCustomerId(ZlqbUtil.getCustomerId(idCard));
        int degree = basicNode.get("degree") == null ? 0 : basicNode.get("degree").asInt();
        parmIsDegree(cuPlatformCustomerDto, degree);
        cuPlatformCustomerDto.setEmail(basicNode.get("email") == null ? "" : basicNode.get("email").asText());
        cuPlatformCustomerDto.setMarket(ZlqbUtil.getAppMarket());
        cuPlatformCustomerDto.setPhoneNum(basicNode.get("phone").asText());
        cuPlatformCustomerDto.setName(basicNode.get("name").asText());
        cuPlatformCustomerDto
                .setHomeAddress(basicNode.get(parmList.get(10)) == null ? "" : basicNode.get(parmList.get(10)).asText());
        int isMarry = basicNode.get(parmList.get(11)) == null ? 5 : basicNode.get(parmList.get(11)).asInt();
        parmIsMarry(cuPlatformCustomerDto, isMarry);

        final int nature = objectNode.get(nodeList.get(1)).get(parmList.get(12)) == null ? 10
                : objectNode.get(nodeList.get(1)).get(parmList.get(12)).asInt();

        cuPlatformCustomerDto.setIndustryCode(ZlqbJobCategoryEnum.valueOfByCode(nature).getToCode());

        JsonNode identifyNode = objectNode.get(nodeList.get(2));

        cuPlatformCustomerDto.setExpirationDate(
                identifyNode.get(parmList.get(0)) == null ? "" : identifyNode.get(parmList.get(0)).asText());
        cuPlatformCustomerDto.setIssueInstitution(identifyNode.get(parmList.get(6)).asText());

        cuPlatformCustomerDto
                .setNation(identifyNode.get(parmList.get(1)) == null ? "" : identifyNode.get(parmList.get(1)).asText());
        cuPlatformCustomerDto.setContactAddress(
                identifyNode.get(parmList.get(7)) == null ? "" : identifyNode.get(parmList.get(7)).asText());

        cuPlatformCustomerDto.setRegisteredAddress(
                identifyNode.get(parmList.get(2)) == null ? "" : identifyNode.get(parmList.get(2)).asText());

        JsonNode companyInfo = objectNode.get(nodeList.get(1));
        if (Objects.nonNull(companyInfo.get(parmList.get(3)))
                && companyInfo.get(parmList.get(3)).get(parmList.get(4)) != null) {
            cuPlatformCustomerDto.setProvinceName(companyInfo.get(parmList.get(3)).get(parmList.get(4)).asText());
        }
        return cuPlatformCustomerDto;
    }

    private void parmIsDegree(CuPlatformCustomerDto cuPlatformCustomerDto, int degree) {
        switch (degree) {
            case 0:
            case 1:
                cuPlatformCustomerDto.setEducationId("6");
                break;
            case 2:
                cuPlatformCustomerDto.setEducationId("3");
                break;
            case 3:
                cuPlatformCustomerDto.setEducationId("2");
                break;
            case 4:
                cuPlatformCustomerDto.setEducationId("1");
                break;
            default:
                break;
        }
    }

    // 设置是否结婚情况
    private void parmIsMarry(CuPlatformCustomerDto cuPlatformCustomerDto, int isMarry) {
        switch (isMarry) {
            case 0:
                cuPlatformCustomerDto.setIfMarriage("SPINSTERHOOD");
                break;
            case 1:
                cuPlatformCustomerDto.setIfMarriage("MARRIED");
                break;
            case 2:
                cuPlatformCustomerDto.setIfMarriage("DIVORCED");
                break;
            case 3:
                cuPlatformCustomerDto.setIfMarriage("MARRIED");
                break;
            case 4:
                cuPlatformCustomerDto.setIfMarriage("WIDOWED");
                break;
            case 5:
                cuPlatformCustomerDto.setIfMarriage("SPINSTERHOOD");
                break;
            default:
                break;
        }
    }

    private CuCustomerCompanyDto sealCuCustomerCompanyDto(ObjectNode objectNode) {
        CuCustomerCompanyDto cuCustomerCompanyDto = new CuCustomerCompanyDto();
        final int type = objectNode.get(nodeList.get(0)).get(parmList.get(13)) == null ? 0
                : objectNode.get(nodeList.get(0)).get(parmList.get(13)).asInt();
        // 企业主
        if (ZlqbProfessionTypeEnum.BUSUBESS_OWNER.getValue() == type) {
            cuCustomerCompanyDto.setCompanyIndustry(ZlqbProfessionTypeEnum.getTextByValue(type));
            JsonNode companyInfo = objectNode.get(nodeList.get(1));
            cuCustomerCompanyDto.setCompanyName(
                    companyInfo.get(parmList.get(5)) == null ? "" : companyInfo.get(parmList.get(5)).asText());
            cuCustomerCompanyDto.setCompanyAddress(
                    companyInfo.get(parmList.get(8)) == null ? "" : companyInfo.get(parmList.get(8)).asText());
            JsonNode commanyCity = companyInfo.get(parmList.get(3));
            if (Objects.nonNull(commanyCity)) {
                cuCustomerCompanyDto.setProvince(
                        commanyCity.get(parmList.get(4)) == null ? "" : commanyCity.get(parmList.get(4)).asText());
                cuCustomerCompanyDto
                        .setCity(commanyCity.get(parmList.get(14)) == null ? "" : commanyCity.get(parmList.get(14)).asText());
                cuCustomerCompanyDto
                        .setArea(commanyCity.get(parmList.get(15)) == null ? "" : commanyCity.get(parmList.get(15)).asText());
            }
            cuCustomerCompanyDto.setProfessionId("SENIOR");
            return cuCustomerCompanyDto;

        }
        // 工薪族、自由职业、公务员、工人
        if (ZlqbProfessionTypeEnum.PROFESSIONAL.getValue() == type
                || ZlqbProfessionTypeEnum.WAGE_EARNERS.getValue() == type) {

            JsonNode companyInfo = objectNode.get(nodeList.get(1));
            cuCustomerCompanyDto.setCompanyIndustry(ZlqbProfessionTypeEnum.getTextByValue(type));
            cuCustomerCompanyDto.setCompanyName(companyInfo.get(parmList.get(5))==null?"":companyInfo.get(parmList.get(5)).asText());
            cuCustomerCompanyDto.setCompanyAddress(
                    companyInfo.get(parmList.get(8)) == null ? "" : companyInfo.get(parmList.get(8)).asText());
            JsonNode commanyCity = companyInfo.get(parmList.get(3));
            if (Objects.nonNull(commanyCity)) {
                cuCustomerCompanyDto.setProvince(
                        commanyCity.get(parmList.get(4)) == null ? "" : commanyCity.get(parmList.get(4)).asText());
                cuCustomerCompanyDto
                        .setCity(commanyCity.get(parmList.get(14)) == null ? "" : commanyCity.get(parmList.get(14)).asText());
                cuCustomerCompanyDto
                        .setArea(commanyCity.get(parmList.get(15)) == null ? "" : commanyCity.get(parmList.get(15)).asText());
            }
            if(companyInfo.get(parmList.get(16)) != null){
                cuCustomerCompanyDto
                        .setRemark("月薪" +  companyInfo.get(parmList.get(16)).asText().replace("null",""));
            }
            return cuCustomerCompanyDto;
        }
        // 学生
        if (ZlqbProfessionTypeEnum.STUDENT.getValue() == type) {
            cuCustomerCompanyDto.setCompanyIndustry("学生");
            return cuCustomerCompanyDto;
        }
        // 无业
        cuCustomerCompanyDto.setCompanyIndustry("无业");
        return cuCustomerCompanyDto;
    }

    private ApplicationDto sealApplicationDto(ObjectNode objectNode, String orderNo) {
        JsonNode basicNode = objectNode.get(nodeList.get(0));
        String idCard = basicNode.get(parmList.get(9)).asText();
        ApplicationDto applicationDto = new ApplicationDto();
        applicationDto.setAppChannel(ZlqbUtil.getChannel());
        applicationDto.setAppId(ZlqbUtil.getAppid());

        // 判断新老用户 客户标识(0:新客户 1：老客户)
        String appLevel = "0";
        // 校验此用户最后一笔订单是否已经结清，未结清怎还是传新用户
        CoreAuditUserInfoBo userInfoBo = zlqbUserService.getCoreAuditUserInfoBo(orderNo);
        RestResult<Void> restResult = orderUserCloudHystrixService.checkOlder(ZlqbUtil.getAppid(), userInfoBo == null ? "" : userInfoBo.getIdCard());
        if (restResult == null) {
            logger.error("校验用户是不是老用户失败 订单号{}", orderNo);
        }
        // 老用户的话
        else if (restResult.isSuccess()) {
            appLevel = "1";
        }
        applicationDto.setAppLevel(appLevel);
        applicationDto.setAppName(ZlqbUtil.getAppName());
        applicationDto.setAppSerialNumber(orderNo);
        applicationDto.setAppVersion(ZlqbUtil.getAppVersion());
        applicationDto.setCallBackUrlAuditStatus(ZlqbUtil.getAuditCallBackUrl());
        applicationDto.setTdBlackBox("0");
        applicationDto.setCertCode(idCard);
        applicationDto.setCertType("1");
        applicationDto.setCustomerId(ZlqbUtil.getCustomerId(idCard));
        //默认15天
        applicationDto.setDays(8);
        JsonNode deviceInfo = objectNode.get("deviceInfo");
        applicationDto.setLoanIp(deviceInfo.get("ip") == null ? "" : deviceInfo.get("ip").asText());
        String devicetype = deviceInfo.get("devicetype") == null ? "" : deviceInfo.get("devicetype").asText();
        logger.debug("该订单请求的设备类型 {}", devicetype);
        if (!"Android".equals(devicetype)) {
            applicationDto.setSource("1");
        } else {
            applicationDto.setSource("0");
        }
        applicationDto.setMobileWebsite("moxie");
        applicationDto.setName(basicNode.get("name").asText());
        applicationDto.setPhoneNum(basicNode.get("phone").asText());
        JsonNode zhimaoScoce = basicNode.get(parmList.get(17));
        applicationDto.setZhiMaScore(null==zhimaoScoce?"":zhimaoScoce.asText());
        return applicationDto;
    }

    // 封装用户的基本信息
    private ZlqbUserInfo sealUserInfoDto(ObjectNode objectNode, String orderNo) {
        JsonNode basicNode = objectNode.get(nodeList.get(0));
        if (Objects.isNull(basicNode)) {
            logger.error("此订单出现 basicInfo节点为空 orderNo--》{}", orderNo);
            return null;
        }
        ZlqbUserInfo userInfo = JacksonUtil.parseWithSnakeCase(basicNode, ZlqbUserInfo.class);
        logger.info("打印从mongodb中获取的数据信息-->{}",userInfo.toString());
        String idCard = basicNode.get(parmList.get(9)).asText();
        userInfo.setOrderNo(orderNo);
        userInfo.setUserId(ZlqbUtil.getCustomerId(idCard.toLowerCase()));
        userInfo.setAge(IdCardUtil.getAgeByIdCard(idCard));
        userInfo.setIdCard(idCard);
        userInfo.setProfesion(basicNode.get(parmList.get(13))==null?0:basicNode.get(parmList.get(13)).asInt());
        userInfo.setDebtAmount(basicNode.get("debtAmount")==null?new BigDecimal(0):new BigDecimal(basicNode.get("debtAmount").asInt()));
        userInfo.setApplyDate(basicNode.get("applyDate")==null?"":basicNode.get("applyDate").asText());
        userInfo.setZmScore(basicNode.get(parmList.get(17))==null?"":basicNode.get(parmList.get(17)).asText());
        userInfo.setLiveCity(basicNode.get("liveCity")==null?"":basicNode.get("liveCity").asText());
        userInfo.setHouseAddress(basicNode.get(parmList.get(10))==null?"":basicNode.get(parmList.get(10)).asText());
        userInfo.setPayBackSourceOption(basicNode.get("payBackSourceOption")==null?0:basicNode.get("payBackSourceOption").asInt());
        userInfo.setLoanTerms(basicNode.get("loanTerms")==null?"":basicNode.get("loanTerms").asInt()+"");
        userInfo.setIsMarry(basicNode.get(parmList.get(11))==null?5:(byte)basicNode.get(parmList.get(11)).asInt());
        userInfo.setLiveStyle(basicNode.get("liveStyle")==null?0:(byte)basicNode.get("liveStyle").asInt());
        JsonNode identifyNode = objectNode.get(nodeList.get(2));
        if (Objects.isNull(identifyNode)) {
            logger.error("此订单出现 identifyInfo 节点为空 orderNo--》{}", orderNo);
            return null;
        }
        // 身份证地址
        userInfo.setAddress(identifyNode.get(parmList.get(7)).asText());
        // 居住地址
        userInfo.setPermanentAddress(
                identifyNode.get(parmList.get(2)) == null ? "" : identifyNode.get(parmList.get(2)).asText());
        // 身份证有效日期
        userInfo.setValidDate(
                identifyNode.get(parmList.get(0)) == null ? "" : identifyNode.get(parmList.get(0)).asText());
        // 签发机构
        userInfo.setIssuedBy(
                identifyNode.get(parmList.get(6)) == null ? "" : identifyNode.get(parmList.get(6)).asText());
        userInfo.setConfidence(identifyNode.get(parmList.get(18))==null?"":identifyNode.get(parmList.get(18)).asText());
        userInfo.setPermanentAddress(identifyNode.get(parmList.get(2))==null?"":identifyNode.get(parmList.get(2)).asText());
        // 正面照片
        userInfo.setFrontFile(identifyNode.get(list.get(0)).asText());
        // 反面照片
        userInfo.setBackFile(identifyNode.get(list.get(1)).asText());
        // 手持照
        userInfo.setNatureFile(identifyNode.get(list.get(2)).asText());
        // 人脸识别
        userInfo.setFacePic(identifyNode.get(list.get(2)).asText());
        // 名族
        userInfo.setNature(identifyNode.get(parmList.get(1)) == null ? "" : identifyNode.get(parmList.get(1)).asText());
        final JsonNode compayInfoNode = objectNode.get(nodeList.get(1));
        if(Objects.nonNull(compayInfoNode)){
            userInfo.setCompanyName(compayInfoNode.get(parmList.get(5))==null?"":compayInfoNode.get(parmList.get(5)).asText());
            userInfo.setEmployedDate(compayInfoNode.get("employedDate")==null?"":compayInfoNode.get("employedDate").asText());
            userInfo.setCommanyCity(compayInfoNode.get(parmList.get(3))==null?"":compayInfoNode.get(parmList.get(3)).asText());
            userInfo.setCommanyType(compayInfoNode.get("commanyType")==null?(byte)10:(byte)compayInfoNode.get("commanyType").asInt());
            userInfo.setCommanyAddress(compayInfoNode.get(parmList.get(8))==null?"":compayInfoNode.get(parmList.get(8)).asText());
            userInfo.setCommanyNature(compayInfoNode.get(parmList.get(12))==null?0:(byte)compayInfoNode.get(parmList.get(12)).asInt());
            JsonNode commanyCity = compayInfoNode.get(parmList.get(3));
            if(Objects.nonNull(commanyCity)){
                userInfo.setAddressProvince(commanyCity.get(parmList.get(4)) == null ? "" : compayInfoNode.get(parmList.get(4)).asText());
                userInfo.setAddressCity(commanyCity.get(parmList.get(14)) == null ? "" : compayInfoNode.get(parmList.get(14)).asText());
                userInfo.setAddressArea(commanyCity.get(parmList.get(15)) == null ? "" : compayInfoNode.get(parmList.get(15)).asText());
            }
            userInfo.setIncome(compayInfoNode.get(parmList.get(16))==null?"":compayInfoNode.get(parmList.get(16)).asText().replace("null", ""));


        }
        userInfo.setReport("报告url链接:目前第三方未提供");
        userInfo.setIsRefresh((byte)1);

        return userInfo;
    }

    private ZlqbUserDevice sealUserDevice(ObjectNode objectNode, String orderNo) {
        JsonNode deviceNode = objectNode.get(nodeList.get(3));
        if(Objects.nonNull(deviceNode)){
            ZlqbUserDevice userDevice = JacksonUtil.parseWithSnakeCase(deviceNode, ZlqbUserDevice.class);
            userDevice.setOrderNo(orderNo);
            return userDevice;
        }
        return null;
    }

    // 封装紧急联系人信息
    private List<ZlqbLinkman> sealLinkManArray(ObjectNode objectNode, String orderNo) {
        JsonNode basicInfo = objectNode.get(nodeList.get(0));
        if (Objects.isNull(basicInfo)) {
            return Collections.emptyList();
        }
        String firstName = basicInfo.get("firstName").asText();
        String firstPhone = basicInfo.get("firstPhone").asText();
        String secondName = basicInfo.get("secondName").asText();
        String secondPhone = basicInfo.get("secondPhone").asText();
        List<ZlqbLinkman> newList = new ArrayList<>();
        ZlqbLinkman firstMan = new ZlqbLinkman(IdUtil.getNumberUuid(UuidPrefix.LINK_MAN), orderNo, firstName,
                firstPhone, "1", new Date(), "", "");
        ZlqbLinkman secondMan = new ZlqbLinkman(IdUtil.getNumberUuid(UuidPrefix.LINK_MAN), orderNo, secondName,
                secondPhone, "2", new Date(), "", "");
        newList.add(firstMan);
        newList.add(secondMan);
        return newList;
    }

    // 转化url地址
    private void handleNodeFile(JsonNode node, String parm, String orderNo) {
        ObjectNode newObj = (ObjectNode) node;
        String newNode = newObj.get(parm).asText();
        newObj.put(parm, handleUrl(newNode, orderNo));
    }

    private String handleUrl(String url, String orderNo) {
        String uploadUrl = "";
        try {
            uploadUrl = OSSFileUtil.uploadByUrl(ZlqbUtil.getAppid(), orderNo + "/photo", url);
        } catch (Exception ex) {
            logger.error("上传图片服务器异常;ordr--》{}", orderNo);
            throw new CalfException(ZlqbResultEnum.SAVE_ERROR.getMessage());
        }
        return uploadUrl;
    }

}
