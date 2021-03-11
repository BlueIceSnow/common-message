package com.tianqi.common.factory;

import com.tianqi.api.domain.callback.SendCallback;
import com.tianqi.api.domain.message.Message;
import com.tianqi.api.domain.type.MessageType;
import com.tianqi.api.product.MessageProduct;
import com.tianqi.common.factory.serialize.JacksonSerializer;
import com.tianqi.common.factory.wrap.RabbitMqTemplateWrap;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * rabbitmq生产者
 *
 * @author yuantianqi
 */
public class RabbitMqProduct implements MessageProduct {

    private RabbitTemplate rabbitTemplate;

    public RabbitMqProduct(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * 发送简单消息
     *
     * @param message 消息体
     */
    @Override
    public void sendMessage(Message message) {
        message.setMessageType(MessageType.SIMPLE);
        sendKernel(message, null);
    }

    /**
     * 发送确认消息
     *
     * @param message  消息体
     * @param callback 消息回调
     */
    @Override
    public void sendConfirmMessage(Message message, SendCallback callback) {
        message.setMessageType(MessageType.CONFIRM);
        RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if (ack) {
                    callback.onSuccess(correlationData.getId(), cause);
                } else {
                    callback.onFail(correlationData.getId(), cause);
                }
            }
        };
        sendKernel(message, confirmCallback);
    }

    /**
     * 发送可靠性消息
     *
     * @param message 消息体
     */
    @Override
    public void sendReliableMessage(Message message) {
        message.setMessageType(MessageType.RELIABLE);
        sendKernel(message, null);
    }

    /**
     * 发送延迟消息
     *
     * @param message
     */
    @Override
    public void sendDelayMessage(Message message) {
        message.setMessageType(MessageType.DELAY);
        message.setDelayMillisecond(message.getDelayMillisecond());
        sendDelayMessage(message);
    }

    /**
     * 发送消息核心方法
     *
     * @param message
     */
    private void sendKernel(Message message, RabbitTemplate.ConfirmCallback confirmCallback) {
        RabbitMqTemplateWrap rabbitMqTemplateWrap = new RabbitMqTemplateWrap(message.getMessageType()
                , rabbitTemplate, confirmCallback);
        rabbitMqTemplateWrap.sendMessage(message);
    }
}
