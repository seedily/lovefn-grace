package com.lovefn.grace.common.service.autoconfigure;

import com.lovefn.grace.common.service.template.ServiceTemplate;
import com.lovefn.grace.common.service.template.ServiceTemplateImpl;
import com.lovefn.grace.common.service.template.TransactionServiceTemplate;
import com.lovefn.grace.common.service.template.TransactionServiceTemplateImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

/**
 * TransactionServiceTemplate AutoConfigure
 */
@Slf4j
@Configuration
@ConditionalOnClass({DataSource.class, TransactionTemplate.class})
public class TransactionServiceTemplateAutoConfigure {

    /**
     * Init TransactionServiceTemplate.
     *
     * @param transactionTemplate : Spring声明数据源时会顺带声明TransactionTemplate
     * @return
     */
    @Bean
    @ConditionalOnBean({DataSource.class, TransactionTemplate.class})
    @ConditionalOnMissingBean
    public TransactionServiceTemplate serviceTemplate(TransactionTemplate transactionTemplate) {
        log.info("Initializing TransactionServiceTemplate");
        TransactionServiceTemplateImpl serviceTemplate = new TransactionServiceTemplateImpl();
        serviceTemplate.setTransactionTemplate(transactionTemplate);
        return serviceTemplate;
    }
}
