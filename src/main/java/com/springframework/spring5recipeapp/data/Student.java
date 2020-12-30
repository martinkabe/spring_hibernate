package com.springframework.spring5recipeapp.data;

import com.springframework.spring5recipeapp.utils.PropertyParser;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashMap;
import java.util.List;

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

    private LinkedHashMap<String, String> countryOptions;
    private LinkedHashMap<String, String> languageOptions;
    private List<String> osOptions;
}
