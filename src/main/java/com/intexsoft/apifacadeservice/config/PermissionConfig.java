package com.intexsoft.apifacadeservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Permissions config
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "permission")
public class PermissionConfig {

    private String employeeView;
    private String ratingExceptions;

}
