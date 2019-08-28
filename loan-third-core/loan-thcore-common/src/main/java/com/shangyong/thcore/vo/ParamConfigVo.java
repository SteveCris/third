package com.shangyong.thcore.vo;

import java.io.Serializable;

/**
 * 参数配置
 * Created by ybds on 2019-03-27.
 */
public class ParamConfigVo implements Serializable {

    private static final long serialVersionUID = -428693535170849720L;

    //参数key
    private String paramKey;

    //参数value
    private String paramValue;

    //参数描述
    private String paramDesc;

    public String getParamKey() {
        return paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getParamDesc() {
        return paramDesc;
    }

    public void setParamDesc(String paramDesc) {
        this.paramDesc = paramDesc;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ParamConfigVo{");
        sb.append("paramKey='").append(paramKey).append('\'');
        sb.append(", paramValue='").append(paramValue).append('\'');
        sb.append(", paramDesc='").append(paramDesc).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
