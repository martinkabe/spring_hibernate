package com.springframework.spring5recipeapp.configuration;

import com.springframework.spring5recipeapp.data.Student;
import com.springframework.spring5recipeapp.utils.PropertyParser;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.io.IOException;

@Configuration
public class StudentConfiguration {

    @Bean
    public Student student(PropertyParser parser) throws IOException {
        Student newStudent = new Student();
        newStudent.setCountryOptions(parser.parse().getCountry());
        newStudent.setLanguageOptions(parser.parse().getLanguage());
        newStudent.setOsOptions(parser.parse().getOperatingSystem());
        return newStudent;
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
