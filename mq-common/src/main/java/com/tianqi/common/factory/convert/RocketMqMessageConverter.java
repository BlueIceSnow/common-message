package com.tianqi.common.factory.convert;

import com.tianqi.common.factory.serialize.Serialize;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.support.GenericMessage;

/**
 * RocketMQ 的message converter
 *
 * @author yuantianqi
 */
public class RocketMqMessageConverter extends com.tianqi.common.factory.convert.MessageConverter implements MessageConverter {

    public RocketMqMessageConverter(Serialize serialize) {
        super(serialize);
    }

    /**
     * 将RocketMQ消息转为自定义消息
     *
     * @param message
     * @param aClass
     * @return
     */
    @Override
    public Object fromMessage(Message<?> message, Class<?> aClass) {
        return this.serialize.deserialize(String.valueOf(message.getPayload()));
    }

    /**
     * 自定义的消息，转换为rocketMQ消息
     *
     * @param customMessage
     * @param messageHeaders
     * @return
     */
    @Override
    public Message<?> toMessage(Object customMessage, MessageHeaders messageHeaders) {

        return new GenericMessage<Object>(this.serialize.serialize(customMessage), messageHeaders);
    }
}
