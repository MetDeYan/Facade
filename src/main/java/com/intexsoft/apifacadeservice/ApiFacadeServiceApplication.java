package com.intexsoft.apifacadeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableFeignClients
@PropertySource("file:./config/api-facade-service-config.properties")
@PropertySource("file:./config/database.properties")
@PropertySource("file:./config/service-url.properties")
@PropertySource("file:./config/permission.properties")
@PropertySource("file:./config/role.properties")
public class ApiFacadeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiFacadeServiceApplication.class, args);
    }

}
