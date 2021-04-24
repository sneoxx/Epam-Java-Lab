package com.zaraev.epam;

import com.zaraev.epam.annotation.Value;
import com.zaraev.epam.exceptions.NoValueAnnotationException;
import com.zaraev.epam.pojo.Animal;
import com.zaraev.epam.pojo.Bird;
import com.zaraev.epam.pojo.Car;
import com.zaraev.epam.pojo.Human;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class ReflectionServiceTest {
    private final static Logger log = LoggerFactory.getLogger(Main.class);
    Human human = new Human();
    Car car = new Car();
    Bird bird = new Bird();
    Animal animal = new Animal();
    Class<?> clazz = human.getClass();
    ReflectionService reflectionService = new ReflectionService();
    public static final String EXAMPLE_NAME = "test";
    public static final Integer EXAMPLE_AGE = 111;
    public static final Integer DEFAULT_AGE_HUMAN = 1;

    @Test
    public void checkEntityAnnotationWhenEntityIsPresentReturnTrue() {
        assertTrue(reflectionService.checkEntityAnnotation(human));
    }

    @Test
    public void checkValueFieldsAnnotationWhenNoPartOfValueReturnFalse() {
        assertFalse(reflectionService.checkValueFieldsAnnotation(human));
    }

    @Test
    public void checkValueMethodsAnnotationWhenValueOnMethodIsPresentReturnTrue() {
        assertTrue(reflectionService.checkValueMethodsAnnotation(human));
    }

    @Test
    public void checkValueFieldsAndMethodsAnnotationsWhenValueIsPresentReturnTrue() {
        assertTrue(reflectionService.checkValueMethodsAnnotation(human));
    }

    @Test
    public void checkValueFieldsAndMethodsAnnotationsWhenNoPartOfValueThrowException() {
        Assertions.assertThrows(NoValueAnnotationException.class, () -> {
            reflectionService.checkValueFieldsAndMethodsAnnotations(bird);
        });
    }

    @Test
    public void checkValueFieldsAndMethodsAnnotationsWhenThereIsNoEntityThrowException() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            reflectionService.checkValueFieldsAndMethodsAnnotations(animal);
        });
    }

    @Test
    public void checkAndFillFieldsWithValueAnnotationWhenFillAgeReturnEquals() {
        reflectionService.checkAndFillFieldsWithValueAnnotation(human, EXAMPLE_AGE);
        assertEquals(human.getAge(), EXAMPLE_AGE);
    }

    @Test
    public void checkAndFillFieldsWithValueAnnotationWhenFillNameReturnEquals() {
        reflectionService.checkAndFillFieldsWithValueAnnotation(human, EXAMPLE_NAME);
        assertEquals(human.getName(), EXAMPLE_NAME);
    }

    @Test
    public void fillFieldsWithValueAnnotationDirectlyWhenFillAgeReturnEquals() {
        reflectionService.fillFieldsWithValueAnnotationDirectly(human, EXAMPLE_AGE);
        assertEquals(human.getAge(), EXAMPLE_AGE);
    }

    @Test
    public void fillFieldsWithValueAnnotationDirectlyWhenAnInvalidArgumentIsEnteredReturnEquals() {
        reflectionService.fillFieldsWithValueAnnotationDirectly(human, 'f');
        assertEquals(human.getAge(), DEFAULT_AGE_HUMAN);
    }

    @Test
    public void setFieldValueAnnotationWhenSetAgeReturnEquals() {
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(Value.class)) {
                log.info("fillFieldsWithValueAnnotationDirectly() - Успешное заполнение переданным значением: {}", field.getType().getName());
                log.info("fillFieldsWithValueAnnotationDirectly() - Успешное заполнение переданным значением: {}", EXAMPLE_AGE.getClass().getTypeName());
                log.info("fillFieldsWithValueAnnotationDirectly() - Успешное заполнение переданным значением: {}", field.getType().getName().equals(EXAMPLE_AGE.getClass().getTypeName()));
                reflectionService.setFieldValueAnnotation(human, EXAMPLE_AGE, field);
            }
        }
        log.info("fillFieldsWithValueAnnotationDirectly() - Успешное заполнение переданным значением: {}", human.getName());
        assertEquals(human.getAge(), EXAMPLE_AGE);
    }
}