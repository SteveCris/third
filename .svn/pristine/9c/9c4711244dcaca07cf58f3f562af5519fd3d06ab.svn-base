package com.shangyong.thjdq.dao;

import com.shangyong.thjdq.entity.DeviceInfo;
import org.apache.ibatis.annotations.Param;

public interface DeviceInfoMapper {
    int deleteByPrimaryKey(String deviceInfoId);

    int insert(DeviceInfo record);

    int insertSelective(DeviceInfo record);

    DeviceInfo selectByPrimaryKey(String deviceInfoId);

    int updateByPrimaryKeySelective(DeviceInfo record);

    int updateByPrimaryKey(DeviceInfo record);

    /**
     * 根据用户信息id查询设备信息
     * @param userInfoId
     * @return
     */
    DeviceInfo selectByUserInfoId(@Param("userInfoId") String userInfoId);
}