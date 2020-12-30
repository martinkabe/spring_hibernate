package com.springframework.spring5recipeapp.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springframework.spring5recipeapp.configuration.FormAttributes;
import com.springframework.spring5recipeapp.data.Attributes;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedHashMap;

@Component
public class PropertyParser {

    private final FormAttributes formAttributes;

    public PropertyParser(FormAttributes formAttributes) {
        this.formAttributes = formAttributes;
    }

    public Attributes parse() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return Attributes.builder()
                .country(mapper.readValue(formAttributes.getCountry(),
                        new TypeReference<LinkedHashMap<String, String>>(){}))
                .language(mapper.readValue(formAttributes.getLanguage(),
                        new TypeReference<LinkedHashMap<String, String>>(){}))
                .operatingSystem(formAttributes.getOperatingSystem())
                .build();
    }
}
