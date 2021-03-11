package com.tianqi.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author yuantianqi
 */
@ConfigurationProperties(prefix = "custom.mq")
@Data
public class ConfigProperties {
    private String addr;
}
