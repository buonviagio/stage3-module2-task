package com.mjc.school;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        //StartApp startApp = context.getBean(StartApp.class);
        StartApp startApp = context.getBean("startApp", StartApp.class);
        startApp.run();
    }
}
