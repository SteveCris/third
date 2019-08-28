package com.shangyong.thbase.dao;

import org.apache.ibatis.annotations.Param;

import com.shangyong.thbase.entity.ThProductConfig;
import com.shangyong.thcore.vo.ProductConfigVo;

public interface ThProductConfigMapper {
    int deleteByPrimaryKey(String productId);

    int insert(ThProductConfig record);

    int insertSelective(ThProductConfig record);

    ThProductConfig selectByPrimaryKey(String productId);

    int updateByPrimaryKeySelective(ThProductConfig record);

    int updateByPrimaryKey(ThProductConfig record);

	ProductConfigVo getProductConfigVo(@Param("appid") String appid);
}