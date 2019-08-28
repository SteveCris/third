package com.shangyong.thzlqb.dao;

import com.shangyong.thzlqb.entity.ZlqbBandCardInfo;
import org.apache.ibatis.annotations.Param;

public interface ZlqbBandCardInfoMapper {
    int deleteByPrimaryKey(String bindId);

    int insert(ZlqbBandCardInfo record);

    int insertSelective(ZlqbBandCardInfo record);

    ZlqbBandCardInfo selectByPrimaryKey(String bindId);

    int updateByPrimaryKeySelective(ZlqbBandCardInfo record);

    int updateByPrimaryKey(ZlqbBandCardInfo record);

    String getBankCardByIdCard(@Param("idCard") String idCard);
}