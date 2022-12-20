package com.intexsoft.apifacadeservice.config;

import lombok.extern.slf4j.Slf4j;

import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

import static com.intexsoft.apifacadeservice.config.BasicConstants.AUTHORIZATION_SECURITY_CONFIG_ORDER;

/**
 * Security configuration class using Keycloak.
 */
@Slf4j
@Configuration
@EnableWebSecurity
@Order(AUTHORIZATION_SECURITY_CONFIG_ORDER)
public class WebSecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        super.configure(http);
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated();
    }
}