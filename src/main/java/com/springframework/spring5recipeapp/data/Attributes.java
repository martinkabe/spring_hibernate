package com.springframework.spring5recipeapp.data;

import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;

@Component
@Builder
@Data
public class Attributes {
    private LinkedHashMap<String, String> country;
    private LinkedHashMap<String, String> language;
    private List<String> operatingSystem;
}
