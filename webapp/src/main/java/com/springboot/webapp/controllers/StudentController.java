package com.springboot.webapp.controllers;

import com.springboot.dao.data.PreFilledFormAttributes;
import com.springboot.dao.data.Student;
import com.springboot.dao.repository.DataQueries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final DataQueries dataQueries;

    public StudentController(Student student, PreFilledFormAttributes formAttributes, DataQueries dataQueries) {
        this.student = student;
        this.formAttributes = formAttributes;
        this.dataQueries = dataQueries;
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

    @RequestMapping(value = "/processForm", method={ RequestMethod.POST })
    public String processForm(@Valid @ModelAttribute("student") Student theStudent,
                              BindingResult theBindingResult,
                              Model model) {
        log.info("BindingResult: {}", theBindingResult);
        if (theBindingResult.hasErrors()) {
            model.addAttribute("student", theStudent);
            model.addAttribute("formAttributes", formAttributes);
            return "student-form";
        } else {
            model.addAttribute("student", theStudent);
            dataQueries.insertEntityHibernate(theStudent, Student.class);
            return "student-confirmation";
        }
    }
}
