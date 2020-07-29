package com.lovefn.grace.tiny.autoconfigure.application;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * to init Application Properties
 */
@PropertySource("classpath:/autoconfigure/application/default.yml")
@EnableConfigurationProperties(BootApplicationProperties.class)
public class BootApplicationConfiguration {
}
