package com.shangyong.thzlqb.contants;

public class CoreContants {
	
	public static final String APPID = "appid";

	public static final String PATH_APPID = "/{" + APPID + "}";
	
	public static final String COLLECTION_ID="_id";
	
	public static final String PUSH_COLLECTION="zlqb_push";

	//系统之间的业务隔离key  防止key污染
	public static final String REDIS_BUSINESS_KEY="zlqb_business_key:";

	public static final String REDIS_ORDER_ONE_KEY="zlqb_orderfirstpush_key:";

	public static final String REDIS_ORDER_TWO_KEY="zlqb_ordersecondpush_key:";

	public static final String REDIS_ORDER_THREE_KEY="zlqb_orderthreepush_key:";

	public static final String REDIS_ORDER_FOURSUCCESS_KEY="zlqb_orderfourreviewsuccess_key:";

	public static final String REDIS_ORDER_FOURFAILURE_KEY="zlqb_orderfourreviewfailure_key:";
}
