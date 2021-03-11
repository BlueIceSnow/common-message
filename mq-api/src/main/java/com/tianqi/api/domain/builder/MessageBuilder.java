package com.tianqi.api.domain.builder;

import com.tianqi.api.domain.message.Message;
import com.tianqi.api.domain.type.MessageType;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息构建器
 *
 * @author yuantianqi
 */
public class MessageBuilder {

    static final long serialVersionUID = 1000401L;
    /**
     * 消息属性
     */
    private final Map<String, Object> attributes = new HashMap<>();
    /**
     * 消息ID
     */
    private String messageId;
    /**
     * 消息主题
     */
    private String topic;
    private String routingKey;
    /**
     * 消息类型
     */
    private MessageType messageType = MessageType.SIMPLE;

    /**
     * 延迟消息，延迟时间，毫秒级
     */
    private Long delayMillisecond = 0L;

    /**
     * 延迟级别
     */
    private int delayLevel = 0;

    private MessageBuilder() {
    }

    public static MessageBuilder create() {
        return new MessageBuilder();
    }

    public MessageBuilder withMessageId(String messageId) {
        this.messageId = messageId;
        return this;
    }

    public MessageBuilder withTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public MessageBuilder withRoutingKey(String routingKey) {
        this.routingKey = routingKey;
        return this;
    }

    public MessageBuilder withMessageType(MessageType messageType) {
        this.messageType = messageType;
        return this;
    }

    public MessageBuilder withAttribute(String key, String val) {
        this.attributes.put(key, val);
        return this;
    }


    public MessageBuilder withDelayMillisecond(Long delayMillisecond) {
        this.delayMillisecond = delayMillisecond;
        return this;
    }

    public MessageBuilder withDelayLevel(int delayLevel){
        this.delayLevel = delayLevel;
        return this;
    }

    public Message build() {
        return new Message(this.messageId, this.topic, this.routingKey, this.attributes,this.delayMillisecond,this.delayLevel);
    }
}
