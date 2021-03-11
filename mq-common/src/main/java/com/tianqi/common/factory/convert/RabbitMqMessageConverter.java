package com.tianqi.common.factory.convert;

import com.tianqi.common.factory.serialize.Serialize;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;

/**
 * RabbitMQ的消息转换类
 *
 * @author yuantianqi
 */
public class RabbitMqMessageConverter extends com.tianqi.common.factory.convert.MessageConverter implements MessageConverter {


    public RabbitMqMessageConverter(Serialize serialize) {
        super(serialize);
    }

    /**
     * 自定义消息转为rabbitMQ消息
     * @param messageObj
     * @param messageProperties
     * @return
     * @throws MessageConversionException
     */
    @Override
    public Message toMessage(Object messageObj, MessageProperties messageProperties) throws MessageConversionException {
        return new Message(serialize.serializeRaw(messageObj), messageProperties);
    }

    /**
     * 从rabbitMQ消息转为自定义消息
     * @param message
     * @return
     * @throws MessageConversionException
     */
    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        return serialize.deserialize(message.getBody());
    }
}
