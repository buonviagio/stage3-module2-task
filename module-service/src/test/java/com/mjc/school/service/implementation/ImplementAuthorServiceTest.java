package com.mjc.school.service.implementation;

import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
@SpringJUnitConfig
class ImplementAuthorServiceTest {
    Random random = new Random();
    @Autowired
    private BaseService<NewsDtoRequest, NewsDtoResponse, Long> newsService;

    @Test
    void findAllReturnsNews() {
        List<NewsDtoResponse> readAllList = newsService.readAll();

        for (NewsDtoResponse news : readAllList) {
            System.out.println(news);
        }

        assertFalse(readAllList.isEmpty());

    }


    @Configuration
    @EnableAspectJAutoProxy(proxyTargetClass = true)
    @ComponentScan(basePackages = {
            "com.mjc.school.service",
            "com.mjc.school.repository",
    })
    static class SpringConfigurationTest {

    }
}