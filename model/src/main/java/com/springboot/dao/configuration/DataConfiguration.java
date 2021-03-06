package com.springboot.dao.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.dao.data.PreFilledFormAttributes;
import com.springboot.dao.data.Student;
import com.springboot.dao.repository.DataQueries;
import com.springboot.dao.repository.DataService;
import com.springboot.dao.repository.EmployeeJpaRepository;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.persistence.EntityManagerFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@PropertySource("classpath:forms.properties")
public class DataConfiguration {

    @Bean
    public DataQueries dataQueries(JdbcTemplate jdbcTemplate,
                                   MetadataSources metadataSources,
                                   EntityManagerFactory entityManagerFactory,
                                   EmployeeJpaRepository employeeJpaRepository) {
        return new DataService(jdbcTemplate, metadataSources, entityManagerFactory, employeeJpaRepository);
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

    @Bean
    public Connection getProperties(DbConnProperties properties) throws SQLException {
        return DriverManager.getConnection(properties.getUrl(),
                properties.getUsername(),
                properties.getUsername());
    }

    @Bean
    public Student student() {
        return new Student();
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
    public PreFilledFormAttributes formAttributes(Environment env) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return new PreFilledFormAttributes(mapper.readValue(env.getProperty("form.country"),
                new TypeReference<LinkedHashMap<String, String>>(){}),
                mapper.readValue(env.getProperty("form.language"),
                        new TypeReference<LinkedHashMap<String, String>>(){}),
                Arrays.asList(env.getProperty("form.operatingSystem").split(",")));
    }
}
