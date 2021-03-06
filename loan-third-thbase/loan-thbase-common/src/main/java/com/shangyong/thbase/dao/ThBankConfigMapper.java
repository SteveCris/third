package com.shangyong.thbase.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shangyong.thbase.entity.ThBankConfig;
import com.shangyong.thcore.vo.BankConfigVo;

public interface ThBankConfigMapper {
	int deleteByPrimaryKey(String bankId);

	int insert(ThBankConfig record);

	int insertSelective(ThBankConfig record);

	ThBankConfig selectByPrimaryKey(String bankId);

	int updateByPrimaryKeySelective(ThBankConfig record);

	int updateByPrimaryKey(ThBankConfig record);

	List<BankConfigVo> listThBankConfigVo(@Param("appid") String appid, @Param("cardType") int cardType);

	int countBank(@Param("appid") String appid, @Param("cardType") int cardType, @Param("bankCode") String bankCode);
}