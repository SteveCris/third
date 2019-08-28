package com.shangyong.thjdq.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 借点钱调用机构接口参数
 * Created by ybds on 2019-03-18.
 */
public class JdqInvokeChannelDto implements Serializable {

    private static final long serialVersionUID = -3479367539549158674L;

    /**
     * 机构编号
     */
    @ApiModelProperty(value = "机构编号")
    private String channel_code;
    /**
     * 签名 对参数按照RSA算法计算得到的签名，详见签名算法
     */
    @ApiModelProperty(value = "签名")
    private String sign;
    /**
     * 业务数据 如果不需要加密，则是原始json串或压缩后的字符串
     */
    @ApiModelProperty(value = "业务数据 如果不需要加密，则是原始json串或压缩后的字符串")
    private String data;
    /**
     * 是否加密 0-不加密,1-加密，默认加密，默认使用AES算法(128位密钥)
     */
    @ApiModelProperty(value = "是否加密 0-不加密,1-加密，默认加密，默认使用AES算法(128位密钥)")
    private int encrypt;
    /**
     * 加密密钥 原始密钥经过RSA加密后的值 可以为空
     */
    @ApiModelProperty(value = "加密密钥 原始密钥经过RSA加密后的值 可以为空")
    private String encrypt_key;
    /**
     * 是否压缩 0-不压缩,1-压缩，默认不压缩。如果压缩，对原始json串压缩
     */
    @ApiModelProperty(value = "是否压缩 0-不压缩,1-压缩，默认不压缩。如果压缩，对原始json串压缩")
    private int compress;
    /**
     * 压缩方式 如果压缩，该字段必传，默认为gzip 可以为空
     */
    @ApiModelProperty(value = "压缩方式 如果压缩，该字段必传，默认为gzip 可以为空")
    private String compress_type;

    public String getChannel_code() {
        return channel_code;
    }

    public void setChannel_code(String channel_code) {
        this.channel_code = channel_code;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(int encrypt) {
        this.encrypt = encrypt;
    }

    public String getEncrypt_key() {
        return encrypt_key;
    }

    public void setEncrypt_key(String encrypt_key) {
        this.encrypt_key = encrypt_key;
    }

    public int getCompress() {
        return compress;
    }

    public void setCompress(int compress) {
        this.compress = compress;
    }

    public String getCompress_type() {
        return compress_type;
    }

    public void setCompress_type(String compress_type) {
        this.compress_type = compress_type;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("JdqBaseDto{");
        sb.append("channel_code='").append(channel_code).append('\'');
        sb.append(", sign='").append(sign).append('\'');
        sb.append(", data='").append(data).append('\'');
        sb.append(", encrypt=").append(encrypt);
        sb.append(", encrypt_key='").append(encrypt_key).append('\'');
        sb.append(", compress=").append(compress);
        sb.append(", compress_type='").append(compress_type).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
