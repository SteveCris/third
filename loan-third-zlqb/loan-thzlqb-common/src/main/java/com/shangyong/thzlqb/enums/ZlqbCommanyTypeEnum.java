package com.shangyong.thzlqb.enums;

import java.util.Objects;

/**
 * @ClassName:ZlqbCommanyTypeEnum </br>
 * @Description: TODO  </br>
 * @Author:童晶继 tongjingji01@gmail.com </br>
 * @Date:2019年7月22日
 */
public enum ZlqbCommanyTypeEnum {
    INTERNET(0,"互联网"),
    BUILDER(1,"房地产/建筑"),
    SERVER(2,"服务业"),
    TRADE(3,"贸易批发"),
    FINANCIAL(4,"金融"),
    EDUCATION(5,"教育艺术"),
    MEDICINE(6,"医药卫生"),
    ENTERTAINMENT(7,"文化传媒"),
    ELECTRONIC(8,"电子电功"),
    OTHER(9,"其它");


    private Integer value;

    private String text;

    ZlqbCommanyTypeEnum(Integer value,String text){
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
            ZlqbCommanyTypeEnum[] values = ZlqbCommanyTypeEnum.values();
            for (ZlqbCommanyTypeEnum type : values){
                if (type.value == value) return type.getText();
            }
        }
        return "";
    }
    public static ZlqbCommanyTypeEnum findTypeByValue(Integer value) {
        if(Objects.nonNull(value)) {
            ZlqbCommanyTypeEnum[] values = ZlqbCommanyTypeEnum.values();
            for (ZlqbCommanyTypeEnum type : values){
                if (type.value == value) return type;
            }
        }
        return null;
    }
}
