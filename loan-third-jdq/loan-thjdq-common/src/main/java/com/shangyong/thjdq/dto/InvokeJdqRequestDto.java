package com.shangyong.thjdq.dto;

import java.io.Serializable;

/**
 * Created by ybds on 2019-03-24.
 */
public class InvokeJdqRequestDto implements Serializable {

    private static final long serialVersionUID = 970146037661178023L;

    private String channel_code;
    private String call;
    private String version;
    private String sign;
    private String data;
    private long timestamp;
    // 可选：是否加密
    private Integer encrypt;
    // 可选：加密密钥
    private String encrypt_key;

    public String getChannel_code() {
        return channel_code;
    }

    public void setChannel_code(String channel_code) {
        this.channel_code = channel_code;
    }

    public String getCall() {
        return call;
    }

    public void setCall(String call) {
        this.call = call;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(Integer encrypt) {
        this.encrypt = encrypt;
    }

    public String getEncrypt_key() {
        return encrypt_key;
    }

    public void setEncrypt_key(String encrypt_key) {
        this.encrypt_key = encrypt_key;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InvokeJdqRequestDto{");
        sb.append("channel_code='").append(channel_code).append('\'');
        sb.append(", call='").append(call).append('\'');
        sb.append(", version='").append(version).append('\'');
        sb.append(", sign='").append(sign).append('\'');
        sb.append(", data='").append(data).append('\'');
        sb.append(", timestamp=").append(timestamp);
        sb.append(", encrypt=").append(encrypt);
        sb.append(", encrypt_key='").append(encrypt_key).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
