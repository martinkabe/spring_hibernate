package com.springframework.spring5recipeapp.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.List;

@Data
@AllArgsConstructor
public class PreFilledFormAttributes {
    private LinkedHashMap<String, String> country;
    private LinkedHashMap<String, String> language;
    private List<String> operatingSystem;
}
