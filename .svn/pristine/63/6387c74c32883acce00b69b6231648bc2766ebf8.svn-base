package com.shangyong.thzlqb.dao;

import com.shangyong.thzlqb.entity.ZlqbOrderReview;
import org.apache.ibatis.annotations.Param;

public interface ZlqbOrderReviewMapper {
    int deleteByPrimaryKey(String orderNo);

    int insert(ZlqbOrderReview record);

    int insertSelective(ZlqbOrderReview record);

    ZlqbOrderReview selectByPrimaryKey(String orderNo);

    int updateByPrimaryKeySelective(ZlqbOrderReview record);

    int updateByPrimaryKey(ZlqbOrderReview record);

    int updateOrderReviewDto(@Param("orderNo")String orderNo,@Param("status")int status,@Param("approveDate")String approveDate);
}