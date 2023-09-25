package com.mjc.school;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"com.mjc.school", "com.mjc.school.service", "com.mjc.school.repository", "com.mjc.school.controller"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SpringConfiguration {

}
