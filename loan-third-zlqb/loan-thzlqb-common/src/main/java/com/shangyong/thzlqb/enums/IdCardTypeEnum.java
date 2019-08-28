package com.shangyong.thzlqb.enums;

import java.util.Objects;
/**
 * @ClassName:IdCardTypeEnum </br>
 * @Description: TODO  </br>
 * @Author:童晶继 tongjingji01@gmail.com </br>
 * @Date:2019年7月22日
 */
public enum IdCardTypeEnum {
    ID_CARD_CLEAE(2>>2,"默认"),
    ID_CARD_NONE(2>>1,"明文");



    private Integer value;

    private String text;

    IdCardTypeEnum(Integer value,String text){
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
            IdCardTypeEnum[] values = IdCardTypeEnum.values();
            for (IdCardTypeEnum type : values){
                if (type.value == value) return type.getText();
            }
        }
        return "";
    }
    public static IdCardTypeEnum findTypeByValue(Integer value) {
        if(Objects.nonNull(value)) {
            IdCardTypeEnum[] values = IdCardTypeEnum.values();
            for (IdCardTypeEnum type : values){
                if (type.value == value) return type;
            }
        }
        return null;
    }
}
