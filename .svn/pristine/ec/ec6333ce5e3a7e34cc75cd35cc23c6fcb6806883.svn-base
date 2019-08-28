package com.shangyong.thjdq.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 跳转机构绑卡接口返回数据
 * Created by ybds on 2019-03-22.
 */
public class BindCardExtVo implements Serializable {

    private static final long serialVersionUID = -2043886681216830657L;

    @ApiModelProperty(value = "绑卡状态： 1-老卡确认成功，无需再重新跳转合作机构绑卡页面。 2-跳转合作机构绑卡页面进行绑卡。")
    private int bind_status;

    @ApiModelProperty(value = "绑卡H5链接地址(bind_status=2时必传)，点击该地址可直接打开绑卡页面")
    private String url;

    public int getBind_status() {
        return bind_status;
    }

    public void setBind_status(int bind_status) {
        this.bind_status = bind_status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BindCardExtVo{");
        sb.append("bind_status=").append(bind_status);
        sb.append(", url='").append(url).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
