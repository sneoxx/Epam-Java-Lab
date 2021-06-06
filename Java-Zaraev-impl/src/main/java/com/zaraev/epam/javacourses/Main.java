package com.zaraev.epam.javacourses;

import com.zaraev.epam.javacourses.demonstration.WorkDemonstrationAll;
import com.zaraev.epam.javacourses.demonstration.WorkDemonstrationCustomer;
import com.zaraev.epam.javacourses.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Locale;

@Slf4j
public class Main {

    public static void main(String[] args) {

        log.info("Демонстрация работы после перевода на спринг, при активации профиля local значения репозитория будут застаблены");
        ApplicationContext context = new AnnotationConfigApplicationContext("com.zaraev.epam.javacourses");
        context.getEnvironment();
        for (String profileName : context.getEnvironment().getActiveProfiles()) {
            log.info("Активный профиль: " + profileName);
        }
        WorkDemonstrationAll workDemonstrationAll = new WorkDemonstrationAll();
        workDemonstrationAll.test(context);
        log.info("Демонстрация локализации значений застабленного Customer");
        WorkDemonstrationCustomer workDemonstrationCustomer = new WorkDemonstrationCustomer();
        CustomerRepository customerRepository = context.getBean(CustomerRepository.class);
        workDemonstrationCustomer.test(context);
        log.info("Переключаем Local на RU");
        customerRepository.setLocale(new Locale("ru", "RU"));
        workDemonstrationCustomer.test(context);
    }
}