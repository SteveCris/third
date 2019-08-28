package com.shangyong.thjdq.config;

import com.shangyong.loan.autoconfigure.redis.BatchRedisTemplate;
import com.shangyong.thjdq.constants.RedisKeyCoreConstant;
import com.shangyong.thjdq.enums.ActiveProfileEnum;
import com.shangyong.thjdq.enums.ResponseCode;
import com.shangyong.thjdq.handler.exception.JdqSignException;
import com.shangyong.thjdq.util.IpBlackListUtil;
import com.shangyong.thjdq.util.SpringContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 白名单拦截器
 * Created by zhengbb on 2019-07-01.
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(SecurityInterceptor.class);

    @Autowired
    private BatchRedisTemplate batchRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

//        if (!ActiveProfileEnum.PRODUCT.name().equalsIgnoreCase(SpringContextUtils.getActiveProfile())) {
//            return super.preHandle(request, response, handler);
//        }

        // 校验ip白名单
        String ip = getIp(request);
        log.info("访问ip:{}", ip);

        IpBlackListUtil ipBlackListUtil = IpBlackListUtil.getInstance();
        if (ipBlackListUtil.getIp() == null) {
            ipBlackListUtil.setIp(batchRedisTemplate.get(RedisKeyCoreConstant.OTHER_PARAM.IP_BLACK_LIST) == null ? "" : batchRedisTemplate.get(RedisKeyCoreConstant.OTHER_PARAM.IP_BLACK_LIST));
        }

        if (ipBlackListUtil.getIp().equals("**")) {
            return super.preHandle(request, response, handler);
        }

        if (!ipBlackListUtil.getIp().contains("," + ip + ",")) {
            throw new JdqSignException(ResponseCode.IP_NOT_ON_THE_WHITE_LIST);
        }

        return super.preHandle(request, response, handler);
    }

    private static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        ip = "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
        if (ip != null) {
            ip = ip.split(",")[0];
        } else {
            ip = "";
        }
        return ip;
    }

}
