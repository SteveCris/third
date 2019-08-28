package com.shangyong.thorder.dao;

import com.shangyong.thorder.entity.OrderAuditR;

public interface OrderAuditRMapper {
    int deleteByPrimaryKey(String auditId);

    int insert(OrderAuditR record);

    int insertSelective(OrderAuditR record);

    OrderAuditR selectByPrimaryKey(String auditId);

    int updateByPrimaryKeySelective(OrderAuditR record);

    int updateByPrimaryKey(OrderAuditR record);
}