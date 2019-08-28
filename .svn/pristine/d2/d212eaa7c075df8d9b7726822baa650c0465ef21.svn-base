package com.shangyong.thbase.dao;

import org.apache.ibatis.annotations.Param;

import com.shangyong.thbase.entity.ThCenterConfig;
import com.shangyong.thcore.vo.CenterConfigVo;

public interface ThCenterConfigMapper {
    int deleteByPrimaryKey(String centerConfigId);

    int insert(ThCenterConfig record);

    int insertSelective(ThCenterConfig record);

    ThCenterConfig selectByPrimaryKey(String centerConfigId);

    int updateByPrimaryKeySelective(ThCenterConfig record);

    int updateByPrimaryKey(ThCenterConfig record);

	CenterConfigVo getCenterConfigVo(@Param("appid") String appid);
}