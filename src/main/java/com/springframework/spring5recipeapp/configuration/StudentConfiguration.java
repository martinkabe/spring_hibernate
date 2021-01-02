package com.springframework.spring5recipeapp.configuration;

import com.springframework.spring5recipeapp.data.Attributes;
import com.springframework.spring5recipeapp.data.Student;
import com.springframework.spring5recipeapp.utils.PropertyParser;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class StudentConfiguration {

    @Bean
    public DataSource dataSource(DbConnProperties properties) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        return dataSource;
    }

    @Bean
    public Connection getProperties(DbConnProperties properties) throws SQLException {
        return DriverManager.getConnection(properties.getUrl(),
                                            properties.getUsername(),
                                            properties.getUsername());
    }

    @Bean
    public Student student(PropertyParser parser) throws IOException {
        Student newStudent = new Student();
        Attributes parsedAttributes = parser.parse();
        newStudent.setCountryOptions(parsedAttributes.getCountry());
        newStudent.setLanguageOptions(parsedAttributes.getLanguage());
        newStudent.setOsOptions(parsedAttributes.getOperatingSystem());
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
