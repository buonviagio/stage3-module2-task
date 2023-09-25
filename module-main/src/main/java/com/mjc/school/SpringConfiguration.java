package com.mjc.school;

import com.mjc.school.service.aspect.NewsAuthorValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "com.mjc.school")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SpringConfiguration {

}
