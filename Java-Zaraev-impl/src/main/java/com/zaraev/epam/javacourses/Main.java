package com.zaraev.epam.javacourses;

import com.zaraev.epam.javacourses.demonstration.*;
import com.zaraev.epam.javacourses.helper.LocaleStub;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Locale;

@Slf4j
public class Main {

    public static void main(String[] args) {

        log.info("Демонстрация работы после перевода на спринг, при активации профиля local значения репозитория будут застаблены");
        ApplicationContext context = new AnnotationConfigApplicationContext("com.zaraev.epam.javacourses");
        WorkDemonstrationAll workDemonstrationAll = context.getBean(WorkDemonstrationAll.class);
        workDemonstrationAll.test();
        log.info("Демонстрация локализации значений застабленного Customer");
        WorkDemonstrationCustomer workDemonstrationCustomer = context.getBean(WorkDemonstrationCustomer.class);
        LocaleStub localeStub = context.getBean(LocaleStub.class);
        workDemonstrationCustomer.test();
        log.info("Переключаем Local на ENGLISH");
        localeStub.setLocale(Locale.ENGLISH);
        workDemonstrationCustomer.test();
    }
}