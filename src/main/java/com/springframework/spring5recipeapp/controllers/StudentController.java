package com.springframework.spring5recipeapp.controllers;

import com.springframework.spring5recipeapp.data.Student;
import org.springframework.beans.BeanUtils;
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
    private final Student student;

    public StudentController(Student student) {
        this.student = student;
    }

    @InitBinder
    public void initialBinderForTrimmingSpaces(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimEditor);
    }

    @RequestMapping(value = "/showForm", method=RequestMethod.GET)
    public String showForm(Model theModel) {
        theModel.addAttribute("student", student);
        return "student-form";
    }

    @RequestMapping(value = "/processForm", method=RequestMethod.POST)
    public String processForm(@Valid @ModelAttribute("student") Student theStudent,
                              BindingResult theBindingResult,
                              Model model) {
        if (theBindingResult.hasErrors()) {
            BeanUtils.copyProperties(student, theStudent);
            model.addAttribute("student", theStudent);
            return "student-form";
        } else {
            model.addAttribute("student", theStudent);
            return "student-confirmation";
        }
    }
}
