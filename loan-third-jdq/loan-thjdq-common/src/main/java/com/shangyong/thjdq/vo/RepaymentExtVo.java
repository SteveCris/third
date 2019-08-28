package com.shangyong.thjdq.vo;

import java.io.Serializable;

/**
 * Created by ybds on 2019-03-22.
 */
public class RepaymentExtVo implements Serializable {

    private static final long serialVersionUID = -357114699188700759L;

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RepaymentExtVo{");
        sb.append("url='").append(url).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
