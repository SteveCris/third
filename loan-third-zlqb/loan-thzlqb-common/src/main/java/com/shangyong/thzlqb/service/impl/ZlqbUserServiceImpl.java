package com.shangyong.thzlqb.service.impl;

import com.shangyong.backend.fegin.BlacklistCloudHystrixService;
import com.shangyong.common.entity.RestResult;
import com.shangyong.loan.ext.util.IdCardUtil;
import com.shangyong.loan.ext.util.IdUtil;
import com.shangyong.loan.ext.util.JsonNodeUtil;
import com.shangyong.loan.ext.util.ResultUtil;
import com.shangyong.rest.feign.BaseUserCloudHystrixService;
import com.shangyong.rest.feign.OrderCloudHystrixService;
import com.shangyong.thcore.dto.CheckUserInfoDto;
import com.shangyong.thcore.vo.BaseUserInfoVo;
import com.shangyong.thzlqb.contants.IdCardConstant;
import com.shangyong.thzlqb.contants.UuidPrefix;
import com.shangyong.thzlqb.dao.ZlqbBandCardInfoMapper;
import com.shangyong.thzlqb.dao.ZlqbCheckRMapper;
import com.shangyong.thzlqb.dao.ZlqbLinkmanMapper;
import com.shangyong.thzlqb.dao.ZlqbUserInfoMapper;
import com.shangyong.thzlqb.entity.ZlqbBandCardInfo;
import com.shangyong.thzlqb.entity.ZlqbCheckR;
import com.shangyong.thzlqb.entity.ZlqbLinkman;
import com.shangyong.thzlqb.entity.ZlqbUserInfo;
import com.shangyong.thzlqb.enums.*;
import com.shangyong.thzlqb.utils.BeanUtil;
import com.shangyong.thzlqb.zlqb.utils.ZlqbUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.shangyong.thzlqb.bo.CoreAuditUserInfoBo;
import com.shangyong.thzlqb.service.ZlqbUserService;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class ZlqbUserServiceImpl implements ZlqbUserService {

	private Logger logger = LoggerFactory.getLogger(ZlqbUserServiceImpl.class);

	@Autowired
	private OrderCloudHystrixService orderCloudHystrixService;

	@Autowired
	private BaseUserCloudHystrixService baseUserCloudHystrixService;

	@Autowired
	private BlacklistCloudHystrixService blacklistCloudHystrixService;


	@Autowired
	private ZlqbCheckRMapper mapper;

	@Autowired
	private ZlqbUserInfoMapper userInfoMapper;

	@Autowired
	private ZlqbLinkmanMapper linkmanMapper;

	@Autowired
	private ZlqbBandCardInfoMapper bandCardInfoMapper;

	@Override
	public CoreAuditUserInfoBo getCoreAuditUserInfoBo(String orderNo) {
		ZlqbUserInfo userInfo =userInfoMapper.getUserInfoByOrderNo(orderNo);
		CoreAuditUserInfoBo do2bo = BeanUtil.do2bo(userInfo, CoreAuditUserInfoBo.class);
		if(Objects.nonNull(do2bo)){
			do2bo.setUserMobile(userInfo.getPhone());
			do2bo.setUserName(userInfo.getName());
		}
		return do2bo;
	}

	@Override
	public JsonNode processCheckUser(ObjectNode request) {
		long startTime = System.currentTimeMillis();

		String idCard = request.get("idCard").asText();

		logger.info("处理助力钱包HITDB 开始时间 --》{},idCard--》{}", startTime, idCard);

		ZlqbCheckR r = initInsertHitDB(idCard);

        return buildResultAndInserDB(ZlqbIsOnTuEnum.IS_STOCK_N.getValue(), ZlqbIsLoanEnum.IS_LOAN_N.getValue(),
                ZlqbRejectReasonEnum.BLACKLIST.getValue(), r);

		/*String hitBlack = isOneHitBlack(idCard);

		r.setRemark(hitBlack);

		if(!StringUtils.isEmpty(hitBlack)){

			return buildResultAndInserDB(ZlqbIsOnTuEnum.IS_STOCK_N.getValue(), ZlqbIsLoanEnum.IS_LOAN_N.getValue(),
					ZlqbRejectReasonEnum.BLACKLIST.getValue(), r);
		}
		// 判断是否命中黑名单
		RestResult<Boolean> restResult = blacklistCloudHystrixService.blacklistQueryIsExist("", idCard);
		boolean isSuccess= ResultUtil.checkAndGet(restResult);
		if (isSuccess) {
			return buildResultAndInserDB(ZlqbIsOnTuEnum.IS_STOCK_Y.getValue(), ZlqbIsLoanEnum.IS_LOAN_N.getValue(),
					ZlqbRejectReasonEnum.BLACKLIST.getValue(), r);
		}

		CheckUserInfoDto checkUserInfoDto = new CheckUserInfoDto();
		checkUserInfoDto.setType(IdCardTypeEnum.ID_CARD_NONE.getValue());
		checkUserInfoDto.setIdentityNo(idCard);
		RestResult<BaseUserInfoVo> baseUserResult = baseUserCloudHystrixService.checkAndSearch(ZlqbUtil.getAppid(),
				checkUserInfoDto);

		BaseUserInfoVo baseUserInfoVo= ResultUtil.checkAndGet(baseUserResult);

		// 说明用户信息不存在，直接返回
		if (!baseUserInfoVo.isIfExistUser()) {
			// #新用户
			return buildResultAndInserDB(ZlqbIsOnTuEnum.IS_STOCK_N.getValue(), ZlqbIsLoanEnum.IS_LOAN_Y.getValue(),
					ZlqbRejectReasonEnum.VOID.getValue(), r);
		}
		// 说明用户信息存在，是隔离用户
		if (baseUserInfoVo.isIfQuarantine()) {
			// #老用户隔离
			return buildResultAndInserDB(ZlqbIsOnTuEnum.IS_STOCK_Y.getValue(), ZlqbIsLoanEnum.IS_LOAN_N.getValue(),
					ZlqbRejectReasonEnum.REJECTED.getValue(), r);
		}

		// 校验在途
		RestResult<Void> result = orderCloudHystrixService.orderOnWayCheck(ZlqbUtil.getAppid(),
				baseUserInfoVo.getIdentityNo());
		if(Objects.nonNull(request)){
			if(result.isSuccess()){
				return buildResultAndInserDB(ZlqbIsOnTuEnum.IS_STOCK_Y.getValue(), ZlqbIsLoanEnum.IS_LOAN_N.getValue(),
						ZlqbRejectReasonEnum.ONTU.getValue(), r);
			}else{
				return buildResultAndInserDB(ZlqbIsOnTuEnum.IS_STOCK_Y.getValue(), ZlqbIsLoanEnum.IS_LOAN_Y.getValue(),
						ZlqbRejectReasonEnum.VOID.getValue(), r);
			}
		}
		 return null;*/

	}

	@Override
	public void insertDto(ZlqbUserInfo userinfo) {
		if(Objects.isNull(userinfo)){
			return;
		}
		userInfoMapper.insertSelective(userinfo);
	}

	@Override
	public void saveBatchLinkMan(List<ZlqbLinkman> linkmanList) {
		//一条条插入：不进行批量插入； 事务较小
		if(CollectionUtils.isEmpty(linkmanList)){
			return;
		}
		linkmanList.forEach(saveDto->{
			linkmanMapper.insertSelective(saveDto);
		});
	}

	@Override
	public ZlqbUserInfo selectOne(String orderNo) {
		return userInfoMapper.selectByPrimaryKey(orderNo);
	}

	@Override
	public void saveUserBandCardDto(ZlqbBandCardInfo info) {
		bandCardInfoMapper.insertSelective(info);
	}
	@Override
	public String getBankCardByIdCard(String idCard) {
	    if(StringUtils.isEmpty(idCard)){
	        return "";
        }
		return bandCardInfoMapper.getBankCardByIdCard(idCard);
	}



	private String isOneHitBlack(String idCard) {

		//校验身份证卡号是否是有效的
		if(!IdCardUtil.validateIdCard(idCard)) {
			return idCard+"身份证卡号格式不对";
		}
		final int age = IdCardUtil.getAgeByIdCard(idCard);
		if(age<20 || age>40){
			return idCard+"用户身份证"+"年龄"+age+"不符合";
		}
		String value = idCard.trim().substring(0, 4);
		boolean existArea = IdCardConstant.isExistArea(value);
		if(existArea){
			return idCard+"用户地区不符合;地区编码"+value;
		}
		return "黑名单--用户不能准入";
	}

	// 初始化zlqbHITDB record

	private ZlqbCheckR initInsertHitDB(String idCard) {
		ZlqbCheckR r = new ZlqbCheckR();

		String checkId = IdUtil.getNumberUuid(UuidPrefix.CHECK_ID);

		r.setCheckId(checkId);

		r.setIdCard(idCard);

		r.setCreateTime(new Date());

		r.setRemark("黑名单--用户不能准入");

		return r;
	}

	private JsonNode buildResultAndInserDB(int isStock, int isCanLoan, int reason, ZlqbCheckR r) {
		// 设置相应的字段值
		r.setStock(isStock);
		r.setCanLoan(isCanLoan);
		r.setRejectReason(reason == 0 ? null : reason);
		mapper.insertSelective(r);
		// 返回字段
		return JsonNodeUtil.data().put("isStock", isStock)//
				.put("isCanLoan", isCanLoan)//
				.put("rejectReason", reason == 0 ? null : reason);
	}

}
