package com.shangyong.thjdq.config.properties;

/**
 * 线下借点钱密钥配置类
 * Created by ybds on 2019-03-18.
 */
public class JdqKeyProperties {


    private String originEncryptKey;

    /** 线下机构私钥(PKCS8) **/
    private String partnerPrivateKey;

    /** 线下借点钱公钥 **/
    private String publicKey;

    public String getOriginEncryptKey() {
        return originEncryptKey;
    }

    public void setOriginEncryptKey(String originEncryptKey) {
        this.originEncryptKey = originEncryptKey;
    }

    public String getPartnerPrivateKey() {
        return partnerPrivateKey;
    }

    public void setPartnerPrivateKey(String partnerPrivateKey) {
        this.partnerPrivateKey = partnerPrivateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
