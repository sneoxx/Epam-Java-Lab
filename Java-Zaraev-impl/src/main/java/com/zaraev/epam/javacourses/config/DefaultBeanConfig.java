package com.zaraev.epam.javacourses.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Profile("default")
@Configuration
public class DefaultBeanConfig {

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory("WER");
    }
}
