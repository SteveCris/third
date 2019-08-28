package com.shangyong.thorder.dao;

import org.apache.ibatis.annotations.Param;

import com.shangyong.thcore.bo.OrderBorrowBo;
import com.shangyong.thcore.vo.OrderBorrowVo;
import com.shangyong.thorder.bo.OrderBorrowInsuranceBo;
import com.shangyong.thorder.entity.OrderBorrowApply;

public interface OrderBorrowApplyMapper {
    int deleteByPrimaryKey(String applyId);

    int insert(OrderBorrowApply record);

    int insertSelective(OrderBorrowApply record);

    OrderBorrowApply selectByPrimaryKey(String applyId);

    int updateByPrimaryKeySelective(OrderBorrowApply record);

    int updateByPrimaryKey(OrderBorrowApply record);
    
    OrderBorrowVo getOrderBorrowVo(@Param("appid") String appid, @Param("orderId") String orderId);
    
    OrderBorrowBo getOrderBorrowBoByAO(@Param("appid") String appid, @Param("orderId") String orderId);

    OrderBorrowBo getOrderBorrowBo(@Param("applyId") String applyId);
    
    OrderBorrowInsuranceBo getOrderBorrowInsuranceBo(@Param("appid") String appid, @Param("orderId") String orderId);
}

