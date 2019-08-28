package com.shangyong.thjdq.util;

/**
 * Created by ybds on 2017/11/6.
 */
public class IpBlackListUtil {

    private static final IpBlackListUtil ipBlackListUtil = new IpBlackListUtil();

    //私有的构造方法，避免外界创建实例
    private IpBlackListUtil(){
    }

    public static IpBlackListUtil getInstance(){
        return ipBlackListUtil;
    }

    /**
     * 白名单ip
     */
    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
