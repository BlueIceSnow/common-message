package com.tianqi.common.factory;

import com.tianqi.api.domain.callback.SendCallback;
import com.tianqi.api.domain.message.Message;
import com.tianqi.api.domain.type.MessageType;
import com.tianqi.api.product.MessageProduct;
import com.tianqi.common.factory.wrap.RocketMqTemplateWrap;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * rocketmq生产者
 *
 * @author yuantianqi
 */
public class RocketMqProduct implements MessageProduct {

    private RocketMQTemplate rocketMQTemplate;

    public RocketMqProduct(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
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
        sendKernel(message, new org.apache.rocketmq.client.producer.SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                callback.onSuccess(sendResult.getMsgId(), sendResult.getTransactionId());
            }

            @Override
            public void onException(Throwable throwable) {
                callback.onFail(throwable.getMessage(), throwable.getLocalizedMessage());
            }
        });
    }

    /**
     * 发送可靠消息
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
        sendKernel(message, null);
    }

    /**
     * 发送消息核心方法
     *
     * @param message
     */
    private void sendKernel(Message message, org.apache.rocketmq.client.producer.SendCallback sendCallback) {
        RocketMqTemplateWrap rabbitMqTemplateWrap = new RocketMqTemplateWrap(rocketMQTemplate, message.getMessageType());
        rabbitMqTemplateWrap.sendMessage(message, sendCallback, rocketMQTemplate.getProducer().getSendMsgTimeout(), 1);
    }
}
