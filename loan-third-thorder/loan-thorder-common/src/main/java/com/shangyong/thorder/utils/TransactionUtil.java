package com.shangyong.thorder.utils;

import org.springframework.transaction.interceptor.TransactionAspectSupport;

public class TransactionUtil {

	private TransactionUtil() {

	}

	public static void rollback() {
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	}
}
