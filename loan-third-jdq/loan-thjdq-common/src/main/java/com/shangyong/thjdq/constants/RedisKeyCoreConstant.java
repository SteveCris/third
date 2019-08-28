package com.shangyong.thjdq.constants;

/**
 * redis key值常量配置类
 * Created by zbb on 2019-03-14.
 */
public class RedisKeyCoreConstant {

    /**
     * 前缀
     */
    public static final String PREFIX = "loan-third-core:";

    /**
     * 借点钱-推送用户信息keys
     */
    public static final class JDQ_USER {
        public static final String PREFIX = RedisKeyCoreConstant.PREFIX + "jdq_user:";
        // 用户检测
        public static final String PUSH_PHASE_CHECK_USER_REPEAT = PREFIX + "push_phase_check_user_repeat:";
        // 添加user_info
        public static final String PUSH_PHASE_ONE_REPEAT = PREFIX + "push_phase_one_repeat:";
        // 保存一推信息实体类
        public static final String PUSH_PHASE_ONE_DTO = PREFIX + "push_phase_one_dto:";
        // 保存一推魔蝎分析报文
        public static final String PUSH_PHASE_ONE_MOXIE_REPORT_DTO = PREFIX + "push_phase_one_moxie_report_dto:";
        // 二推补充信息重复key
        public static final String PUSH_PHASE_TWO_REPEAT = PREFIX + "push_phase_two_repeat:";
        // 保存二推信息实体类
        public static final String PUSH_PHASE_TWO_DTO = PREFIX + "push_phase_two_dto:";
        // 用户md5信息保存
        public static final String PUSH_PHASE_MD5_REPEAT = PREFIX + "push_phase_md5_repeat:";
    }

    /**
     * 借点钱-定时任务keys
     */
    public static final class JDQ_JOB {
        public static final String PREFIX = RedisKeyCoreConstant.PREFIX + "jdq_job:";
        // 推送风控
        public static final String PUSH_RISK_REPEAT = PREFIX + "push_risk_repeat:";
        // 风控推送中未回调
        public static final String PUSH_NOT_CALL_BACK_RISK_REPEAT = PREFIX + "push_not_call_back_risk_repeat:";
        // 手动一推信息入库
        public static final String PUSH_PHASE_ONE_DATA = PREFIX + "push_phase_one_data:";
        // 手动二推信息入库
        public static final String PUSH_PHASE_TWO_DATA = PREFIX + "push_phase_two_data:";
        // 风控推送身份证
        public static final String PUSH_RISK_IN_NUMBER_REPEAT = PREFIX + "push_phase_id_number_data:";

    }

    /**
     * 其他参数
     */
    public static final class OTHER_PARAM {
        public static final String PREFIX = RedisKeyCoreConstant.PREFIX + "other_param:";
        // ip白名单
        public static final String IP_BLACK_LIST = PREFIX + "ip_black_list:";
    }




}
