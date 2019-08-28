package com.shangyong.thbase.dao;

import org.apache.ibatis.annotations.Param;

import com.shangyong.thbase.entity.ThBankMatch;
import com.shangyong.thcore.vo.BankMatchVo;

public interface ThBankMatchMapper {
	int deleteByPrimaryKey(String matchId);

	int insert(ThBankMatch record);

	int insertSelective(ThBankMatch record);

	ThBankMatch selectByPrimaryKey(String matchId);

	int updateByPrimaryKeySelective(ThBankMatch record);

	int updateByPrimaryKey(ThBankMatch record);

	BankMatchVo getBankMatchVo(@Param("appid") String appid, @Param("cardNoPrefix") String cardNoPrefix);
}