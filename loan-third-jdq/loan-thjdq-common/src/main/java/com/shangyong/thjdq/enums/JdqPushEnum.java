package com.shangyong.thjdq.enums;

/**
 * Created by ybds on 2019-03-24.
 */
public enum  JdqPushEnum {

    ORDER_STATE_CALL("feedbackOrderStatus","订单状态推送接口", "FeedbackOrderStatusVo"),
    AUTHORIZE_RESULT_CALL("authorizeFeedback","跳转认证结果反馈接口", "未定");

    /**
     * call
     */
    private String call;
    /**
     * 描述
     */
    private String desc;
    /**
     * 使用的data对象
     */
    private String objectName;

    public String getCall() {
        return call;
    }

    public void setCall(String call) {
        this.call = call;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    JdqPushEnum(String call, String desc,String objectName) {
        this.call = call;
        this.desc = desc;
        this.objectName = objectName;
    }
}
