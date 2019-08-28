package com.shangyong.thryt.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
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
import com.shangyong.loan.autoconfigure.redis.BatchRedisTemplate;
import com.shangyong.rest.feign.OrderCloudHystrixService;
import com.shangyong.thryt.bo.RytOrderBo;
import com.shangyong.thryt.contants.CoreContants;
import com.shangyong.thryt.contants.UuidPrefix;
import com.shangyong.thryt.entity.RytLinkman;
import com.shangyong.thryt.entity.RytUserInfo;
import com.shangyong.thryt.enums.RytResultEnum;
import com.shangyong.thryt.event.RytPushEvent;
import com.shangyong.thryt.exception.CalfException;
import com.shangyong.thryt.self.enums.EducationEnum;
import com.shangyong.thryt.self.enums.JobCategoryEnum;
import com.shangyong.thryt.self.enums.JobTypeEnum;
import com.shangyong.thryt.self.enums.LinkmanEnum;
import com.shangyong.thryt.self.enums.MarriageEnum;
import com.shangyong.thryt.self.enums.WorkingAgeEnum;
import com.shangyong.thryt.send.Sender;
import com.shangyong.thryt.service.MongodbService;
import com.shangyong.thryt.service.RytConsumerService;
import com.shangyong.thryt.service.RytOrderService;
import com.shangyong.thryt.service.RytUserService;
import com.shangyong.thryt.utils.IdCardUtil;
import com.shangyong.thryt.utils.IdUtil;
import com.shangyong.thryt.utils.JacksonUtil;
import com.shangyong.thryt.utils.JsonNodeUtil;
import com.shangyong.thryt.utils.OSSFileUtil;
import com.shangyong.thryt.utils.RytUtil;

@Service
public class RytConsumerServiceImpl implements RytConsumerService {

	private Logger logger = LoggerFactory.getLogger(RytConsumerServiceImpl.class);

	@Autowired
	private RytOrderService rytOrderService;

	@Autowired
	private RytUserService rytUserService;

	@Autowired
	private MongodbService mongodbService;

	@Autowired
	private ReportCloudHystrixService reportCloudHystrixService;

	@Autowired
	private UploadCloudHystrixService uploadCloudHystrixService;

	@Autowired
	private ApplicationCloudHystrixService applicationCloudHystrixService;

	@Autowired
	private OrderCloudHystrixService orderCloudHystrixService;

	@Autowired
	private Sender sender;

