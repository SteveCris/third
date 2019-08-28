package com.shangyong.thorder;

import java.util.concurrent.TimeUnit;

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
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.shangyong.center.result.vo.RestResultTokenModel;
import com.shangyong.common.entity.RestResult;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BatchCancelOrder {

	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void test() throws InterruptedException {

		String[] orderIds=("THO126446587751694336," + 
				"THO126463694212169728," + 
				"THO126821194631479296," + 
				"THO129365814687039488," + 
				"THO129366091578212352," + 
				"THO129367716472553472," + 
				"THO130814313010036736," + 
				"THO131521164664111104," + 
				"THO131532005966872576," + 
				"THO131534560377700352," + 
				"THO131540230799884288," + 
				"THO131549594533232640," + 
				"THO131881161679962112," + 
				"THO131881269385494528," + 
				"THO131881398347759616," + 
				"THO131882276236558336," + 
				"THO131882276395941888," + 
				"THO131882533359976448," + 
				"THO131882543002681344," + 
				"THO131882788663066624," + 
				"THO131883162056785920," + 
				"THO131883662214955008," + 
				"THO131883789046513664," + 
				"THO131884037915541504," + 
				"THO131884167448231936," + 
				"THO131884318971658240," + 
				"THO131884417357447168," + 
				"THO131884920443240448," + 
				"THO131885936458858496," + 
				"THO131886189325058048," + 
				"THO131886308598480896," + 
				"THO131886563838656512," + 
				"THO131886685653827584," + 
				"THO131886815962464256," + 
				"THO131886836493582336," + 
				"THO131887340464373760," + 
				"THO131887442897666048," + 
				"THO131887442918637568," + 
				"THO131888069614764032," + 
				"THO131888448280723456," + 
				"THO131888615285325824," + 
				"THO131888620305907712," + 
				"THO131888962045214720," + 
				"THO131889458176851968," + 
				"THO131889464246009856," + 
				"THO131891139400695808," + 
				"THO131891214558429184," + 
				"THO131891478384345088," + 
				"THO131891499775295488," + 
				"THO131891620508336128," + 
				"THO131891635909820416," + 
				"THO131892879634202624," + 
				"THO131892979601244160," + 
				"THO131893754926727168," + 
				"THO131893766662389760," + 
				"THO131893983776342016," + 
				"THO131894991407546368," + 
				"THO131895126233448448," + 
				"THO131895367619837952," + 
				"THO131896024917606400," + 
				"THO131896405638774784," + 
				"THO131896569199853568," + 
				"THO131897059191029760," + 
				"THO131897777377509376," + 
				"THO131897928280178688," + 
				"THO131897937306320896," + 
				"THO131898438492094464," + 
				"THO131898511619784704," + 
				"THO131898790826213376," + 
				"THO131899573672083456," + 
				"THO131899958541418496," + 
				"THO131900144848207872").split(",");
		
	
		for (int i = 0; i < orderIds.length; i++) {
			cancelOrder(orderIds[i]);
			TimeUnit.SECONDS.sleep(1);
		}
		
		System.out.println(1);
		
		
	}

	private void cancelOrder(String orderId) throws RestClientException {
		String url = "https://gt.symy666.vip/thorder/order/o6NAAJqaxgAsBqYK/cancel?token=uDPWetDT6ppbyH7hzrrEAnpLKQKCNd8k&orderId="+orderId;
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		httpHeaders.add("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);

		HttpEntity<Void> httpEntity = new HttpEntity<>(httpHeaders);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
		if (response.getStatusCodeValue() == 200) {
			System.out.println(response.getBody());
		}else {
			System.out.println(response);
		}
	}

}
