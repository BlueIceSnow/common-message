package com.tianqi.mq;

import com.rabbitmq.client.Channel;
import com.sun.org.apache.xml.internal.resolver.readers.TR9401CatalogReader;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author yuantianqi
 */
@Component
public class MessageListener {

    @RabbitListener(
            bindings = {
                    @QueueBinding(
                            exchange = @Exchange(
                                    name = "exchange-1",
                                    type = "topic"
                            ),
                            value = @Queue(
                                    name = "queue01",
                                    durable = "true"
                            ),
                            key = "springboot.*"
                    )
            }
    )
    @RabbitHandler
    public void onMessage(Message message , Channel channel) throws IOException {
        System.out.println(message.getPayload());
//        channel.basicAck((Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG), false);
    }
}
