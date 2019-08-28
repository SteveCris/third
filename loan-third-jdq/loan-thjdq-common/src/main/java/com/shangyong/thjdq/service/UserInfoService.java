package com.shangyong.thjdq.service;

import com.shangyong.thjdq.dto.PushPhaseOneDto;
import com.shangyong.thjdq.entity.UserInfo;
import com.shangyong.thjdq.event.PushPhaseOneEvent;
import com.shangyong.thjdq.event.PushPhaseOtherInfoEvent;
import com.shangyong.thjdq.event.PushPhaseTwoEvent;
import com.shangyong.thjdq.vo.PushPhaseOneVo;
import com.shangyong.thjdq.vo.Response;

import java.util.List;

/**
 * 用户基础信息service
 * Created by zbb on 2019-03-13.
 */
public interface UserInfoService {

    /**
     * 借点钱基本信息记录
     * @return
     */
    Response<PushPhaseOneVo> pushPhaseOne(PushPhaseOneDto pushPhaseOneDto);

    /**
     * 保存一推基本信息
     */
    boolean pushPhaseOneDataAsync(PushPhaseOneEvent pushPhaseOneEvent);

    /**
     * 保存二推补充信息
     * @return
     */
    boolean pushPhaseTwoDataAsync(PushPhaseTwoEvent pushPhaseTwoEvent);

    /**
     * 保存用户其他信息
     * @return
     */
    boolean pushPhaseOtherInfoAsync(PushPhaseOtherInfoEvent pushPhaseOtherInfoEvent);

    /**
     * 根据身份证,手机查询是否存在user_info
     * @param idNumber
     * @param mobile
     * @return
     */
    UserInfo selectByIdNumberAndMobile(String idNumber, String mobile);

    /**
     * 根据身份证查询是否存在user_info
     * @param idNumber
     * @return
     */
    List<UserInfo> selectByIdNumber(String idNumber);

    /**
     * 根据借点钱订单号查询用户基本信息
     * @param jdqOrderId
     * @return
     */
    UserInfo selectByJdqOrderId(String jdqOrderId);

    /**
     * 查询未推送给风控系统的所有用户
     * @return
     */
    List<UserInfo> selectUnRiskUserInfo();

    /**
     * 查询推送中风控系统的所有用户
     * @return
     */
    List<UserInfo> selectNotCallBackRiskUserInfo();

    /**
     * 查询用户基本信息
     * @param userInfoId
     * @return
     */
    UserInfo selectLockByUserInfoId(String userInfoId);

    /**
     * 根据身份证号查询用户基本信息
     * @param idNumber
     * @return
     */
    UserInfo selectLockByIdNumber(String idNumber);

    /**
     * 根据风控流水号查询用户基本信息
     * @param idNumber
     * @return
     */
    UserInfo selectLockByAppSerialNumber(String idNumber);

    /**
     * 根据手机身份证MD5查询用户基本信息
     * @param phoneIdNumberMd5
     * @return
     */
    List<UserInfo> selectByPhoneIdNumberMd5(String phoneIdNumberMd5);

    /**
     * 更新用户信息为待审核状态
     * @param userInfoId
     * @param jdqOrderId
     * @param appSerialNumber
     * @return
     */
    boolean pushUserInfoToWaitStatus(String userInfoId, String jdqOrderId, String appSerialNumber);

    /**
     * 更新用户信息为审核初始状态
     * @param userInfoId
     * @param jdqOrderId
     * @return
     */
    boolean pushUserInfoToInitStatus(String userInfoId, String jdqOrderId);

    /**
     * 更新用户信息为重复身份证订单
     * @param userInfoId
     * @param jdqOrderId
     * @return
     */
    boolean pushUserInfoToRepeatStatus(String userInfoId, String jdqOrderId);

    /**
     * 根据身份证获取MD5值
     * @param idNumber
     * @return
     */
    List<UserInfo> selectUnionPhoneIdNumberMd5ByIdNumber(String idNumber);

    /**
     * 审核成功事件
     * @param userInfo
     * @return
     */
    boolean auditSuccess(UserInfo userInfo);

    /**
     * 审核失败事件
     * @param userInfo
     * @param msg
     * @return
     */
    boolean auditFailed(UserInfo userInfo, String msg);

}
