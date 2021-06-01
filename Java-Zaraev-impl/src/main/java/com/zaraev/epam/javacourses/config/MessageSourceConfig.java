package com.zaraev.epam.javacourses.config;


import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Profile("local")
@Configuration
public class MessageSourceConfig {

    @Bean
    public MessageSource messageSource() {   //возвращаем у бина интерфейс MessageSource, завязываемcя на абстрации, реализации будет уже делать спринг
        ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource =
                new ReloadableResourceBundleMessageSource();
        reloadableResourceBundleMessageSource.setDefaultEncoding("UTF-8"); // кодировку по умолчанию
        reloadableResourceBundleMessageSource.setBasename("classpath:messages/message"); // путь к папке с бандлами
        return reloadableResourceBundleMessageSource; // возвращаем реализацию интерфейса
    }
}