package com.intexsoft.apifacadeservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Roles config
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "role")
public class RoleConfig {

    private String ekAdmin;
    private String ekEmployeeManager;
    private String ekMarketingManager;
    private String ekProjectManager;
    private String ekUser;

}
