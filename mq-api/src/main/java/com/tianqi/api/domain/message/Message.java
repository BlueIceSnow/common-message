package com.tianqi.api.domain.message;

import com.tianqi.api.domain.type.MessageType;

import java.io.Serializable;
import java.util.Map;

/**
 * MQ发送的消息体
 *
 * @author yuantianqi
 */
public class Message implements Serializable {
    static final long serialVersionUID = 1000401L;
    /**
     * 消息类型
     */
    private MessageType messageType = MessageType.SIMPLE;
    /**
     * 消息ID
     */
    private String messageId;
    /**
     * 消息主题
     */
    private String topic;
    /**
     * 消息路由key
     */
    private String routingKey;
    /**
     * 消息属性
     */
    private Map<String, Object> attributes;

    /**
     * 延迟消息，延迟时间，毫秒级
     */
    private Long delayMillisecond = 0L;

    /**
     * 延迟级别
     */
    private int delayLevel = 0;

    public Message(String messageId, String topic, String routingKey, Map<String, Object> attributes
            , Long delayMillisecond, int delayLevel) {
        this.messageId = messageId;
        this.topic = topic;
        this.routingKey = routingKey;
        this.attributes = attributes;
        this.delayMillisecond = delayMillisecond;
        this.delayLevel = delayLevel;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Long getDelayMillisecond() {
        return delayMillisecond;
    }

    public void setDelayMillisecond(Long delayMillisecond) {
        this.delayMillisecond = delayMillisecond;
    }

    public int getDelayLevel() {
        return delayLevel;
    }

    public void setDelayLevel(int delayLevel) {
        this.delayLevel = delayLevel;
    }
}
