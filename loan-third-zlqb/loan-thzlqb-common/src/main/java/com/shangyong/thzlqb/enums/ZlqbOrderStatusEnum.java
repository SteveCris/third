package com.shangyong.thzlqb.enums;

import java.util.Objects;

/**
 * @ClassName:ZlqbOrderStatusEnum </br>
 * @Description: TODO  </br>
 * @Author:童晶继 tongjingji01@gmail.com </br>
 * @Date:2019年7月22日
 */
public enum ZlqbOrderStatusEnum {

    VOID_DB(0,"初始化"),
    DATA_INVIDATION(10,"数据时限去除"),
    INSERT_DB(20,"基本数据落库"),
    PUSH_CHECK(30,"推送审核"),
    CHECK_PASS(40,"审核通过"),
    CHECK_FAIL(50,"审核不通过"),
    PAY_FAIL(100,"放款失败"),
    REPAY_SUC(130,"还款成功"),
    ORDER_CANCEL(1000,"订单取消");

    private Integer value;

    private String text;

    ZlqbOrderStatusEnum(Integer value,String text){
        this.value=value;
        this.text=text;
    }

    /**
     * @return the value
     */
    public Integer getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Integer value) {
        this.value = value;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    public static String getTextByValue(Integer value) {
        if(Objects.nonNull(value)) {
            ZlqbOrderStatusEnum[] values = ZlqbOrderStatusEnum.values();
            for (ZlqbOrderStatusEnum type : values){
                if (type.value == value) return type.getText();
            }
        }
        return "";
    }
    public static ZlqbOrderStatusEnum findTypeByValue(Integer value) {
        if(Objects.nonNull(value)) {
            ZlqbOrderStatusEnum[] values = ZlqbOrderStatusEnum.values();
            for (ZlqbOrderStatusEnum type : values){
                if (type.value == value) return type;
            }
        }
        return null;
    }


}
