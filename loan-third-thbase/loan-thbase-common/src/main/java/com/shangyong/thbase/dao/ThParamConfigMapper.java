package com.shangyong.thbase.dao;

import com.shangyong.thbase.entity.ThParamConfig;
import com.shangyong.thcore.vo.ParamConfigVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ThParamConfigMapper {
    int deleteByPrimaryKey(String paramConfigId);

    int insert(ThParamConfig record);

    int insertSelective(ThParamConfig record);

    ThParamConfig selectByPrimaryKey(String paramConfigId);

    int updateByPrimaryKeySelective(ThParamConfig record);

    int updateByPrimaryKey(ThParamConfig record);

    ParamConfigVo getParamConfigVo(@Param("appid") String appid, @Param("paramKey") String paramKey);

    List<ParamConfigVo> getParamConfigVoList(@Param("appid") String appid, @Param("paramKeys") String[] paramKeys);
}