	@Autowired
	private BatchRedisTemplate batchRedisTemplate;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean processRytPushTime(RytPushEvent rytPushEvent) {
		String orderNo = rytPushEvent.getOrderNo();
		if (!rytOrderService.updateOrderStatus(orderNo, 10, 0, false, null)) {
			return rytOrderService.checkStatus(orderNo, 10);
		}

		TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
			@Override
			public void afterCommit() {
				sender.sendMq("/third", "ex.push.ryt.fall", "push.rKey", rytPushEvent, true);
			}
		});

		ObjectNode objectNode = mongodbService.getData(CoreContants.PUSH_COLLECTION, orderNo);
		// 用户认证数据
		ObjectNode userAuthNode = (ObjectNode) objectNode.get("user_auth");
		// 身份证数据
		ArrayNode photoArrayNode = (ArrayNode) userAuthNode.get("photos");
		photoArrayNode.forEach(jsonNode -> {
			ObjectNode photoNode = (ObjectNode) jsonNode;
			String downUrl = photoNode.get("photo_url").asText();
			String uploadUrl = OSSFileUtil.uploadByUrl(RytUtil.getAppid(),
					orderNo + "/photo" + photoNode.get("photo_type").asText(), downUrl);
			// 设置新值
			photoNode.put("photo_url", uploadUrl);
		});
		// 运营商数据
		String fileDownUrl = userAuthNode.get("carrier_file").asText();
		String fileUploadUrl = OSSFileUtil.uploadByUrl(RytUtil.getAppid(), orderNo + "/cfile", fileDownUrl);
		userAuthNode.put("carrier_file", fileUploadUrl);

		// 用户补充信息
		ObjectNode userAddInfoNode = (ObjectNode) objectNode.get("user_add_info");
		// 人脸识别数据
		ArrayNode faceArrayNode = (ArrayNode) userAddInfoNode.get("face_data");
		ArrayNode newFaceArrayNode = JsonNodeUtil.arrayData();
		faceArrayNode.forEach(jsonNode -> {
			TextNode textNode = (TextNode) jsonNode;
			String downUrl = textNode.asText();
			String uploadUrl = OSSFileUtil.uploadByUrl(RytUtil.getAppid(), orderNo + "/face", downUrl);
			newFaceArrayNode.add(uploadUrl);
		});
		userAddInfoNode.set("face_data", newFaceArrayNode);

		// 运营商报告
		String reportDownUrl = userAddInfoNode.get("carrier_report").asText();
		String reportUploadUrl = OSSFileUtil.uploadByUrl(RytUtil.getAppid(), orderNo + "/creport", reportDownUrl);
		userAddInfoNode.put("carrier_report", reportUploadUrl);

		if (mongodbService.updateData(CoreContants.PUSH_COLLECTION, orderNo, objectNode)) {
			return true;
		} else {
			logger.error("更新mongdb失败，订单号：{}", orderNo);
			throw new CalfException(RytResultEnum.MONGODB_UPDATE_ERROR);
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean processRytPushFall(RytPushEvent rytPushEvent) {
		String orderNo = rytPushEvent.getOrderNo();
		if (!rytOrderService.updateOrderStatus(orderNo, 20, 10, false, null)) {
			return rytOrderService.checkStatus(orderNo, 20);
		}
		TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
			@Override
			public void afterCommit() {
				sender.sendMq("/third", "ex.push.ryt.audit", "push.rKey", rytPushEvent, true);
			}
		});
		// 获取mongo信息
		ObjectNode objectNode = mongodbService.getData(CoreContants.PUSH_COLLECTION, orderNo);
		// 获取用户基本信息
		ObjectNode userInfoNode = (ObjectNode) objectNode.get("user_info");

		// 获得联系人列表
		ArrayNode linkmanArrayNode = (ArrayNode) userInfoNode.get("linkman");

		// 获取用户认证信息
		ObjectNode userAuthNode = (ObjectNode) objectNode.get("user_auth");

		// 身份证数据
		ArrayNode photoArrayNode = (ArrayNode) userAuthNode.get("photos");

		// 获取用户补充信息
		ObjectNode userAddInfoNode = (ObjectNode) objectNode.get("user_add_info");

		// 获取设备信息
		String platform;
		if (userAddInfoNode.has("device")) {
			ObjectNode deviceNode = (ObjectNode) userAddInfoNode.get("device");
			if (deviceNode.has("basic")) {
				platform = deviceNode.get("basic").get("platform").asText();
			} else {
				platform = "";
			}
		} else {
			platform = "";
		}

		// #保存用户信息
		RytUserInfo rytUserInfo = JacksonUtil.parseWithSnakeCase(userInfoNode, RytUserInfo.class);

		// 重复点击
		if (batchRedisTemplate.isRepeatClick(RytUtil.getChannel() + ":" + rytUserInfo.getIdCard(),
				TimeUnit.DAYS.toSeconds(1))) {
			logger.error("存在重复推单情况,orderNo:{},userMobile:{},idCard:{}", orderNo, rytUserInfo.getUserMobile(),
					rytUserInfo.getIdCard());
			throw new CalfException(RytResultEnum.CLICK_REPEAT);
		}
		rytUserInfo.setOrderNo(orderNo);
		rytUserInfo.setUserId(RytUtil.getCustomerId(rytUserInfo.getIdCard().toLowerCase()));
		rytUserInfo.setCreateTime(new Date());

		// 设置照片数据
		photoArrayNode.forEach(jsonNode -> {
			ObjectNode photoNode = (ObjectNode) jsonNode;
			String photoUrl = photoNode.get("photo_url").asText();
			int photoType = photoNode.get("photo_type").asInt();
			if (1 == photoType) {
				rytUserInfo.setIdCardPic(photoUrl);
			} else if (2 == photoType) {
				rytUserInfo.setIdCardPicThe(photoUrl);
			} else if (9 == photoType) {
				rytUserInfo.setIdCardPicHand(photoUrl);
			}
		});

		// 设置人脸数据
		ArrayNode faceArrayNode = (ArrayNode) userAddInfoNode.get("face_data");
		rytUserInfo.setFacePic(faceArrayNode.get(0).asText());
		rytUserInfo.setFacePicOther(faceArrayNode.has(1) ? faceArrayNode.get(1).asText() : null);
		// 设置os版本
		rytUserInfo.setOsVersion(platform);

		if (!rytUserService.save(rytUserInfo)) {
			throw new CalfException(RytResultEnum.SAVE_ERROR);
		}

		// #保存联系人信息
		List<RytLinkman> rytLinkmanList = new LinkedList<>();
		linkmanArrayNode.forEach(jsonNode -> {
			ObjectNode linkmanNode = (ObjectNode) jsonNode;
			RytLinkman rytLinkman = new RytLinkman();
			rytLinkman.setMobile(linkmanNode.get("mobile").asText());
			rytLinkman.setName(linkmanNode.get("name").asText());
			rytLinkman.setType(linkmanNode.get("type").asText());
			rytLinkman.setCreateTime(new Date());
			rytLinkman.setLinkId(IdUtil.getNumberUuid(UuidPrefix.RYT_LINK));
			rytLinkman.setOrderNo(orderNo);
			// 联系人枚举
			rytLinkmanList.add(rytLinkman);
		});

		if (!rytUserService.batchSave(rytLinkmanList)) {
			throw new CalfException(RytResultEnum.SAVE_ERROR);
		}

		RytOrderBo rytOrderBo = rytOrderService.getRytOrderBo(orderNo);
		// 推送订单至待审核
		RestResult<Void> toWaitResult = orderCloudHystrixService.orderToWaitAudit(RytUtil.getAppid(),
				rytOrderBo.getOrderId());
		if (toWaitResult == null || !toWaitResult.isSuccess()) {
			throw new CalfException(RytResultEnum.REMOTE_ERROR);
		}

		return true;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean processRytPushAudit(RytPushEvent rytPushEvent) {
		String orderNo = rytPushEvent.getOrderNo();
		if (!rytOrderService.updateOrderStatus(orderNo, 30, 20, false, null)) {
			return rytOrderService.checkStatus(orderNo, 30);
		}
		// 获取订单信息
		RytOrderBo rytOrderBo = rytOrderService.getRytOrderBo(orderNo);

		ObjectNode objectNode = mongodbService.getData(CoreContants.PUSH_COLLECTION, orderNo);

		// 0新用户1老用户 == 目前永远是新用户
		String appLevel = "0";
		// 获取用户基本信息
		ObjectNode userInfoNode = (ObjectNode) objectNode.get("user_info");
		// 获得身份证号
		String idCard = userInfoNode.get("id_card").asText();
		// 获得联系人列表
		ArrayNode linkmanArrayNode = (ArrayNode) userInfoNode.get("linkman");
		// 获取用户补充信息
		ObjectNode userAddInfoNode = (ObjectNode) objectNode.get("user_add_info");
		// 获取用户补充信息
		String platform;
		if (userAddInfoNode.has("device")) {
			ObjectNode deviceNode = (ObjectNode) userAddInfoNode.get("device");
			if (deviceNode.has("basic")) {
				platform = deviceNode.get("basic").get("platform").asText();
			} else {
				platform = "";
			}
		} else {
			platform = "";
		}

		// 获取用户补充信息
		ObjectNode workInfoNode = (ObjectNode) userAddInfoNode.get("work_info");
		// 通讯录信息
		ArrayNode contactsArrayNode = (ArrayNode) userAddInfoNode.get("contacts");
		// 行业枚举
		JobCategoryEnum jobCategoryEnum = JobCategoryEnum.valueOfByCode(userAddInfoNode.get("job_category").asText());
		// 职业身份枚举
		JobTypeEnum jobTypeEnum = JobTypeEnum.valueOfByCode(workInfoNode.get("job_types").asText());
		// 婚姻状况枚举
		MarriageEnum marriageEnum = MarriageEnum.valueOfByCode(userAddInfoNode.get("marriage_state").asText());
		// 教育情况枚举
		EducationEnum educationEnum = EducationEnum.valueOfByCode(userAddInfoNode.get("educations").asText());

		AddressBookDto addressBookDto = new AddressBookDto();

		String contactsjson = contactsArrayNode.toString();
		contactsjson = contactsjson.replaceAll("\"name\"", "\"contactName\"")
				.replaceAll("\"phone\"", "\"contactPhone\"").replaceAll(" ", "");
		//// 上传通讯录
		addressBookDto.setAppName(RytUtil.getAppName());
		addressBookDto.setCustomerId(RytUtil.getCustomerId(idCard));
		addressBookDto.setJsonInfo(JacksonUtil.parse(contactsjson, ArrayNode.class));
		RestResult<String> uploadAddressBookResult = uploadCloudHystrixService.uploadAddressBook(addressBookDto);
		if (uploadAddressBookResult == null || !uploadAddressBookResult.isSuccess()) {
			logger.debug("通讯录：{}", addressBookDto);
			logger.error("上传通讯录失败，result：{}", uploadAddressBookResult);
			throw new CalfException(RytResultEnum.REMOTE_ERROR);
		}

		//// 上传通讯录报文
		DataReportDto dataReportDto = new DataReportDto();
		dataReportDto.setAppSerialNumber(orderNo);
		dataReportDto.setTaskType("09001");
		dataReportDto.setJsonInfo(JsonNodeUtil.getNode(userAddInfoNode.get("carrier_report").asText()).toString());
		RestResult<Boolean> operatorInfoUploadResult = reportCloudHystrixService.operatorInfoUpload(dataReportDto);
		if (operatorInfoUploadResult == null || !operatorInfoUploadResult.isSuccess()) {
			logger.debug("运营商报文：{}", dataReportDto);
			logger.error("上运营商失败，result：{}", operatorInfoUploadResult);
			throw new CalfException(RytResultEnum.REMOTE_ERROR);
		}

		//// 上传申请单对象

		// 创建申请单信息
		ApplicationAllDto applicationAllDto = new ApplicationAllDto();

		// 设置上传通讯录 时 返回 bu_thridpart_report 表中的id
		applicationAllDto.setThirdPartAddressBookId(uploadAddressBookResult.getData().getBody());

		// 平台申请单对象bean
		applicationAllDto.setApplicationBean(
				buildApplication(orderNo, rytOrderBo, appLevel, userInfoNode, userAddInfoNode, platform));

		// 平台客户所属公司信息bean
		applicationAllDto.setCuCustomerCompany(buildCustomerCompany(workInfoNode, jobCategoryEnum, jobTypeEnum));

		// 平台客户信息表bean
		applicationAllDto.setCuPlatformCustomer(
				buildPlatformCustomer(userInfoNode, idCard, jobCategoryEnum, marriageEnum, educationEnum));
		// 平台客户紧急联系人bean
		applicationAllDto.setCuIcePersonList(buildIcePersonList(linkmanArrayNode));

		// 平台客户客户人脸识别评分记录bean 人脸不一定用到
		FaceRecognitionScoreDto faceRecognitionScoreDto = new FaceRecognitionScoreDto();
		applicationAllDto.setFaceRecognitionScore(faceRecognitionScoreDto);
		// 平台客户其它账号信息bean
		applicationAllDto.setCuCustomerOtherList(new ArrayList<>());
		// 芝麻 行业信息
		applicationAllDto.setIndustryDetailsList(new ArrayList<>());
		RestResult<Void> result = applicationCloudHystrixService.saveApplication(applicationAllDto);
		if (result == null || !result.isSuccess()) {
			logger.error("上传申请单失败，result：{}", applicationAllDto);
			throw new CalfException(RytResultEnum.REMOTE_ERROR);
		} else {
			return true;
		}

	}

	/**
	 * @param userInfoNode
	 * @param idCard
	 * @param jobCategoryEnum
	 * @param marriageEnum
	 * @param educationEnum
	 * @return
	 */
	private CuPlatformCustomerDto buildPlatformCustomer(ObjectNode userInfoNode, String idCard,
			JobCategoryEnum jobCategoryEnum, MarriageEnum marriageEnum, EducationEnum educationEnum) {
		// 平台客户信息表bean
		CuPlatformCustomerDto cuPlatformCustomerDto = new CuPlatformCustomerDto();
		cuPlatformCustomerDto.setAge(IdCardUtil.getAgeByIdCard(idCard));
		cuPlatformCustomerDto.setAppName(RytUtil.getAppName());
		// cuPlatformCustomerDto.setBankCard(null);
		// cuPlatformCustomerDto.setBankName(null);
		cuPlatformCustomerDto.setBirth(IdCardUtil.getBirthByIdCard(idCard));

		cuPlatformCustomerDto.setCertCode(idCard);
		cuPlatformCustomerDto.setCertType("1");
		cuPlatformCustomerDto.setContactAddress(userInfoNode.get("address").asText());
		cuPlatformCustomerDto.setCustomerId(RytUtil.getCustomerId(idCard));
		cuPlatformCustomerDto.setEducationId(educationEnum.getToCode());
		// cuPlatformCustomerDto.setEmail(null);
		cuPlatformCustomerDto.setExpirationDate(userInfoNode.get("valid_date").asText());
		cuPlatformCustomerDto.setHomeAddress(userInfoNode.get("address").asText());
		cuPlatformCustomerDto.setIfMarriage(marriageEnum.getToCode());
		cuPlatformCustomerDto.setIndustryCode(jobCategoryEnum.getToCode());
		cuPlatformCustomerDto.setIssueInstitution(userInfoNode.get("issued_by").asText());
		// cuPlatformCustomerDto.setLiveTime(null);
		cuPlatformCustomerDto.setMarket(RytUtil.getAppMarket());
		cuPlatformCustomerDto.setName(userInfoNode.get("user_name").asText());
		cuPlatformCustomerDto.setNation(userInfoNode.has("race") ? userInfoNode.get("race").asText() : "");
		cuPlatformCustomerDto.setPhoneNum(userInfoNode.get("user_mobile").asText());
		cuPlatformCustomerDto.setProvinceName(userInfoNode.get("province").asText());
		cuPlatformCustomerDto.setRegisteredAddress(userInfoNode.get("id_card_address").asText());
		// cuPlatformCustomerDto.setRegisterTime(null);
		// cuPlatformCustomerDto.setRemark(null);
		return cuPlatformCustomerDto;
	}

	/**
	 * @param linkmanArrayNode
	 * @return
	 */
	private List<CuIcePersonDto> buildIcePersonList(ArrayNode linkmanArrayNode) {
		List<CuIcePersonDto> cuIcePersonList = new LinkedList<>();
		linkmanArrayNode.forEach(jsonNode -> {
			ObjectNode linkmanNode = (ObjectNode) jsonNode;
			CuIcePersonDto cuIcePersonDto = new CuIcePersonDto();
			cuIcePersonDto.setPhoneNum(linkmanNode.get("mobile").asText());
			cuIcePersonDto.setTrueName(linkmanNode.get("name").asText());
			// 联系人枚举
			LinkmanEnum linkmanEnum = LinkmanEnum.valueOfByCode(linkmanNode.get("type").asText());
			cuIcePersonDto.setType(linkmanEnum.getToCode());
			cuIcePersonList.add(cuIcePersonDto);
		});
		return cuIcePersonList;
	}

	/**
	 * @param workInfoNode
	 * @param jobCategoryEnum
	 * @param jobTypeEnum
	 */
	private CuCustomerCompanyDto buildCustomerCompany(ObjectNode workInfoNode, JobCategoryEnum jobCategoryEnum,
			JobTypeEnum jobTypeEnum) {
		// 平台客户所属公司信息bean
		CuCustomerCompanyDto cuCustomerCompanyDto = new CuCustomerCompanyDto();
		// 企业主
		if (JobTypeEnum.JOBTYPEENUM1 == jobTypeEnum) {
			// cuCustomerCompanyDto.setArea(null);
			// cuCustomerCompanyDto.setCity(null);
			// cuCustomerCompanyDto.setCompanyAddress(null);
			cuCustomerCompanyDto.setCompanyIndustry(jobCategoryEnum.getToName());
			cuCustomerCompanyDto.setCompanyName(workInfoNode.get("enterprise_name").asText());
			cuCustomerCompanyDto.setCompanyTel(workInfoNode.get("colleague_phone").asText());
			// cuCustomerCompanyDto.setLat(null);
			// cuCustomerCompanyDto.setLng(null);
			// cuCustomerCompanyDto.setProfessionId(null);
			// cuCustomerCompanyDto.setProvince(null);
			// cuCustomerCompanyDto.setStreetAddress(null);
			// cuCustomerCompanyDto.setWorkingHours(null);
			// cuCustomerCompanyDto.setWorkPhoto(null);
		}
		// 个体户
		else if (JobTypeEnum.JOBTYPEENUM2 == jobTypeEnum) {
			cuCustomerCompanyDto.setArea(workInfoNode.get("corporate_district").asText());
			cuCustomerCompanyDto.setCity(workInfoNode.get("corporate_city").asText());
			cuCustomerCompanyDto.setCompanyAddress(workInfoNode.get("corporate_address").asText());
			cuCustomerCompanyDto.setCompanyIndustry(jobCategoryEnum.getToName());
			cuCustomerCompanyDto.setCompanyName(workInfoNode.get("sole_name").asText());
			// cuCustomerCompanyDto.setCompanyTel(null);
			// cuCustomerCompanyDto.setLat(null);
			// cuCustomerCompanyDto.setLng(null);
			// cuCustomerCompanyDto.setProfessionId(null);
			cuCustomerCompanyDto.setProvince(workInfoNode.get("corporate_province").asText());
			// cuCustomerCompanyDto.setStreetAddress(null);
			// cuCustomerCompanyDto.setWorkingHours(null);
			// cuCustomerCompanyDto.setWorkPhoto(null);
		}
		// 工薪族、自由职业、公务员、工人
		else if (JobTypeEnum.JOBTYPEENUM3 == jobTypeEnum || JobTypeEnum.JOBTYPEENUM5 == jobTypeEnum
				|| JobTypeEnum.JOBTYPEENUM6 == jobTypeEnum || JobTypeEnum.JOBTYPEENUM7 == jobTypeEnum) {

			WorkingAgeEnum workingAgeEnum = WorkingAgeEnum.valueOfByCode(workInfoNode.get("job_types").asText());

			cuCustomerCompanyDto.setArea(workInfoNode.get("company_district").asText());
			cuCustomerCompanyDto.setCity(workInfoNode.get("company_city").asText());
			cuCustomerCompanyDto.setCompanyAddress(workInfoNode.get("company_address").asText());
			cuCustomerCompanyDto.setCompanyIndustry(jobCategoryEnum.getToName());
			cuCustomerCompanyDto.setCompanyName(workInfoNode.get("company_name").asText());
			cuCustomerCompanyDto.setCompanyTel(workInfoNode.get("company_tel").asText());
			// cuCustomerCompanyDto.setLat(null);
			// cuCustomerCompanyDto.setLng(null);
			// cuCustomerCompanyDto.setProfessionId(null);
			cuCustomerCompanyDto.setProvince(workInfoNode.get("company_province").asText());
			// cuCustomerCompanyDto.setStreetAddress(null);
			cuCustomerCompanyDto.setWorkingHours(workingAgeEnum.getName());
			// cuCustomerCompanyDto.setWorkPhoto(null);
		}
		// 学生
		else if (JobTypeEnum.JOBTYPEENUM4 == jobTypeEnum) {
			cuCustomerCompanyDto.setCompanyIndustry("学生");
		}
		// 无业
		else {
			cuCustomerCompanyDto.setCompanyIndustry("无业");
		}

		return cuCustomerCompanyDto;
	}

	/**
	 * @param orderNo
	 * @param rytOrderBo
	 * @param appLevel
	 * @param userInfoNode
	 * @param userAddInfoNode
	 * @param platform
	 * @return
	 */
	private ApplicationDto buildApplication(String orderNo, RytOrderBo rytOrderBo, String appLevel,
			ObjectNode userInfoNode, ObjectNode userAddInfoNode, String platform) {

		ApplicationDto applicationDto = new ApplicationDto();
		applicationDto.setAppChannel(RytUtil.getChannel());
		applicationDto.setAppId(RytUtil.getAppid());
		applicationDto.setAppLevel(appLevel);
		applicationDto.setAppName(RytUtil.getAppName());
		applicationDto.setAppSerialNumber(orderNo);
		applicationDto.setAppVersion(RytUtil.getAppVersion());
		applicationDto.setCallBackUrlAuditStatus(RytUtil.getAuditCallBackUrl());
		applicationDto.setTdBlackBox("0");
		// applicationDto.setCallBackUrlInfoRecollect(null);
		applicationDto.setCertCode(userInfoNode.get("id_card").asText());
		applicationDto.setCertType("1");
		applicationDto.setCustomerId(RytUtil.getCustomerId(userInfoNode.get("id_card").asText()));
		applicationDto.setDays(rytOrderBo.getLoanTerm());
		// applicationDto.setJxlUseToken(null);
		applicationDto.setLoanIp(userAddInfoNode.get("ip").asText());
		applicationDto.setMobileWebsite("moxie");
		applicationDto.setName(userInfoNode.get("user_name").asText());
		applicationDto.setPhoneNum(userInfoNode.get("user_mobile").asText());
		// applicationDto.setProductQuota(null);
		if ("iphone".equalsIgnoreCase(platform)) {
			applicationDto.setSource("1");
		} else {
			applicationDto.setSource("0");
		}

		// applicationDto.setTdBlackBox(null);
		// applicationDto.setZhiMaOpenId(null);
		// applicationDto.setZhiMaScore(null);
		return applicationDto;
	}

}
