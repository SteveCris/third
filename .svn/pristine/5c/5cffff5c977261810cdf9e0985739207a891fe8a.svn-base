package com.shangyong.thzlqb.enums;

import java.util.Objects;

/**
 * @ClassName:ZlqbIsSignEnum </br>
 * @Description: TODO  </br>
 * @Author:童晶继 tongjingji01@gmail.com </br>
 * @Date:2019年7月25日
 */
public enum ZlqbIsSignEnum {
    IS_SIGN_N(2>>2,"否"),
    IS_SIGN_Y(2>>1,"是");

    private Integer value;

    private String text;

    ZlqbIsSignEnum(Integer value,String text){
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
            ZlqbIsSignEnum[] values = ZlqbIsSignEnum.values();
            for (ZlqbIsSignEnum type : values){
                if (type.value == value) return type.getText();
            }
        }
        return "";
    }
    public static ZlqbIsSignEnum findTypeByValue(Integer value) {
        if(Objects.nonNull(value)) {
            ZlqbIsSignEnum[] values = ZlqbIsSignEnum.values();
            for (ZlqbIsSignEnum type : values){
                if (type.value == value) return type;
            }
        }
        return null;
    }

}
