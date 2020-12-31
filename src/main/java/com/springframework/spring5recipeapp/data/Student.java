package com.springframework.spring5recipeapp.data;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.LinkedHashMap;
import java.util.List;

@Data
public class Student {

    @NotNull(message = "{validation.student.name.NotNull}")
    @Size(min=3, max=20, message = "The size of the field should be {min}-{max}")
    private String firstName;

    @NotNull(message = "{validation.student.name.NotNull}")
    @Size(min=3, max=20, message = "The size of the field should be {min}-{max}")
    private String lastName;

    @Min(value=0, message = "{validation.student.age.MinValue}")
    @Max(value=999, message = "{validation.student.age.MaxValue}")
    @NotNull(message = "{validation.student.age.NotNull}")
    private Integer age;

    @NotBlank(message = "{validation.student.postalCode.NotBlank}")
    @Pattern(regexp = "^\\d{3}[ ]?\\d{2}", message = "{validation.student.postalCode.chars}")
    private String postalCode;

    private String countryName;
    private String languageName;
    private List<String> osName;
    private LinkedHashMap<String, String> countryOptions;
    private LinkedHashMap<String, String> languageOptions;
    private List<String> osOptions;
}
