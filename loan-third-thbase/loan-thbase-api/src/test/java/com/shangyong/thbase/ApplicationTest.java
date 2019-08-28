package com.shangyong.thbase;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.shangyong.thbase.service.BaseUserService;
import com.shangyong.thcore.dto.CheckUserInfoDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

	@Autowired
	BaseUserService baseUserService;

	@Test
	public void test() {
		String signMd5=DigestUtils.md5Hex("13122053988"+"620522198908140319");
		CheckUserInfoDto checkUserInfoDto = new CheckUserInfoDto();
		checkUserInfoDto.setIdentityNoPrefix("62052219890");
		checkUserInfoDto.setMobilePrefix("13122");
		checkUserInfoDto.setSignMd5(signMd5);
		
		baseUserService.checkAndGetUserInfo(checkUserInfoDto);
		System.out.println(1);

	}

}
