package com.springframework.spring5recipeapp.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashMap;
import java.util.List;

@Component
@NoArgsConstructor
@Data
public class Student {

    @NotEmpty(message = "{validation.name.NotEmpty}")
    @NotNull(message = "{validation.name.NutNull}")
    @Size(min=3, max=20, message = "The size of the field should be {min}-{max}")
    private String firstName;

    @NotEmpty(message = "{validation.name.NotEmpty}")
    @NotNull(message = "{validation.name.NutNull}")
    @Size(min=3, max=20, message = "The size of the field should be {min}-{max}")
    private String lastName;

    private String countryName;
    private String languageName;
    private List<String> osName;

    @Autowired
    private LinkedHashMap<String, String> countryOptions;

    @Autowired
    private LinkedHashMap<String, String> languageOptions;

    @Autowired
    private List<String> osOptions;
}
