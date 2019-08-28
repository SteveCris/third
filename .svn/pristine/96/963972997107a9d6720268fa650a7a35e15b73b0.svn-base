package com.shangyong.thryt.service;

import java.util.List;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.shangyong.thryt.bo.RytAuditUserInfoBo;
import com.shangyong.thryt.bo.RytUserInfoBo;
import com.shangyong.thryt.entity.RytLinkman;
import com.shangyong.thryt.entity.RytUserInfo;

public interface RytUserService {

	/**
	 * 获得用户信息
	 * 
	 * @param orderNo
	 * @return
	 */
	RytUserInfoBo getUserInfo(String orderNo);

	/**
	 * 获得认证用户数据
	 * 
	 * @param orderNo
	 * @return
	 */
	RytAuditUserInfoBo getRytAuditUserInfoBo(String orderNo);

	/**
	 * 校验接口
	 * 0,0 融易推老用户
	 * 1,1 新用户
	 * 0,2 合作方老用户
	 * 
	 * @param mobileHidden
	 * @param idCardHidden
	 * @param userName
	 * @param md5
	 * @return
	 */
	ObjectNode checkUser(String mobileHidden, String idCardHidden, String userName, String md5);

	/**
	 * 创建校验记录
	 * @param data
	 * @param result
	 * @return
	 */
	boolean createCheckRecord(ObjectNode data,ObjectNode result);
	
	/**
	 * 批量保存
	 * 
	 * @param rytLinkmanList
	 * @return
	 */
	boolean batchSave(List<RytLinkman> rytLinkmanList);

	/**
	 * 保存用户信息
	 * 
	 * @param rytUserInfo
	 * @return
	 */
	boolean save(RytUserInfo rytUserInfo);

}
