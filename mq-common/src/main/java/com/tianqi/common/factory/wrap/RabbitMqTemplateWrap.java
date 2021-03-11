package com.tianqi.common.factory.wrap;

import com.tianqi.api.domain.message.Message;
import com.tianqi.api.domain.type.MessageType;
import com.tianqi.common.factory.convert.RabbitMqMessageConverter;
import com.tianqi.common.factory.serialize.JacksonSerializer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * rabbit mq的消息包装类
 *
 * @author yuantianqi
 */
public class RabbitMqTemplateWrap {
    private static final Map<MessageType, RabbitTemplate> delegates = new ConcurrentHashMap<>();
    private final RabbitTemplate rabbitTemplate;
    private final MessageType messageType;


    /**
     * 消息发送模板包装类
     *
     * @param messageType
     * @param rabbitTemplate
     * @param confirmCallback
     */
    public RabbitMqTemplateWrap(MessageType messageType, RabbitTemplate rabbitTemplate, RabbitTemplate.ConfirmCallback confirmCallback) {
        this.messageType = messageType;
        if (!Optional.ofNullable(delegates.get(messageType)).isPresent()) {
            ConnectionFactory connectionFactory = rabbitTemplate.getConnectionFactory();
            RabbitTemplate template = new RabbitTemplate(connectionFactory);
            template.setMessageConverter(new RabbitMqMessageConverter(new JacksonSerializer(Message.class)));
            if (!Optional.ofNullable(confirmCallback).isPresent()) {
                template.setConfirmCallback(confirmCallback);
            }
            delegates.putIfAbsent(messageType, template);
        }
        this.rabbitTemplate = delegates.get(messageType);
    }

    /**
     * 发送消息，基础包装类，无回调函数
     */
    public void sendMessage(Message message) {
        CorrelationData correlationData = new CorrelationData(message.getMessageId());
        rabbitTemplate.convertAndSend(message.getTopic(), message.getRoutingKey(), message, correlationData);
    }


}
