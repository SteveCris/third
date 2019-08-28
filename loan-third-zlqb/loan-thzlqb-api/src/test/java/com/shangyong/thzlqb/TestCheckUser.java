package com.shangyong.thzlqb;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.shangyong.loan.ext.util.JsonNodeUtil;
import com.shangyong.thzlqb.zlqb.utils.AesUtil;
import com.shangyong.thzlqb.zlqb.utils.ZlqbUtil;

public class TestCheckUser {

	public static void main(String[] args) {
		ObjectNode node=JsonNodeUtil.data()
				.put("idCard", "412702198208237417");
		String request = AesUtil.encrypt(node.toString(), ZlqbUtil.getThirdAesKey());
		System.out.println(request);
	}
}
