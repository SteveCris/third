package com.shangyong.thbase.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.shangyong.loan.autoconfigure.SleuthLoggerExclude;
import com.shangyong.thbase.contants.CoreContants;
import com.shangyong.thbase.contants.RedisPrefix;
import com.shangyong.thbase.dao.ThBankConfigMapper;
import com.shangyong.thbase.dao.ThBankMatchMapper;
import com.shangyong.thbase.service.BaseBankService;
import com.shangyong.thcore.vo.BankConfigVo;
import com.shangyong.thcore.vo.BankMatchVo;

@Service
public class BaseBankServiceImpl implements BaseBankService {

	@Autowired
	private ThBankConfigMapper thBankConfigMapper;

	@Autowired
	private ThBankMatchMapper thBankMatchMapper;

	@SleuthLoggerExclude(excludeInput = false, excludeOut = true)
	@Cacheable(key = "'" + RedisPrefix.CONFIG_BANK
			+ "'+#appid+':'+#cardType", value = "COMMON#600", unless = "#result==null")
	@Override
	public List<BankConfigVo> listThBankConfigVo(String appid, int cardType) {
		List<BankConfigVo> list = thBankConfigMapper.listThBankConfigVo(appid, cardType);
		if (list.isEmpty()) {
			list = thBankConfigMapper.listThBankConfigVo(CoreContants.DEFAULT_KEY, cardType);
		}
		return list;
	}

	@SleuthLoggerExclude(excludeInput = false, excludeOut = true)
	@Cacheable(key = "'" + RedisPrefix.CONFIG_CHECK
			+ "'+#appid+':'+#bankCode", value = "COMMON#600", unless = "#result==null")
	@Override
	public boolean checkBank(String appid, int cardType, String bankCode) {
		int count = thBankConfigMapper.countBank(appid,cardType, bankCode);
		if (count > 0) {
			return true;
		} else {
			return thBankConfigMapper.countBank(CoreContants.DEFAULT_KEY,cardType, bankCode) > 0;
		}
	}

	@SleuthLoggerExclude(excludeInput = false, excludeOut = true)
	@Cacheable(key = "'" + RedisPrefix.CONFIG_MATCH
			+ "'+#appid+':'+#cardNoPrefix", value = "COMMON#600", unless = "#result==null")
	@Override
	public BankMatchVo getBankMatchVo(String appid, String cardNoPrefix) {
		return thBankMatchMapper.getBankMatchVo(null, cardNoPrefix);
	}

}
