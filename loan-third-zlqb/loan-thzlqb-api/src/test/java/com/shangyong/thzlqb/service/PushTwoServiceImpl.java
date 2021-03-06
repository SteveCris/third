package com.shangyong.thzlqb.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.shangyong.loan.autoconfigure.SleuthLoggerExclude;
import com.shangyong.loan.ext.exception.CalfException;
import com.shangyong.loan.ext.util.JsonNodeUtil;
import com.shangyong.thzlqb.contants.CoreContants;
import com.shangyong.thzlqb.enums.ZlqbResultEnum;

@Service
public class PushTwoServiceImpl implements PushTwoService {

	private Logger logger=LoggerFactory.getLogger(PushTwoServiceImpl.class);
	
	@Autowired
	private MongodbService mongodbService;


	@Autowired
	private PushClient client;

	protected static final List<String> nodeList = Arrays.asList("basicInfo", "companyInfo", "identifyInfo",
			"deviceInfo", "address_book", "operator");
	
	
	@SleuthLoggerExclude(excludeInput = true, excludeOut = false)
	@Override
	public boolean pushTwo(String orderNo, ObjectNode node) {
		//ObjectNode objectNode= mongodbService.getData(CoreContants.PUSH_COLLECTION, orderNo);
		if (checkNodeParm(node)) {
			logger.error("订单推审核单元时mongoDB数据查询时为空 orderNo --》{}", orderNo);
			throw new CalfException(ZlqbResultEnum.MONGODB_SEARCH_NULL.getMessage());
		}
		final JsonNode baseInfo = node.get(nodeList.get(0));

		final JsonNode companyInfo = node.get(nodeList.get(1));

		final JsonNode indentInfo = node.get(nodeList.get(2));

		final JsonNode deviceInfo = node.get(nodeList.get(3));

		ObjectNode data = JsonNodeUtil.data(); //
	             data.set("orderInfo",
						 JsonNodeUtil.data()
				.put("orderNo", orderNo) //
				.put("applyAmount", new BigDecimal(1000))//
				.put("termUnit", 1)//
				.put("applyTerm", 7));
		data.set("supplementInfo",JsonNodeUtil.data()
				.put("marriageStatus",isMary(baseInfo))//结婚情况
				.put("email",null==baseInfo.get("email")?"":baseInfo.get("email").asText())  //邮箱
				.put("loanReason",isLoadReson(baseInfo))  //借款理由 1.短期周转 2.购物、3.装修、4.买房、5.买 、6.旅游 10.其它
				.put("position",1) //默认
				.put("industryCategory",isIndustryCategory(companyInfo))
				.put("companyName",null==companyInfo.get("companyName")?"":companyInfo.get("companyName").asText())
				.put("companyPhone","")
				.put("companyAddress",null==companyInfo.get("commanyCity")?"":companyInfo.get("commanyCity").asText())
				.put("residentialAddress",null==baseInfo.get("liveCity")?"":baseInfo.get("liveCity").asText())  //现居住地址
				.put("immediateFamilyRelationship",1)//直系亲属关系
				.put("emergencyContactRelationship",5) //紧急联系人关系
				.put("immediateFamilyName",baseInfo.get("firstName").asText())  //
				.put("immediateFamilyPhoneNo",baseInfo.get("firstPhone").asText())
				.put("emergencyContactName",baseInfo.get("firstName").asText())
				.put("emergencyContactPhoneNo",baseInfo.get("firstPhone").asText())
				.put("creditCard","")
				.put("qq",null==baseInfo.get("qqNumber")?"":baseInfo.get("qqNumber").asText())
				.put("wechat",baseInfo.get("phone").asText())); //一般微信号 是手机号
		data.set("ocrInfo",JsonNodeUtil.data() //
				.put("ocrName",baseInfo.get("name").asText()) //姓名
				.put("ocrId",baseInfo.get("idCard").asText())  //身份证
				.put("ocrPeriod",null==indentInfo.get("validDate")?"":indentInfo.get("validDate").asText()) //
				.put("ocrAddress",indentInfo.get("address").asText()) //
				.put("ocrSex","")
				.put("ocrNation",null==indentInfo.get("nation")?"":indentInfo.get("nation").asText())
				.put("ocrGov",null==indentInfo.get("issuedBy")?"":indentInfo.get("issuedBy").asText())
				.put("idCardFront",indentInfo.get("frontFile").asText())
				.put("idCardBack",indentInfo.get("backFile").asText())); //
		data.set("faceInfo",JsonNodeUtil.data()
				.put("livingImage",indentInfo.get("natureFile").asText())
				.put("status",1));
		data.set("deviceInfo",JsonNodeUtil.data()
				.put("platformId",null==deviceInfo.get("devicetype")?"android":deviceInfo.get("devicetype").asText().toLowerCase())
				.put("deviceNo",null==deviceInfo.get("imei")?"":deviceInfo.get("imei").asText())
				.put("deviceModel","")
				.set("contacts",(ArrayNode) node.get("address_book")));
		//return client.pushData(data, "http://risk.qingdaoboyuan.cn/data/recOtherInfo");
	return true;
	}

	private int isIndustryCategory(JsonNode companyInfo) {
		if(Objects.nonNull(companyInfo.get("commanyType"))){
			int commanyType = companyInfo.get("commanyType").asInt();
			switch (commanyType){
				case 0:
					return 3;
				case 1:
				case 2:
				case 3:
				case 4:
				case 7:
				case 9:
					return  10;
				case 8:
					return 1;

			}
		}

		return  10;
	}

	//借款理由
	private int isLoadReson(JsonNode baseInfo) {
		if(null !=baseInfo.get("loanPurpose")){
			int loanPurpose = baseInfo.get("loanPurpose").asInt();
			switch (loanPurpose){
				case 1:
					return 2;
				case 2:
					return 3;
				case 3:
					return 6;
				case 4:
					return 5;
				case 5:
				case 6:
				case 7:
				case 8:
					return 10;

			}
		}

		return 10;
	}
   //是否结婚 ；如果未传 默认 为0
	private int  isMary(JsonNode baseInfo) {
		int isMary =0;
		if(null!=baseInfo.get("isMarry")){
			int marry = baseInfo.get("isMarry").asInt();
			return  0 == marry || 2 == marry ? 0 : 1;
		}
		return isMary;
	}



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

}
