package com.springboot.webapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;

@Controller
@RequestMapping("/hello")
@Validated
public class HelloWorldController {

    @RequestMapping("/showForm")
    public String showForm() {
        return "hw-form";
    }

    @RequestMapping("/processForm")
    public String processForm() {
        return "helloworld";
    }

    @RequestMapping("/processFormVersionTwo")
    public String letsShoutDude(HttpServletRequest request, Model model) {
        String theName = request.getParameter("studentName");
        theName = theName.toUpperCase();
        String result = "Yo! " + theName;

        model.addAttribute("message", result);

        return "helloworld";
    }

    @RequestMapping("/processFormVersionThree")
    public String processFormVersionThree(@RequestParam("studentName") @NotBlank(message = "This field cannot be blank")
                                                      String theName,
                                          Model model) {
        theName = theName.toUpperCase();
        String result = "Hey My Friend from v3! " + theName;

        model.addAttribute("message", result);

        return "helloworld";
    }
}
