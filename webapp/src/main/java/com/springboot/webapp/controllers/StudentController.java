package com.springboot.webapp.controllers;

import com.springboot.helper.spring.Helper;
import com.springboot.webapp.data.PreFilledFormAttributes;
import com.springboot.webapp.data.Student;
import com.springboot.webapp.repository.EmployeeQueries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final Logger log = LoggerFactory.getLogger(StudentController.class);

    private final Student student;
    private final PreFilledFormAttributes formAttributes;
    private final EmployeeQueries query;

    @Autowired
    private Helper helper;

    public StudentController(Student student, PreFilledFormAttributes formAttributes, EmployeeQueries query) {
        this.student = student;
        this.formAttributes = formAttributes;
        this.query = query;
    }

    @InitBinder
    public void initialBinderForTrimmingSpaces(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimEditor);
    }

    @RequestMapping(value = "/showForm", method=RequestMethod.GET)
    public String showForm(Model theModel) {
        theModel.addAttribute("student", student);
        theModel.addAttribute("formAttributes", formAttributes);
        return "student-form";
    }

    @RequestMapping(value = "/processForm", method=RequestMethod.POST)
    public String processForm(@Valid @ModelAttribute("student") Student theStudent,
                              BindingResult theBindingResult,
                              Model model) {
        log.info("BindingResult: {}", theBindingResult);
        if (theBindingResult.hasErrors()) {
            model.addAttribute("student", theStudent);
            model.addAttribute("formAttributes", formAttributes);
            return "student-form";
        } else {
            helper.saySomething(theStudent.getFirstName());
            model.addAttribute("student", theStudent);
            query.hibernateInsertEntity(theStudent, Student.class);
            return "student-confirmation";
        }
    }
}