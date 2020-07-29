package com.lovefn.grace.tiny.autoconfigure.application;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * application common config
 */
@ConfigurationProperties(prefix = "grace.application")
@Data
public class BootApplicationProperties {

    private String id;

    private String name;

    /**
     * environment: test prod ...
     */
    private String env;

    /**
     * 版本
     */
    private String version;

}
