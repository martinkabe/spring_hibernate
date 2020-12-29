package com.springframework.spring5recipeapp.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
