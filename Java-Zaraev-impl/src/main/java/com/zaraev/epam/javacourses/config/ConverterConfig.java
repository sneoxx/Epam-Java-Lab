package com.zaraev.epam.javacourses.config;

import com.zaraev.epam.javacourses.converter.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class ConverterConfig {

    @Bean
    public ConversionServiceFactoryBean conversionService() {
        ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
        Set<Converter>converters = new HashSet<>();
        converters.add(new CustomerFromCustomerDTOConverter());
        converters.add(new CustomerDTOFromCustomerConverter());
        converters.add(new SupplierDTOFromSupplierConverter());
        converters.add(new SupplierFromSupplierDTOConverter());
        converters.add(new ProductDTOFromProductConverter());
        converters.add(new ProductFromProductDTOConverter());
        converters.add(new OrderDTOFromOrderConverter());
        converters.add(new OrderFromOrderDTOConverter());
        bean.setConverters(converters);
        return bean;
    }
}