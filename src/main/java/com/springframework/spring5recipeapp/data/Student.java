package com.springframework.spring5recipeapp.data;

import com.springframework.spring5recipeapp.validation.CourseCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity(name = "Student")
@Table(name = "student")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

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

    @CourseCode(value="MAR", message="field must start with MAR")
    private String courseCode;

    private String countryName;
    private String languageName;
    private String osName;
}
