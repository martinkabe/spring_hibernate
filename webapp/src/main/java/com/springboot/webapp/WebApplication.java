package com.springboot.webapp;

import com.springboot.hcrud.configuration.DataConfiguration;
import com.springboot.hcrud.configuration.DbConnProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableConfigurationProperties
@Import({ DataConfiguration.class, DbConnProperties.class })
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

}
