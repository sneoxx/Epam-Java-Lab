package com.zaraev.epam.javacourses;

import com.zaraev.epam.javacourses.demonstration.WorkDemonstrationAll;
import com.zaraev.epam.javacourses.demonstration.WorkDemonstrationCustomer;
import com.zaraev.epam.javacourses.helper.RepositoryHelper;
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
        RepositoryHelper repositoryHelper = context.getBean(RepositoryHelper.class);
        workDemonstrationCustomer.test();
        log.info("Переключаем Local на RU");
        repositoryHelper.setLocale(new Locale( "ru" ,"RU"));
        workDemonstrationCustomer.test();
    }
}