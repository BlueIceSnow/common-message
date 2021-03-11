package com.tianqi.common.factory;

import com.tianqi.api.domain.type.ProductType;
import com.tianqi.api.product.MessageProduct;
import com.tianqi.common.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Optional;

/**
 * 生产者工厂
 *
 * @author yuantianqi
 */
@Slf4j
public class ProductFactory {


    /**
     * 创建生产者
     *
     * @param productType
     * @return
     */
    public static MessageProduct createProduct(ProductType productType) {
        productType = Optional.ofNullable(productType).orElseGet(() -> {
            log.error("参数入参为null");
            return ProductType.RABBIT_MQ;
        });
        switch (productType) {
            case RABBIT_MQ:
                return new RabbitMqProduct(SpringUtil.getBean(RabbitTemplate.class));
            case ROCKET_MQ:
                return new RocketMqProduct(SpringUtil.getBean(RocketMQTemplate.class));
            default:
                return null;
        }
    }
}
