package com.shangyong.thjdq.enums;

/**
 * 图片格式限定
 * Created by zhengbb on 2019-01-03.
 */
public enum PicTypeEnum {

    JPG("JPG"),
    PNG("PNG"),
    GIF("GIF"),
    BMP("BMP"),
    JPEG("JPEG");

    private String description;//描述

    PicTypeEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static String getAllPicTypeStr() {
        PicTypeEnum[] arrPicTypeEnum = PicTypeEnum.values();
        StringBuilder sb = new StringBuilder();
        sb.append(".");
        for (PicTypeEnum picTypeEnum: arrPicTypeEnum) {
            sb.append(picTypeEnum.getDescription()+ ".");
        }

        return sb.toString();
    }
}
