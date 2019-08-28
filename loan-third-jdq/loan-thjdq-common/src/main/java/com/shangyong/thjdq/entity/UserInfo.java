package com.shangyong.thjdq.entity;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class UserInfo implements Serializable {
    //主键id
    @ApiModelProperty(value = "主键id")
    private String userInfoId;

    //借点钱订单id
    @ApiModelProperty(value = "借点钱订单id")
    private String jdqOrderId;

    //本地订单号
    @ApiModelProperty(value = "本地订单号")
    private String localOrderId;

    //客户id
    @ApiModelProperty(value = "客户id")
    private String customerId;

    //风控流水单号
    @ApiModelProperty(value = "风控流水单号")
    private String appSerialNumber;

    //手机号码
    @ApiModelProperty(value = "手机号码")
    private String mobile;

    //姓名
    @ApiModelProperty(value = "姓名")
    private String name;

    //身份证
    @ApiModelProperty(value = "身份证")
    private String idNumber;

    //手机身份证md5
    @ApiModelProperty(value = "手机身份证md5")
    private String phoneIdNumberMd5;

    //身份证类型 （1=工薪族、2=企业主、3=自由职业者）
    @ApiModelProperty(value = "身份证类型 （1=工薪族、2=企业主、3=自由职业者）")
    private String role;

    //出生年月 yyyy-MM-dd
    @ApiModelProperty(value = "出生年月 yyyy-MM-dd")
    private String birth;

    //年龄
    @ApiModelProperty(value = "年龄")
    private Integer age;

    //名族
    @ApiModelProperty(value = "名族")
    private String nation;

    //身份证正面图片url
    @ApiModelProperty(value = "身份证正面图片url")
    private String idPositive;

    //身份证反面图片url
    @ApiModelProperty(value = "身份证反面图片url")
    private String idNegative;

    //人脸照片url
    @ApiModelProperty(value = "人脸照片url")
    private String face;

    //手持身份证照片URL
    @ApiModelProperty(value = "手持身份证照片URL")
    private String handIdPhoto;

    //婚姻状况
    @ApiModelProperty(value = "婚姻状况")
    private String marry;

    //文化程度
    @ApiModelProperty(value = "文化程度")
    private String educate;

    //邮箱
    @ApiModelProperty(value = "邮箱")
    private String email;

    //居住详细地址
    @ApiModelProperty(value = "居住详细地址")
    private String livingAddress;

    //户籍地址
    @ApiModelProperty(value = "户籍地址")
    private String idCardAddress;

    //一推二推消息状态 0:初始化;1:一推中;2;一推成功;3:一推失败4:二推中:5:二推成功;6:二推失败
    @ApiModelProperty(value = "一推二推消息状态 0:初始化;1:一推中;2;一推成功;3:一推失败4:二推中:5:二推成功;6:二推失败")
    private Integer pushPhaseState;

    //推送风控状态 0:初始化;1:推送中;2:审核失败;3:审核成功；4:重复身份证，不推送风控
    @ApiModelProperty(value = "推送风控状态 0:初始化;1:推送中;2:审核失败;3:审核成功;4:重复身份证")
    private Integer pushRiskState;

    //运营商数据推送状态 0:初始化:1:推送中;2:审核失败:3审核成功；4:重复身份证，不推送风控
    @ApiModelProperty(value = "运营商数据推送状态 0:初始化:1:推送中;2:审核失败:3:审核成功;4:重复身份证")
    private Integer pushOperatorState;

    //无源比对结果数据
    @ApiModelProperty(value = "无源比对结果数据")
    private String faceResult;

    //有源比对结果数据
    @ApiModelProperty(value = "有源比对结果数据")
    private String faceResultActive;

    //上一次审批被拒时间
    @ApiModelProperty(value = "上一次审批被拒时间")
    private Date lastRejectDate;

    //身份证签发机构
    @ApiModelProperty(value = "身份证签发机构")
    private String idSigningAuthority;

    //身份证有效起始日期(格式：2016-09-01
    @ApiModelProperty(value = "身份证有效起始日期(格式：2016-09-01")
    private String idStartDate;

    //身份证有效截止日期(格式：2036-09-01或长期
    @ApiModelProperty(value = "身份证有效截止日期(格式：2036-09-01或长期")
    private String idExpiryDate;

    //创建时间
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    //修改时间
    @ApiModelProperty(value = "修改时间")
    private Date modifyTime;
    
    private String appName;
    
    private String ext1;
    
    private String ext2;

    private static final long serialVersionUID = 1L;

    
    
    public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public String getExt2() {
		return ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	/**
     * 获取主键id
     *
     */
    public String getUserInfoId() {
        return userInfoId;
    }

    /**
     * 设置主键id
     *
     * @param userInfoId 主键id
     */
    public void setUserInfoId(String userInfoId) {
        this.userInfoId = userInfoId == null ? null : userInfoId.trim();
    }

    /**
     * 获取借点钱订单id
     *
     */
    public String getJdqOrderId() {
        return jdqOrderId;
    }

    /**
     * 设置借点钱订单id
     *
     * @param jdqOrderId 借点钱订单id
     */
    public void setJdqOrderId(String jdqOrderId) {
        this.jdqOrderId = jdqOrderId == null ? null : jdqOrderId.trim();
    }

    /**
     * 获取本地订单号
     *
     */
    public String getLocalOrderId() {
        return localOrderId;
    }

    /**
     * 设置本地订单号
     *
     * @param localOrderId 本地订单号
     */
    public void setLocalOrderId(String localOrderId) {
        this.localOrderId = localOrderId == null ? null : localOrderId.trim();
    }

    /**
     * 获取客户id
     *
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * 设置客户id
     *
     * @param customerId 客户id
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * 获取风控流水单号
     *
     */
    public String getAppSerialNumber() {
        return appSerialNumber;
    }

    /**
     * 设置风控流水单号
     *
     * @param appSerialNumber 风控流水单号
     */
    public void setAppSerialNumber(String appSerialNumber) {
        this.appSerialNumber = appSerialNumber;
    }

    /**
     * 获取手机号码
     *
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号码
     *
     * @param mobile 手机号码
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 获取姓名
     *
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取身份证
     *
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * 设置身份证
     *
     * @param idNumber 身份证
     */
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber == null ? null : idNumber.trim();
    }
    
    /**
     * 获取手机身份证md5
     *
     */
    public String getPhoneIdNumberMd5() {
        return phoneIdNumberMd5;
    }

    /**
     * 设置手机身份证md5
     *
     * @param phoneIdNumberMd5 手机身份证md5
     */
    public void setPhoneIdNumberMd5(String phoneIdNumberMd5) {
        this.phoneIdNumberMd5 = phoneIdNumberMd5 == null ? null : phoneIdNumberMd5.trim();
    }

    /**
     * 获取身份证类型 （1=工薪族、2=企业主、3=自由职业者）
     *
     */
    public String getRole() {
        return role;
    }

    /**
     * 设置身份证类型 （1=工薪族、2=企业主、3=自由职业者）
     *
     * @param role 身份证类型 （1=工薪族、2=企业主、3=自由职业者）
     */
    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    /**
     * 获取出生年月 yyyy-MM-dd
     *
     */
    public String getBirth() {
        return birth;
    }

    /**
     * 设置出生年月 yyyy-MM-dd
     *
     * @param birth 出生年月 yyyy-MM-dd
     */
    public void setBirth(String birth) {
        this.birth = birth == null ? null : birth.trim();
    }

    /**
     * 获取年龄
     *
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置年龄
     *
     * @param age 年龄
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取名族
     *
     */
    public String getNation() {
        return nation;
    }

    /**
     * 设置名族
     *
     * @param nation 名族
     */
    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
    }

    /**
     * 获取身份证正面图片url
     *
     */
    public String getIdPositive() {
        return idPositive;
    }

    /**
     * 设置身份证正面图片url
     *
     * @param idPositive 身份证正面图片url
     */
    public void setIdPositive(String idPositive) {
        this.idPositive = idPositive == null ? null : idPositive.trim();
    }

    /**
     * 获取身份证反面图片url
     *
     */
    public String getIdNegative() {
        return idNegative;
    }

    /**
     * 设置身份证反面图片url
     *
     * @param idNegative 身份证反面图片url
     */
    public void setIdNegative(String idNegative) {
        this.idNegative = idNegative == null ? null : idNegative.trim();
    }

    /**
     * 获取人脸照片url
     *
     */
    public String getFace() {
        return face;
    }

    /**
     * 设置人脸照片url
     *
     * @param face 人脸照片url
     */
    public void setFace(String face) {
        this.face = face == null ? null : face.trim();
    }

    /**
     * 获取手持身份证照片URL
     *
     */
    public String getHandIdPhoto() {
        return handIdPhoto;
    }

    /**
     * 设置手持身份证照片URL
     *
     * @param handIdPhoto 手持身份证照片URL
     */
    public void setHandIdPhoto(String handIdPhoto) {
        this.handIdPhoto = handIdPhoto == null ? null : handIdPhoto.trim();
    }

    /**
     * 获取婚姻状况
     *
     */
    public String getMarry() {
        return marry;
    }

    /**
     * 设置婚姻状况
     *
     * @param marry 婚姻状况
     */
    public void setMarry(String marry) {
        this.marry = marry == null ? null : marry.trim();
    }

    /**
     * 获取文化程度
     *
     */
    public String getEducate() {
        return educate;
    }

    /**
     * 设置文化程度
     *
     * @param educate 文化程度
     */
    public void setEducate(String educate) {
        this.educate = educate == null ? null : educate.trim();
    }

    /**
     * 获取邮箱
     *
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取居住详细地址
     *
     */
    public String getLivingAddress() {
        return livingAddress;
    }

    /**
     * 设置居住详细地址
     *
     * @param livingAddress 居住详细地址
     */
    public void setLivingAddress(String livingAddress) {
        this.livingAddress = livingAddress == null ? null : livingAddress.trim();
    }

    /**
     * 获取户籍地址
     *
     */
    public String getIdCardAddress() {
        return idCardAddress;
    }

    /**
     * 设置户籍地址
     *
     * @param idCardAddress 户籍地址
     */
    public void setIdCardAddress(String idCardAddress) {
        this.idCardAddress = idCardAddress == null ? null : idCardAddress.trim();
    }

    /**
     * 获取一推二推消息状态 0:初始化;1:一推中;2;一推成功;3:一推失败4:二推中:5:二推成功;6:二推失败
     *
     */
    public Integer getPushPhaseState() {
        return pushPhaseState;
    }

    /**
     * 设置一推二推消息状态 0:初始化;1:一推中;2;一推成功;3:一推失败4:二推中:5:二推成功;6:二推失败
     *
     * @param pushPhaseState 一推二推消息状态 0:初始化;1:一推中;2;一推成功;3:一推失败4:二推中:5:二推成功;6:二推失败
     */
    public void setPushPhaseState(Integer pushPhaseState) {
        this.pushPhaseState = pushPhaseState;
    }

    /**
     * 获取推送风控状态 0:初始化;1:推送中;2:审核失败;3:审核成功
     *
     */
    public Integer getPushRiskState() {
        return pushRiskState;
    }

    /**
     * 设置推送风控状态 0:初始化;1:推送中;2:审核失败;3:审核成功
     *
     * @param pushRiskState 推送风控状态 0:初始化;1:推送中;2:审核失败;3:审核成功
     */
    public void setPushRiskState(Integer pushRiskState) {
        this.pushRiskState = pushRiskState;
    }

    /**
     * 获取运营商数据推送状态 0:初始化:1:推送中;2:审核失败:3审核成功
     *
     */
    public Integer getPushOperatorState() {
        return pushOperatorState;
    }

    /**
     * 设置运营商数据推送状态 0:初始化:1:推送中;2:审核失败:3审核成功
     *
     * @param pushOperatorState 运营商数据推送状态 0:初始化:1:推送中;2:审核失败:3审核成功
     */
    public void setPushOperatorState(Integer pushOperatorState) {
        this.pushOperatorState = pushOperatorState;
    }

    /**
     * 获取无源比对结果数据
     *
     */
    public String getFaceResult() {
        return faceResult;
    }

    /**
     * 设置无源比对结果数据
     *
     * @param faceResult 无源比对结果数据
     */
    public void setFaceResult(String faceResult) {
        this.faceResult = faceResult;
    }

    /**
     * 获取有源比对结果数据
     *
     */
    public String getFaceResultActive() {
        return faceResultActive;
    }

    /**
     * 设置有源比对结果数据
     *
     * @param faceResultActive 有源比对结果数据
     */
    public void setFaceResultActive(String faceResultActive) {
        this.faceResultActive = faceResultActive;
    }

    /**
     * 获取上一次审批被拒时间
     *
     */
    public Date getLastRejectDate() {
        return lastRejectDate;
    }

    /**
     * 设置上一次审批被拒时间
     *
     * @param lastRejectDate 上一次审批被拒时间
     */
    public void setLastRejectDate(Date lastRejectDate) {
        this.lastRejectDate = lastRejectDate;
    }

    /**
     * 获取身份证签发机构
     *
     */
    public String getIdSigningAuthority() {
        return idSigningAuthority;
    }

    /**
     * 设置身份证签发机构
     *
     * @param idSigningAuthority 身份证签发机构
     */
    public void setIdSigningAuthority(String idSigningAuthority) {
        this.idSigningAuthority = idSigningAuthority == null ? null : idSigningAuthority.trim();
    }

    /**
     * 获取身份证有效起始日期(格式：2016-09-01
     *
     */
    public String getIdStartDate() {
        return idStartDate;
    }

    /**
     * 设置身份证有效起始日期(格式：2016-09-01
     *
     * @param idStartDate 身份证有效起始日期(格式：2016-09-01
     */
    public void setIdStartDate(String idStartDate) {
        this.idStartDate = idStartDate == null ? null : idStartDate.trim();
    }

    /**
     * 获取身份证有效截止日期(格式：2036-09-01或长期
     *
     */
    public String getIdExpiryDate() {
        return idExpiryDate;
    }

    /**
     * 设置身份证有效截止日期(格式：2036-09-01或长期
     *
     * @param idExpiryDate 身份证有效截止日期(格式：2036-09-01或长期
     */
    public void setIdExpiryDate(String idExpiryDate) {
        this.idExpiryDate = idExpiryDate == null ? null : idExpiryDate.trim();
    }

    /**
     * 获取创建时间
     *
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置修改时间
     *
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserInfo{");
        sb.append("userInfoId=").append(userInfoId);
        sb.append(", jdqOrderId=").append(jdqOrderId);
        sb.append(", localOrderId=").append(localOrderId);
        sb.append(", mobile=").append(mobile);
        sb.append(", name=").append(name);
        sb.append(", idNumber=").append(idNumber);
        sb.append(", phoneIdNumberMd5=").append(phoneIdNumberMd5);
//        sb.append(", role=").append(role);
//        sb.append(", birth=").append(birth);
//        sb.append(", age=").append(age);
//        sb.append(", nation=").append(nation);
//        sb.append(", idPositive=").append(idPositive);
//        sb.append(", idNegative=").append(idNegative);
//        sb.append(", face=").append(face);
//        sb.append(", handIdPhoto=").append(handIdPhoto);
//        sb.append(", marry=").append(marry);
//        sb.append(", educate=").append(educate);
//        sb.append(", email=").append(email);
//        sb.append(", livingAddress=").append(livingAddress);
//        sb.append(", idCardAddress=").append(idCardAddress);
        sb.append(", pushPhaseState=").append(pushPhaseState);
        sb.append(", pushRiskState=").append(pushRiskState);
        sb.append(", pushOperatorState=").append(pushOperatorState);
//        sb.append(", faceResult=").append(faceResult);
//        sb.append(", faceResultActive=").append(faceResultActive);
//        sb.append(", lastRejectDate=").append(lastRejectDate);
//        sb.append(", idSigningAuthority=").append(idSigningAuthority);
//        sb.append(", idStartDate=").append(idStartDate);
//        sb.append(", idExpiryDate=").append(idExpiryDate);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", appName=").append(appName);
        sb.append(", ext1=").append(ext1);
        sb.append(", ext2=").append(ext2);
        sb.append('}');
        return sb.toString();
    }
}