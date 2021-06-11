package com.zaraev.epam.javacourses.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * Конфигурационный JPA класс для работы с БД
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.zaraev.epam.javacourses.repository"})
public class JpaConfig {

}