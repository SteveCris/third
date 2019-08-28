package com.shangyong.thzlqb.dao;

import com.shangyong.thzlqb.dto.UserInfoDto;
import com.shangyong.thzlqb.entity.ZlqbUserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ZlqbUserInfoMapper {
    int deleteByPrimaryKey(String orderNo);

    int insert(ZlqbUserInfo record);

    int insertSelective(ZlqbUserInfo record);

    ZlqbUserInfo selectByPrimaryKey(String orderNo);

    int updateByPrimaryKeySelective(ZlqbUserInfo record);

    int updateByPrimaryKey(ZlqbUserInfo record);

    ZlqbUserInfo getUserInfoByOrderNo(@Param("orderNo") String orderNo);

    Long selectCount(@Param("isRefresh") byte isRefresh);

    List<ZlqbUserInfo> selectAllPageInfoByLimit(@Param("isRefresh")byte isRefresh, @Param("startNum")int startNum, @Param("pageSize")int pageSize);

    int refreshUserInfoFromMongoDB(UserInfoDto userInfoDto);
}