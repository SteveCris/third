package com.shangyong.thjdq.enums;

/**
 * 当前环境枚举
 * Created by zhengbb on 2019-01-03.
 */
public enum ActiveProfileEnum {

    PRODUCT("正式环境"),
    TEST("测试环境"),
    DEV("开发环境");

    private String description;//描述

    ActiveProfileEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
