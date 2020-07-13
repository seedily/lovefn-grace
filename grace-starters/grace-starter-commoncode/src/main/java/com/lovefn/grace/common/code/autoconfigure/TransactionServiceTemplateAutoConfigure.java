package com.lovefn.grace.common.code.autoconfigure;

import com.lovefn.grace.common.code.template.TransactionServiceTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
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
    public void serviceTemplate(TransactionTemplate transactionTemplate) {
        log.info("Initializing TransactionServiceTemplate");
        TransactionServiceTemplate.setTransactionTemplate(transactionTemplate);
    }
}
