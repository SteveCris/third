package com.shangyong.thzlqb;

import com.shangyong.thzlqb.service.PushService;
import com.shangyong.thzlqb.service.PushTwoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {


	@Autowired
	private PushTwoService pushService;

	@Test
	public void test() {
		pushService.pushTwo("1734186", null);

	}

}
