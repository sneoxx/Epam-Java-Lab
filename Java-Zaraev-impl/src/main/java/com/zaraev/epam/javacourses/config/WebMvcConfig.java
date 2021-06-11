package com.zaraev.epam.javacourses.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Конфиг класс для работы с Spring web
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = { "com.zaraev.epam.javacourses" })
public class WebMvcConfig implements WebMvcConfigurer {

}