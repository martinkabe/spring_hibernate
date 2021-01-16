package com.springboot.dao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class HibernateCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(HibernateCrudApplication.class, args);
    }
}
