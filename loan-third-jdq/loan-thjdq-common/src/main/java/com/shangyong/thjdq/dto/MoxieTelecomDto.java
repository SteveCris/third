package com.shangyong.thjdq.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zbb on 2019-03-14.
 */
public class MoxieTelecomDto implements Serializable {

    private static final long serialVersionUID = -8808895696471263232L;
    private List<MoxieTelecomSmsesDto> smses;

    private int code;

    private String city;

    private List<MoxieTelecomNetsDto> nets;

    private String last_modify_time;

    private int reliability;

    private String open_time;

    private String imsi;

    private int available_balance;

    private String province;

    private List<MoxieTelecomBillsDto> bills;

    private String state;

    private String email;

    private String address;

    private MoxieTelecomMonthInfoDto month_info;

    private String level;

    private String mobile;

    private String message;

    private List<MoxieTelecomPackagesDto> packages;

    private List<MoxieTelecomFamiliesDto> families;

    private List<MoxieTelecomRechargesDto> recharges;

    private String carrier;

    private List<MoxieTelecomCallsDto> calls;

    private String idcard;

    private String name;

    private String package_name;

    public List<MoxieTelecomSmsesDto> getSmses() {
        return smses;
    }

    public void setSmses(List<MoxieTelecomSmsesDto> smses) {
        this.smses = smses;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<MoxieTelecomNetsDto> getNets() {
        return nets;
    }

    public void setNets(List<MoxieTelecomNetsDto> nets) {
        this.nets = nets;
    }

    public String getLast_modify_time() {
        return last_modify_time;
    }

    public void setLast_modify_time(String last_modify_time) {
        this.last_modify_time = last_modify_time;
    }

    public int getReliability() {
        return reliability;
    }

    public void setReliability(int reliability) {
        this.reliability = reliability;
    }

    public String getOpen_time() {
        return open_time;
    }

    public void setOpen_time(String open_time) {
        this.open_time = open_time;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public int getAvailable_balance() {
        return available_balance;
    }

    public void setAvailable_balance(int available_balance) {
        this.available_balance = available_balance;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public List<MoxieTelecomBillsDto> getBills() {
        return bills;
    }

    public void setBills(List<MoxieTelecomBillsDto> bills) {
        this.bills = bills;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MoxieTelecomMonthInfoDto getMonth_info() {
        return month_info;
    }

    public void setMonth_info(MoxieTelecomMonthInfoDto month_info) {
        this.month_info = month_info;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<MoxieTelecomPackagesDto> getPackages() {
        return packages;
    }

    public void setPackages(List<MoxieTelecomPackagesDto> packages) {
        this.packages = packages;
    }

    public List<MoxieTelecomFamiliesDto> getFamilies() {
        return families;
    }

    public void setFamilies(List<MoxieTelecomFamiliesDto> families) {
        this.families = families;
    }

    public List<MoxieTelecomRechargesDto> getRecharges() {
        return recharges;
    }

    public void setRecharges(List<MoxieTelecomRechargesDto> recharges) {
        this.recharges = recharges;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public List<MoxieTelecomCallsDto> getCalls() {
        return calls;
    }

    public void setCalls(List<MoxieTelecomCallsDto> calls) {
        this.calls = calls;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }
}
