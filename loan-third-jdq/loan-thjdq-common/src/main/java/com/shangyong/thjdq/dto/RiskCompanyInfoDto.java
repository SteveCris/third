package com.shangyong.thjdq.dto;

import java.io.Serializable;

/**
 * Created by ybds on 2019-03-19.
 */
public class RiskCompanyInfoDto implements Serializable {

    private static final long serialVersionUID = -5636074611072627241L;

    /** 公司行业 **/
    private String companyIndustry;

    /** 公司名称 **/
    private String companyName;

    /** 公司电话 **/
    private String companyTel;

    /** 公司地址 **/
    private String companyAddress;

    /** 街道地址 **/
    private String streetAddress;

    /** 客户工作时长 **/
    private String workingHours;

    /** 经度 **/
    private String lng;

    /** 纬度 **/
    private String lat;

    /** 工作照片存储URL **/
    private String workPhoto;

    /** 省 **/
    private String province;

    /** 市 **/
    private String city;

    /** 区 **/
    private String area;

    /** 备注 **/
    private String remark;

    /** ADVANCED-高级资深人员、INTERMEDIATES-中级技术人员、BEGINNERS-初级、助理人员、PRACTICE-见习专员、SENIOR-高层管理人员、MIDDLE-中层管理人员、JUNIOR-基层管理人员、NORMAL-普通员工 **/
    private String professionId;

    public String getCompanyIndustry() {
        return companyIndustry;
    }

    public void setCompanyIndustry(String companyIndustry) {
        this.companyIndustry = companyIndustry;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyTel() {
        return companyTel;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getWorkPhoto() {
        return workPhoto;
    }

    public void setWorkPhoto(String workPhoto) {
        this.workPhoto = workPhoto;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getProfessionId() {
        return professionId;
    }

    public void setProfessionId(String professionId) {
        this.professionId = professionId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RiskCompanyInfoDto{");
        sb.append("companyIndustry='").append(companyIndustry).append('\'');
        sb.append(", companyName='").append(companyName).append('\'');
        sb.append(", companyTel='").append(companyTel).append('\'');
        sb.append(", companyAddress='").append(companyAddress).append('\'');
        sb.append(", streetAddress='").append(streetAddress).append('\'');
        sb.append(", workingHours='").append(workingHours).append('\'');
        sb.append(", lng='").append(lng).append('\'');
        sb.append(", lat='").append(lat).append('\'');
        sb.append(", workPhoto='").append(workPhoto).append('\'');
        sb.append(", province='").append(province).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", area='").append(area).append('\'');
        sb.append(", remark='").append(remark).append('\'');
        sb.append(", professionId='").append(professionId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
