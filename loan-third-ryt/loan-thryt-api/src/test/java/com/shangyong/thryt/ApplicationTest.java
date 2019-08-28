package com.shangyong.thryt;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

	static CountDownLatch countDownLatch = new CountDownLatch(1);

	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void test() throws InterruptedException {

		while (true) {
			int seconds=RandomUtils.nextInt(0, 10);
			System.out.println(seconds);
			TimeUnit.SECONDS.sleep(seconds);
			new Demo(restTemplate).test();
		}
		
		
	}

	static class Demo {

		public Demo(RestTemplate restTemplate) {
			super();
			this.restTemplate = restTemplate;
		}

		private RestTemplate restTemplate;

		static int maxCount = 600;

		static CyclicBarrier cyclicBarrier = new CyclicBarrier(maxCount);

		public void test() {
			push();
			for (int i = 0; i < maxCount; i++) {

				new Thread(() -> {
					try {
						cyclicBarrier.await();
						push();

					} catch (BrokenBarrierException | InterruptedException e) {

					}
				}).start();

			}

		}

		private void push() {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
			httpHeaders.add("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
			HttpEntity<Void> httpEntity = new HttpEntity<>(httpHeaders);
			String url = "http://test.pinganedai.vip:18011/thryt/test/order";
			ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
		}

	}

}
