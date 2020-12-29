package com.springframework.spring5recipeapp.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.LinkedHashMap;
import java.util.List;

@Configuration
public class StudentConfiguration {

    @Value("#{${country}}")
    private LinkedHashMap<String,String> country;

    @Value("#{${language}}")
    private LinkedHashMap<String, String> language;

    @Value("#{'${operatingSystem}'.split(',')}")
    private List<String> operatingSystem;

    @Bean
    public LinkedHashMap<String, String> countryOptions() {
        return country;
    }

    @Bean
    public LinkedHashMap<String, String> languageOptions() {
        return language;
    }

    @Bean
    public List<String> osOptions() {
        return operatingSystem;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
}
