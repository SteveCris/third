package com.shangyong.thjdq.mq;

import com.shangyong.rest.feign.MqCloudHystrixService;
import com.shangyong.rest.mq.dto.MqDto;
import com.shangyong.rest.mq.vo.MqResult;
import com.shangyong.thjdq.config.properties.JdqCommonProperties;
import com.shangyong.thjdq.constants.JdqConstant;
import com.shangyong.thjdq.enums.RabbitMqEnum;
import com.shangyong.thjdq.util.JacksonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by ybds on 2019-03-19.
 */
@Component
public class MqSender {

    private static Logger logger = LoggerFactory.getLogger(MqSender.class);

    @Autowired
    private MqCloudHystrixService mqCloudHystrixService;
    @Autowired
    private JdqCommonProperties jdqCommonProperties;

    public boolean sendMq(RabbitMqEnum routingKeyEnum, Object body, boolean isConfirm) {
        return sendMq(routingKeyEnum.getVirtualHost(), routingKeyEnum.getExchange(), getRoutingKeyByRule(routingKeyEnum.getRoutingKey(), routingKeyEnum.getRoutingKeyRule()),
                body, isConfirm);
    }

//    /**
//     * 发送mqtt队列（推送发送单个用户）
//     *
//     */
//    public boolean sendMqtt(String appid, String uid, MqttPayloadEnum mqttPayloadEnum) {
//        MqttMessage body = new MqttMessage();
//        body.setKey(Constants.MQTT_TOPIC_CLIENTS + appid + "/" + uid);
//        body.setPayload(JacksonUtil.parseToJsonString(mqttPayloadEnum.with()));
//        body.setQos("1");
//        return sendMq(RoutingKeyEnum.R_MQTT, body, true);
//    }
//
//    /**
//     * 发送mqtt队列（推送全部用户）
//     *
//     */
//    public boolean sendMqttAllUser(String appid, Integer appName, MqttPayloadEnum mqttPayloadEnum) {
//        MqttMessage body = new MqttMessage();
//        body.setKey(appid + "_" + appName + Constants.MQTT_TOPIC_SHARETOPIC);
//        body.setPayload(JacksonUtil.parseToJsonString(mqttPayloadEnum.with()));
//        body.setQos("1");
//        return sendMq(RoutingKeyEnum.R_MQTT, body, true);
//    }

//    /***
//     * 发送极光消息队列
//     *
//     * @param body
//     * @return
//     */
//    public boolean sendJPush(ScJpushReqinfo body) {
//        return sendMq(RoutingKeyEnum.R_JPUSH, body, true);
//    }
//
//    public boolean sendDingDingMsg(String content) {
//        return sendDingDingMsg(null, null, content);
//    }
//
//    public boolean sendDingDingMsg(String appid, String content) {
//        return sendDingDingMsg(appid, null, content);
//    }
//
//    public boolean sendDingDingMsg(Integer appName, String content) {
//        return sendDingDingMsg(null, appName, content);
//    }
//
    /**
     * 发送到群的钉钉报警队列
     *
     */
//    public boolean sendDingDingMsg(String appid, Integer appName, String content) {
//        DingReqInfo reqInfo = new DingReqInfo();
//        reqInfo.setMessageId(UUIDUtils.getUUID());
//        reqInfo.setMsgType(4);
//        String env = PropertiesUtil.get("current_env");
//        StringBuilder msgContent = new StringBuilder("(" + env + ")");
//        if (appid != null) {
//            msgContent.append("appid:" + appid);
//        }
//        if (appName != null) {
//            msgContent.append("appName:" + appName);
//        }
//        msgContent.append(content);
//        reqInfo.setMsgContent(msgContent.toString());
//        return sendMq(RoutingKeyEnum.R_DINGDIND, reqInfo, true);
//    }

    private boolean sendMq(String virtualHost, String exchange, String routingKey, Object body, boolean isConfirm) {
        String bodyStr = JacksonUtil.parseToJsonString(body);
        logger.info("sendMq,virtualHost:{},exchange:{},routingKey:{},body:{},isConfirm:{}", virtualHost, exchange,
                routingKey, bodyStr, isConfirm);

        MqResult mqResult;
        MqDto mqDto = new MqDto();
        mqDto.setVirtualHost(virtualHost);
        mqDto.setExchange(exchange);
        mqDto.setRoutingKey(routingKey);
        mqDto.setMsg(bodyStr);
        if (isConfirm) {
            mqResult = mqCloudHystrixService.sendAndConfirm(mqDto);
        } else {
            mqResult = mqCloudHystrixService.send(mqDto);
        }

        if (mqResult == null) {
            logger.error("send服务器 发生熔断了，请尽快查看!!!");
            return false;
        }

        try {
            logger.info("respone:{}", mqResult);
            return mqResult.isSuccess();
        } catch (Exception e) {
            logger.error("发送失败：virtualHost：{},exchange：{},routingKey：{},msg：{}", virtualHost, exchange, routingKey,
                    bodyStr, e);
            return false;
        }
    }

    /**
     * 路由key替换规则
     * @param routingKey
     * @param rule
     * @return
     */
    private String getRoutingKeyByRule(String routingKey, int rule) {
        // 0:无规则；1：替换#为appid
        if (rule == 1) {
            return routingKey.replace("#",jdqCommonProperties.getRiskAppId());
        }
        return routingKey;
    }

}
