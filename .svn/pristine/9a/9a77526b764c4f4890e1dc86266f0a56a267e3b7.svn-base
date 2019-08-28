package com.shangyong.thjdq.dao;

import com.shangyong.thjdq.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserInfoMapper {
    int deleteByPrimaryKey(String userInfoId);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(String userInfoId);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    /**
     * 根据身份证和手机查询是否存在user_info
     */
    UserInfo selectByIdNumberAndMobile(@Param("idNumber") String idNumber, @Param("mobile") String mobile);

    /**
     * 根据身份证查询是否存在user_info
     */
    List<UserInfo> selectByIdNumber(@Param("idNumber") String idNumber);

    /**
     * 根据借点钱订单号查询用户基本信息
     * @param jdqOrderId
     * @return
     */
    UserInfo selectByJdqOrderId(@Param("jdqOrderId") String jdqOrderId);

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
     * @return
     */
    UserInfo selectLockByUserInfoId(@Param("userInfoId")String userInfoId);

    /**
     * 根据身份证查询用户基本信息
     * @return
     */
    UserInfo selectLockByIdNumber(@Param("idNumber")String idNumber);

    /**
     * 根据风控流水单号查询用户基本信息
     * @return
     */
    UserInfo selectLockByAppSerialNumber(@Param("appSerialNumber")String appSerialNumber);

    /**
     * 根据手机身份证MD5查询用户基本信息
     * @return
     */
    List<UserInfo> selectByPhoneIdNumberMd5(@Param("phoneIdNumberMd5")String phoneIdNumberMd5);

    /**
     * 根据用户本地订单查询用户基本信息
     * @param localOrderId
     * @return
     */
    UserInfo selectByLocalOrderId(@Param("localOrderId") String localOrderId);

    /**
     * 根据身份证获取MD5值
     * @param idNumber
     * @return
     */
    List<UserInfo> selectUnionPhoneIdNumberMd5ByIdNumber(@Param("idNumber")String idNumber);

    /**
     * 根据借点钱订单号更新用户其他信息
     * @param userInfo
     * @return
     */
    int updateOtherInfoByJdqOrderId(@Param("userInfo") UserInfo userInfo);

}