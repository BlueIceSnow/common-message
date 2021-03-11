package com.tianqi.common.factory.wrap;

import com.tianqi.api.domain.message.Message;
import com.tianqi.api.domain.type.MessageType;
import com.tianqi.common.factory.convert.RocketMqMessageConverter;
import com.tianqi.common.factory.serialize.JacksonSerializer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * rocketMQ的Template包装类
 *
 * @author yuantianqi
 */
public class RocketMqTemplateWrap {
    private static final Map<MessageType, RocketMQTemplate> delegates = new ConcurrentHashMap<>();
    private final RocketMQTemplate rocketMQTemplate;
    private final MessageType messageType;

    /**
     * 封装基础内容
     *
     * @param rocketMQTemplate
     * @param messageType
     */
    public RocketMqTemplateWrap(RocketMQTemplate rocketMQTemplate, MessageType messageType) {

        this.messageType = messageType;
        if (delegates.get(messageType) == null) {
            RocketMQTemplate template = new RocketMQTemplate();
            template.setConsumer(rocketMQTemplate.getConsumer());
            template.setProducer(rocketMQTemplate.getProducer());
            template.setMessageConverter(new RocketMqMessageConverter(new JacksonSerializer(Message.class)));
            delegates.putIfAbsent(messageType, template);
        }
        this.rocketMQTemplate = delegates.get(messageType);
    }

    /**
     * 封装发送方法
     *
     * @param message
     * @param sendCallback
     */
    public void sendMessage(Message message, SendCallback sendCallback, long timeout, int delayLevel) {
        switch (message.getMessageType()) {
            case SIMPLE:
                rocketMQTemplate.syncSend(message.getTopic() + ":" + message.getRoutingKey(), message);
                break;
            case CONFIRM:
                rocketMQTemplate.asyncSend(message.getTopic() + ":" + message.getRoutingKey(), message, sendCallback);
                break;
            case RELIABLE:
                break;
            case DELAY:
                rocketMQTemplate.syncSend(message.getTopic() + ":" + message.getRoutingKey(), MessageBuilder.withPayload(message).build()
                        , timeout, delayLevel);
                break;
        }
    }
}
