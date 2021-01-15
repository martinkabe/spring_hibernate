package com.springboot.helper.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelperConfiguration {

    @Bean
    public Helper getHelp() {
        return new Texter1();
    }
}
