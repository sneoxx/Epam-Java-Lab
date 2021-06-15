package com.zaraev.epam.javacourses;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ConditionalOnProperty(prefix = "logger", name = "enabled", havingValue = "true")
@ConditionalOnWebApplication
public class LoggerConfiguration {

    @Bean
    public LoggingImpl loggingImpl(){
        return new LoggingImpl();
    }

}
