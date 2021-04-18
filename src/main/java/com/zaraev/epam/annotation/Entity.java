package com.zaraev.epam.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Интерфейс аннотации Entity навешиваемой на классы
 */
@Target(ElementType.TYPE) //данная аннотация моэет быть навешана на класс поля и методы
@Retention(RetentionPolicy.RUNTIME)
public @interface Entity {
}