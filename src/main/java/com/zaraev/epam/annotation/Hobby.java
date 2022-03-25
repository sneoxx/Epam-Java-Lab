package com.zaraev.epam.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Repeatable(Hobbies.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Hobby {
  int MIN_LEVEL = 1;
  String kind();                  //обязательный элемент
  int level() default MIN_LEVEL; //можно указывать константу
  String[] tags() default {};    //можно указывать массив элементов
  String okStringProp() default ""; //можно указывать пустую строку
  MyDescriptionEnum description() default MyDescriptionEnum.TELEGRAM;

//  String stringProp() default new String(""); //не скомпилируется
//  String anotherStringProp() = null; //не скомпилируется
//  Integer anotherLevel(); //не скомпилируется
}