package com.shangyong.thzlqb.enums;


import java.util.Objects;

/**
 * @ClassName:ZlqbIsOnTuEnum </br>
 * @Description: TODO  </br>
 * @Author:童晶继 tongjingji01@gmail.com </br>
 * @Date:2019年7月22日
 */


public enum ZlqbIsOnTuEnum {

    IS_STOCK_N(2>>2,"否"),
    IS_STOCK_Y(2>>1,"是");

    private Integer value;

    private String text;

    ZlqbIsOnTuEnum(Integer value,String text){
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
            ZlqbIsOnTuEnum[] values = ZlqbIsOnTuEnum.values();
            for (ZlqbIsOnTuEnum type : values){
                if (type.value == value) return type.getText();
            }
        }
        return "";
    }
    public static ZlqbIsOnTuEnum findTypeByValue(Integer value) {
        if(Objects.nonNull(value)) {
            ZlqbIsOnTuEnum[] values = ZlqbIsOnTuEnum.values();
            for (ZlqbIsOnTuEnum type : values){
                if (type.value == value) return type;
            }
        }
        return null;
    }
}
