package com.shangyong.thjdq.dto;

import java.io.Serializable;

/**
 * Created by ybds on 2019-03-14.
 */
public class MoxieTelecomFamiliesItemsDto implements Serializable {

    private static final long serialVersionUID = 6860882648564788200L;
    private String long_number;
    private String short_number;
    private String member_type;
    private String join_date;
    private String expire_date;

    public String getLong_number() {
        return long_number;
    }

    public void setLong_number(String long_number) {
        this.long_number = long_number;
    }

    public String getShort_number() {
        return short_number;
    }

    public void setShort_number(String short_number) {
        this.short_number = short_number;
    }

    public String getMember_type() {
        return member_type;
    }

    public void setMember_type(String member_type) {
        this.member_type = member_type;
    }

    public String getJoin_date() {
        return join_date;
    }

    public void setJoin_date(String join_date) {
        this.join_date = join_date;
    }

    public String getExpire_date() {
        return expire_date;
    }

    public void setExpire_date(String expire_date) {
        this.expire_date = expire_date;
    }
}
