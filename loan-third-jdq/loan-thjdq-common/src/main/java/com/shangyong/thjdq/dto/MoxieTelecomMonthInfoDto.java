package com.shangyong.thjdq.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ybds on 2019-03-14.
 */
public class MoxieTelecomMonthInfoDto implements Serializable {

    private static final long serialVersionUID = 6884138655395761906L;
    private String phone_no;

    private int month_count;

    private String user_id;

    private List<MoxieTelecomCommonDto> month_list;

    private int miss_month_count;

    private int no_call_month;

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public int getMonth_count() {
        return month_count;
    }

    public void setMonth_count(int month_count) {
        this.month_count = month_count;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public List<MoxieTelecomCommonDto> getMonth_list() {
        return month_list;
    }

    public void setMonth_list(List<MoxieTelecomCommonDto> month_list) {
        this.month_list = month_list;
    }

    public int getMiss_month_count() {
        return miss_month_count;
    }

    public void setMiss_month_count(int miss_month_count) {
        this.miss_month_count = miss_month_count;
    }

    public int getNo_call_month() {
        return no_call_month;
    }

    public void setNo_call_month(int no_call_month) {
        this.no_call_month = no_call_month;
    }
}
