package com.springframework.spring5recipeapp.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "form")
public class FormAttributes {
    private String country;
    private String language;
    private List<String> operatingSystem;
}
