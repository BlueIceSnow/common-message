package com.tianqi.rest;

import com.tianqi.api.domain.builder.MessageBuilder;
import com.tianqi.api.domain.callback.SendCallback;
import com.tianqi.api.domain.message.Message;
import com.tianqi.api.domain.type.ProductType;
import com.tianqi.api.product.MessageProduct;
import com.tianqi.common.factory.ProductFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuantianqi
 */
@RestController
public class DemoRest {

    @GetMapping("/send")
    public String sendMessage() {
        MessageProduct product = ProductFactory.createProduct(ProductType.ROCKET_MQ);
        Message build = MessageBuilder.create()
                .withMessageId("1")
                .withTopic("demo")
                .withRoutingKey("tag1")
                .build();
        product.sendMessage(build);
        MessageProduct rabbitMq = ProductFactory.createProduct(ProductType.RABBIT_MQ);
        rabbitMq.sendMessage(build);
        return null;
    }
}
