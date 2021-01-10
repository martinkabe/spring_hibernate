package com.springframework.spring5recipeapp.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springframework.spring5recipeapp.data.Employee;
import com.springframework.spring5recipeapp.data.FormAttributes;
import com.springframework.spring5recipeapp.data.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@PropertySource("classpath:forms.properties")
public class StudentConfiguration {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource(DbConnProperties properties) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(properties.getDriverClassName());
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
    public Student student() throws IOException {
        Student newStudent = new Student();
        newStudent.setCountryOptions(formAttributes().getCountry());
        newStudent.setLanguageOptions(formAttributes().getLanguage());
        newStudent.setOsOptions(formAttributes().getOperatingSystem());
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

    @Bean
    public FormAttributes formAttributes() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return new FormAttributes(mapper.readValue(env.getProperty("form.country"),
                new TypeReference<LinkedHashMap<String, String>>(){}),
                mapper.readValue(env.getProperty("form.language"),
                        new TypeReference<LinkedHashMap<String, String>>(){}),
                Arrays.asList(env.getProperty("form.operatingSystem").split(",")));
    }

    @Bean
    public MetadataSources getMetaSource(DbConnProperties properties) {
        Map<String, String> settings = new HashMap<>();
        settings.put("connection.driver_class", properties.getDriverClassName());
        settings.put("dialect", properties.getDialect());
        settings.put("hibernate.connection.url", properties.getUrl());
        settings.put("hibernate.connection.username", properties.getUsername());
        settings.put("hibernate.connection.password", properties.getPassword());
        settings.put("hibernate.current_session_context_class", properties.getCurrentSessionContextClass());
        settings.put("hibernate.show_sql", properties.getShowSql());
        settings.put("hibernate.format_sql", properties.getFormatSql());

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(settings).build();

        return new MetadataSources(serviceRegistry);
    }
}
