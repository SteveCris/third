package com.shangyong.thjdq.dto;

import java.io.Serializable;

/**
 * 用户检测接口
 * Created by ybds on 2019-03-18.
 */
public class CheckUserDto implements Serializable{
    private static final long serialVersionUID = 171867151708546476L;

    /**
     * 用户身份证号（遇到字母转为大写），默认后五位掩码，如3408811979010*****
     */
    private String id_number;
    /**
     * 用户手机号，默认后4位掩码，如1391234****
     */
    private String phone;
    /**
     * 用户姓名
     */
    private String user_name;
    /**
     * 手机号和身份证号（遇到字母转为大写）MD5加密串
     */
    private String phone_id_number_md5;

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPhone_id_number_md5() {
        return phone_id_number_md5;
    }

    public void setPhone_id_number_md5(String phone_id_number_md5) {
        this.phone_id_number_md5 = phone_id_number_md5;
    }
}
