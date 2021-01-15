package com.springboot.helper.spring;

import org.springframework.stereotype.Component;

@Component
public class Texter1 implements Helper {
    @Override
    public void saySomething(String text) {
        System.out.println("adding ... " + text);
    }
}
