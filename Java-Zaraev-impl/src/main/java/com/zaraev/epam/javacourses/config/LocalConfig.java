package com.zaraev.epam.javacourses.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Locale;

//@Profile("local")
//@PropertySource("classpath:impl.properties")
//@Configuration
public class LocalConfig {

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory("WER");
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource =
                new ReloadableResourceBundleMessageSource();
        reloadableResourceBundleMessageSource.setDefaultEncoding("UTF-8");
        reloadableResourceBundleMessageSource.setBasename("classpath:messages/message");
        return reloadableResourceBundleMessageSource;
    }

    @Bean
    public Locale locale() {
       return new Locale("ru", "RU");
    }

}