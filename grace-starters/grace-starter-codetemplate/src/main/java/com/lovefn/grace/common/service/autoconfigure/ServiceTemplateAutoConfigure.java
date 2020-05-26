package com.lovefn.grace.common.service.autoconfigure;

import com.lovefn.grace.common.service.template.ServiceTemplate;
import com.lovefn.grace.common.service.template.ServiceTemplateImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ServiceTemplate AutoConfigure
 */
@Slf4j
@Configuration
public class ServiceTemplateAutoConfigure {

    /**
     * Init ServiceTemplate.
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public ServiceTemplate serviceTemplate() {
        log.info("Initializing ServiceTemplate");
        ServiceTemplate serviceTemplate = new ServiceTemplateImpl();
        return serviceTemplate;
    }

}
