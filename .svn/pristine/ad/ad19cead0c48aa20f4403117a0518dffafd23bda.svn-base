package com.shangyong.thjdq.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 推送补充信息接口DTO对象
 * Created by zbb on 2019-03-14.
 */
public class PushPhaseTwoDto implements Serializable {

    private static final long serialVersionUID = 4879119921825767192L;

    private UserInfoTwoDto user_info;
    private UserContactDto user_contact;
    private List<AddressBookDto> address_book;
    private DeviceInfoDto device_info;
    private String jdq_order_id;
    private String userInfoId;


    public UserInfoTwoDto getUser_info() {
        return user_info;
    }

    public void setUser_info(UserInfoTwoDto user_info) {
        this.user_info = user_info;
    }

    public UserContactDto getUser_contact() {
        return user_contact;
    }

    public void setUser_contact(UserContactDto user_contact) {
        this.user_contact = user_contact;
    }

    public List<AddressBookDto> getAddress_book() {
        return address_book;
    }

    public void setAddress_book(List<AddressBookDto> address_book) {
        this.address_book = address_book;
    }

    public DeviceInfoDto getDevice_info() {
        return device_info;
    }

    public void setDevice_info(DeviceInfoDto device_info) {
        this.device_info = device_info;
    }

    public String getJdq_order_id() {
        return jdq_order_id;
    }

    public void setJdq_order_id(String jdq_order_id) {
        this.jdq_order_id = jdq_order_id;
    }

    public String getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(String userInfoId) {
        this.userInfoId = userInfoId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PushPhaseTwoDto{");
        sb.append("user_info=").append(user_info);
        sb.append(", user_contact=").append(user_contact);
        // 不打印通讯录
//        sb.append(", address_book=").append(address_book);
        sb.append(", device_info=").append(device_info);
        sb.append(", jdq_order_id=").append(jdq_order_id);
        sb.append(", userInfoId=").append(userInfoId);
        sb.append('}');
        return sb.toString();
    }
}
