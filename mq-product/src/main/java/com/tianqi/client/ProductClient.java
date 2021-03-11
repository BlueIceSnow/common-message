package com.tianqi.client;

import com.tianqi.api.domain.callback.SendCallback;
import com.tianqi.api.domain.message.Message;
import com.tianqi.api.product.MessageProduct;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息发送实现
 *
 * @author yuantianqi
 */
@Component
public class ProductClient implements MessageProduct {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendMessage(Message message) {

//        rabbitTemplate.convertAndSend();
    }

    @Override
    public void sendConfirmMessage(Message message, SendCallback callback) {

    }

    @Override
    public void sendReliableMessage(Message message) {

    }

    @Override
    public void sendDelayMessage(Message message) {

    }
}
