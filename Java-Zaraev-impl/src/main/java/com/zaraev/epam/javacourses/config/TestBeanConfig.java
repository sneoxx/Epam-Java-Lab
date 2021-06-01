package com.zaraev.epam.javacourses.config;

import com.zaraev.epam.javacourses.service.IService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class TestBeanConfig {
    @Bean
    public IService beanService() {
        return () -> "local bean service";
    }
}
