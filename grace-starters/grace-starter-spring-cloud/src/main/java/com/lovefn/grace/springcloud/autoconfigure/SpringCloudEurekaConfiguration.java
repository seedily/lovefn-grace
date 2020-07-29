package com.lovefn.grace.springcloud.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * to init Application Properties
 */
@PropertySource("classpath:/autoconfigure/eureka/default.yml")
@Configuration
@ConditionalOnClass({com.netflix.eureka.EurekaServerConfig.class})
@ConditionalOnProperty(name = "eureka.client.serviceUrl.defaultZone")
public class SpringCloudEurekaConfiguration {
}
