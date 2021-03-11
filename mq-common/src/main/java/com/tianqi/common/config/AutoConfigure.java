package com.tianqi.common.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuantianqi
 */
@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties(ConfigProperties.class)
public class AutoConfigure {

    @Bean
    public String getProperties(ConfigProperties configProperties){
        return configProperties.getAddr();
    }
}
