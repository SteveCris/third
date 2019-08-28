package com.shangyong.thzlqb.enums;

import java.util.Objects;

/**
 * @ClassName:ZlqbProfessionTypeEnum </br>
 * @Description: TODO  </br>
 * @Author:童晶继 tongjingji01@gmail.com </br>
 * @Date:2019年7月22日
 */
public enum ZlqbProfessionTypeEnum {
    WAGE_EARNERS(1,"工薪族"),
    BUSUBESS_OWNER(2,"企业主"),
    STUDENT(3,"学生"),
    PROFESSIONAL(4,"自由职业");


    private Integer value;

    private String text;

    ZlqbProfessionTypeEnum(Integer value,String text){
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
            ZlqbProfessionTypeEnum[] values = ZlqbProfessionTypeEnum.values();
            for (ZlqbProfessionTypeEnum type : values){
                if (type.value == value) return type.getText();
            }
        }
        return "";
    }
    public static ZlqbProfessionTypeEnum findTypeByValue(Integer value) {
        if(Objects.nonNull(value)) {
            ZlqbProfessionTypeEnum[] values = ZlqbProfessionTypeEnum.values();
            for (ZlqbProfessionTypeEnum type : values){
                if (type.value == value) return type;
            }
        }
        return null;
    }
}
