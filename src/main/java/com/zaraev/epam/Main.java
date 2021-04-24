package com.zaraev.epam;

import com.zaraev.epam.annotation.Value;
import com.zaraev.epam.pojo.Animal;
import com.zaraev.epam.pojo.Bird;
import com.zaraev.epam.pojo.Human;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {

    private final static Logger log = LoggerFactory.getLogger(Main.class);

    @Value
    public static void main(String[] args) {
        log.debug("Старт программы");
        Human human = new Human();
        Bird bird = new Bird();
        Animal animal = new Animal();
        ReflectionService reflectionService = new ReflectionService();
        reflectionService.checkEntityAnnotation(human);
        reflectionService.checkValueFieldsAnnotation(human);
        reflectionService.checkValueMethodsAnnotation(human);
        log.debug("Класс bird аннотации @Entity есть, аннотаций Value нет");
        reflectionService.checkAndFillFieldsWithValueAnnotation(bird, 1);
        log.debug("Класс animal аннотации @Entity нет, аннотаций Value есть");
        reflectionService.checkAndFillFieldsWithValueAnnotation(animal, 1);
        log.debug("Проверяем установку значения String");
        reflectionService.checkAndFillFieldsWithValueAnnotation(human, "Kolya");
        log.debug("Проверяем установку значения Float");
        reflectionService.checkAndFillFieldsWithValueAnnotation(human, 100.0f);
        log.debug("Проверяем установку значения Boolean");
        reflectionService.checkAndFillFieldsWithValueAnnotation(human, true);
        log.debug("Проверяем установку значения null");
        reflectionService.checkAndFillFieldsWithValueAnnotation(human, null);
    }
}