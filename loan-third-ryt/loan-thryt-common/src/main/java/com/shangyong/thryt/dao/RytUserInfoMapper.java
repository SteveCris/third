package com.shangyong.thryt.dao;

import org.apache.ibatis.annotations.Param;

import com.shangyong.thryt.bo.RytAuditUserInfoBo;
import com.shangyong.thryt.bo.RytUserInfoBo;
import com.shangyong.thryt.entity.RytUserInfo;

public interface RytUserInfoMapper {
    int deleteByPrimaryKey(String orderNo);

    int insert(RytUserInfo record);

    int insertSelective(RytUserInfo record);

    RytUserInfo selectByPrimaryKey(String orderNo);

    int updateByPrimaryKeySelective(RytUserInfo record);

    int updateByPrimaryKey(RytUserInfo record);

	RytUserInfoBo getUserInfoBo(@Param("orderNo") String orderNo);

	RytAuditUserInfoBo getRytAuditUserInfoBo(@Param("orderNo") String orderNo);
}