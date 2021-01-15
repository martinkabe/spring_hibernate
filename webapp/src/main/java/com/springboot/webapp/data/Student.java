package com.springboot.webapp.data;

import com.springboot.webapp.validation.CourseCode;
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
@NoArgsConstructor
public class Student implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id", unique = true, nullable = false)
    private int studentId;

    @Column(name = "first_name")
    @NotNull(message = "{validation.student.name.NotNull}")
    @Size(min=3, max=20, message = "The size of the field should be {min}-{max}")
    private String firstName;

    @Column(name = "last_name")
    @NotNull(message = "{validation.student.name.NotNull}")
    @Size(min=3, max=20, message = "The size of the field should be {min}-{max}")
    private String lastName;

    @Column(name = "age")
    @Min(value=0, message = "{validation.student.age.MinValue}")
    @Max(value=999, message = "{validation.student.age.MaxValue}")
    @NotNull(message = "{validation.student.age.NotNull}")
    private Integer age;

    @Column(name = "postal_code")
    @NotBlank(message = "{validation.student.postalCode.NotBlank}")
    @Pattern(regexp = "^\\d{3}[ ]?\\d{2}", message = "{validation.student.postalCode.chars}")
    private String postalCode;

    @Column(name = "course_code")
    @CourseCode(value="MAR", message="field must start with MAR")
    private String courseCode;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "language_name")
    private String languageName;

    @Column(name = "os_name")
    private String osName;
}