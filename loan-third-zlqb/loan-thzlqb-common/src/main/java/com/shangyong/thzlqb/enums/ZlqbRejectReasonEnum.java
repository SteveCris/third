package com.shangyong.thzlqb.enums;

import java.util.Objects;

/**
 * @ClassName:ZlqbIsOnTuEnum </br>
 * @Description: TODO  </br>
 * @Author:童晶继 tongjingji01@gmail.com </br>
 * @Date:2019年7月22日
 */


public enum ZlqbRejectReasonEnum {
    VOID(0,"VOID"),
    BLACKLIST(1,"黑名单"),
    ONTU(2,"在贷"),
    REJECTED(3,"被拒记录"),
    OTHER(4," 其他");


    private Integer value;

    private String text;

    ZlqbRejectReasonEnum(Integer value,String text){
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
            ZlqbRejectReasonEnum[] values = ZlqbRejectReasonEnum.values();
            for (ZlqbRejectReasonEnum type : values){
                if (type.value == value) return type.getText();
            }
        }
        return "";
    }
    public static ZlqbRejectReasonEnum findTypeByValue(Integer value) {
        if(Objects.nonNull(value)) {
            ZlqbRejectReasonEnum[] values = ZlqbRejectReasonEnum.values();
            for (ZlqbRejectReasonEnum type : values){
                if (type.value == value) return type;
            }
        }
        return null;
    }
}
