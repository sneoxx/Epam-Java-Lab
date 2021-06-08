package com.zaraev.epam.javacourses;

import com.zaraev.epam.javacourses.demonstration.WorkDemonstrationAll;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class Main {

    public static void main(String[] args) {

        log.info("Демонстрация работы после перевода на спринг data");
        ApplicationContext context = new AnnotationConfigApplicationContext("com.zaraev.epam.javacourses");
        WorkDemonstrationAll workDemonstrationAll = new WorkDemonstrationAll();
        workDemonstrationAll.test(context);
//        Main main = new Main();
//
//        main.onStartup();





//        log.info("Демонстрация локализации значений застабленного Customer");
//        context.getEnvironment();
//        for (String profileName : context.getEnvironment().getActiveProfiles()) {
//            log.info("Активный профиль: " + profileName);
//        }
//        WorkDemonstrationCustomer workDemonstrationCustomer = new WorkDemonstrationCustomer();
//        CustomerRepository customerRepository = context.getBean(CustomerRepository.class);
//        workDemonstrationCustomer.test(context);
//        log.info("Переключаем Local на RU");
//        customerRepository.setLocale(new Locale("ru", "RU"));
//        workDemonstrationCustomer.test(context);

    }

}