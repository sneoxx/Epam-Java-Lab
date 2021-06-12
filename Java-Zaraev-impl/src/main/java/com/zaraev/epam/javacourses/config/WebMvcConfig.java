package com.zaraev.epam.javacourses.config;

import com.zaraev.epam.javacourses.converter.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Конфиг класс для работы с Spring web
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = { "com.zaraev.epam.javacourses" })
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new CustomerDTOFromCustomerConverter());
        registry.addConverter(new CustomerFromCustomerDTOConverter());
        registry.addConverter(new SupplierDTOFromSupplierConverter());
        registry.addConverter(new SupplierFromSupplierDTOConverter());
        registry.addConverter(new ProductDTOFromProductConverter());
        registry.addConverter(new ProductFromProductDTOConverter());
        registry.addConverter(new OrderDTOFromOrderConverter());
        registry.addConverter(new OrderFromOrderDTOConverter());
    }
}