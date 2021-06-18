package com.zaraev.epam.javacourses;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурационный класс для создания бина логгера и возможности активации его через файл
 */
@Configuration
@ConditionalOnProperty(prefix = "logger", name = "enabled", havingValue = "true")
@ConditionalOnWebApplication
public class LoggerConfiguration {

    @Bean
    public LoggingAspect loggingImpl(){
        return new LoggingAspect();
    }

}