package com.springboot.dao.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;

@Data
@AllArgsConstructor
@Component
public class PreFilledFormAttributes {
    private LinkedHashMap<String, String> country;
    private LinkedHashMap<String, String> language;
    private List<String> operatingSystem;
}
