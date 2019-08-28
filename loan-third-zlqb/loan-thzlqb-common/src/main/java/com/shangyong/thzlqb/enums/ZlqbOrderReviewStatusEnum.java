package com.shangyong.thzlqb.enums;

import java.util.Objects;

/**
 * @ClassName:ZlqbOrderReviewStatusEnum </br>
 * @Description: TODO  </br>
 * @Author:童晶继 tongjingji01@gmail.com </br>
 * @Date:2019年7月25日
 */
public enum ZlqbOrderReviewStatusEnum {
    REVIEW_IN(20,"审核中"),
    REVIEW_FAILURE(40,"审核失败"),
    REVIEW_SUCCESS(50,"审核成功");


    private Integer value;

    private String text;

    ZlqbOrderReviewStatusEnum(Integer value,String text){
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
            ZlqbOrderReviewStatusEnum[] values = ZlqbOrderReviewStatusEnum.values();
            for (ZlqbOrderReviewStatusEnum type : values){
                if (type.value == value) return type.getText();
            }
        }
        return "";
    }
    public static ZlqbOrderReviewStatusEnum findTypeByValue(Integer value) {
        if(Objects.nonNull(value)) {
            ZlqbOrderReviewStatusEnum[] values = ZlqbOrderReviewStatusEnum.values();
            for (ZlqbOrderReviewStatusEnum type : values){
                if (type.value == value) return type;
            }
        }
        return null;
    }
}
