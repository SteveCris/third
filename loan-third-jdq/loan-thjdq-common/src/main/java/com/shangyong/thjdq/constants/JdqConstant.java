package com.shangyong.thjdq.constants;

/**
 * 借点钱参数配置
 * Created by ybds on 2019-03-15.
 */
public class JdqConstant {

    /** 点号 分隔用 **/
    public static final String SPLIT_SPOT = "\\.";

    /** 点号 **/
    public static final String SPOT = ".";

    /** 加密用的sign */
    public static final String MERCHANT_KEY = "SHANGYONGRISK";

    /** 风控魔蝎运营商task_type **/
    public static final String MOXIE_REPORT_TASK_TYPE = "09001";

    /** 风控魔蝎运营商mongo_collection **/
    public static final String MONGO_JDQ_COLLECTION_NAME = "jdq_mongo_moxie_report";

    /**
     * 每次一次性从broker里面取的待消费的消息的个数
     */
    public static final int RABBITMQ_DEFAULT_PREFETCH_COUNT = 1;

    /**
     * 每个listener并发消费者个数
     */
    public static final int RABBITMQ_DEFAULT_CONCURRENT = 75;

    /**
     * 每个listener并发消费者个数
     */
    public static final int RABBITMQ_DEFAULT_CONCURRENT_2 = 30;

}
