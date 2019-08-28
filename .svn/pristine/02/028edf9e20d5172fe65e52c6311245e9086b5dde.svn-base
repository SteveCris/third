package com.shangyong.thorder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.shangyong.thorder.service.EquityCardService;
import com.shangyong.thorder.service.TaskService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private EquityCardService equityCardService;
	
	@Test
	public void test() {
	
		
		System.out.println(equityCardService.getOrderEquity("", ""));
	}

}
