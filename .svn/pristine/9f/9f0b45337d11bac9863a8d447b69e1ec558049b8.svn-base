package com.shangyong.thjdq.enums;

/**
 * rabbitMQ队列，交换机,路由key枚举
 * Created by zbb on 2019-03-15.
 */
public enum RabbitMqEnum {

    PUSH_PHASE_ONE(RabbitMqEnum.THIRD_VIRTUAL_HOST, "ex.event.jdq.push","direct", "push.jdq.rKey","queue.event.jdq.one.push",0),
    PUSH_PHASE_TWO(RabbitMqEnum.THIRD_VIRTUAL_HOST, "ex.event.jdq.push","direct", "push.jdq.two.rKey","queue.event.jdq.two.push",0),
    PUSH_PHASE_OTHER(RabbitMqEnum.THIRD_VIRTUAL_HOST, "ex.event.jdq.push","direct", "push.jdq.other.rKey","queue.event.jdq.other.push",0),
    PUSH_PHASE_RISK(RabbitMqEnum.THIRD_VIRTUAL_HOST, "ex.event.jdq.push","direct", "push.jdq.risk.rKey","queue.event.jdq.risk.push",0),
    AUDIT_EVENT(RabbitMqEnum.THIRD_VIRTUAL_HOST, "ex.event.audit","topic", "audit.#.rKey","queue.event.audit.process",1),
    ORDER_PUSH(RabbitMqEnum.THIRD_VIRTUAL_HOST, "ex.event.order.push","fanout", "order.push.rKey","queue.event.order.push",0);

    /**
     * 第三方接入产品平台虚拟机
     */
    public static final String THIRD_VIRTUAL_HOST = "/third";

    RabbitMqEnum(String virtualHost, String exchange, String exchangeType, String routingKey,
                 String queueName, int routingKeyRule) {
        this.virtualHost = virtualHost;
        this.exchange = exchange;
        this.exchangeType = exchangeType;
        this.routingKey = routingKey;
        this.queueName = queueName;
        this.routingKeyRule = routingKeyRule;
    }

    private String virtualHost; // 虚拟机
    private String exchange; // 交换机
    private String exchangeType;// 交换机类型
    private String routingKey; // 路由键
    private String queueName; // 队列名称
    private int routingKeyRule; // 路由键规则 0:无规则；1：替换#为appid

    public String getVirtualHost() {
        return virtualHost;
    }

    public void setVirtualHost(String virtualHost) {
        this.virtualHost = virtualHost;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(String exchangeType) {
        this.exchangeType = exchangeType;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public int getRoutingKeyRule() {
        return routingKeyRule;
    }

    public void setRoutingKeyRule(int routingKeyRule) {
        this.routingKeyRule = routingKeyRule;
    }
}
