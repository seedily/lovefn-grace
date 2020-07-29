package com.lovefn.grace.tiny.autoconfigure.metrics;


import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * for metrics
 */
@PropertySource("classpath:/autoconfigure/metrics/default.properties")
@Configuration
@ConditionalOnClass({MeterRegistry.class, TimedAspect.class})
public class MetricsAutoConfiguration {


}
