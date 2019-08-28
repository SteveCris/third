package com.shangyong.thjdq.enums;

/**
 * Created by ybds on 2019-03-25.
 */
public enum CompanyIndustryEnum {

    PFLS(1,"1001503","批发/零售"),
    ZZY(2,"1000701","制造业"),
    JRBXZQ(3,"1001002","金融/保险/证券"),
    ZSCYLY(4,"1004408","住宿/餐饮/旅游"),
    SYFW(5,"1004508","商业服务/娱乐/艺术/体育"),
    JSJ(6,"1000201","计算机/互联网"),
    TXDZ(7,"1000501","通讯电子"),
    JZ(8,"1003206","建筑/房地产"),
    FL(9,"1003807","法律/咨询"),
    WS(10,"1003907","卫生/教育/社会服务"),
    GGSY(11,"1005511","公共事业/社会团体"),
    SW(12,"1002304","生物/制药"),
    GG(13,"1002605","广告/媒体"),
    NY(14,"1005310","能源"),
    MY(15,"1001403","贸易"),
    JTYS(16,"1004809","交通运输/仓储/物流"),
    NLMY(17,"1005711","农/林/牧/渔"),
    QT(18,"1006012","其他");

    private int code;
    private String riskName;
    private String desc;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getRiskName() {
        return riskName;
    }

    public void setRiskName(String riskName) {
        this.riskName = riskName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    CompanyIndustryEnum(int code, String riskName,String desc) {
        this.code = code;
        this.riskName = riskName;
        this.desc = desc;
    }

    public static String findByCode(String code) {
        CompanyIndustryEnum[] companyIndustryEnums = CompanyIndustryEnum.values();
        for (int i = 0; i < companyIndustryEnums.length; i++) {
            if(companyIndustryEnums[i].getRiskName().equals(code)) {
                return companyIndustryEnums[i].getDesc();
            }
        }
        return null;
    }

}
