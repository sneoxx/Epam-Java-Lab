package com.zaraev.epam.javacourses.service.impl;

import com.zaraev.epam.javacourses.service.EService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LessonExampleImpl implements EService {

    //инжектим, чтобы не ругалось что не проинициализировано - делаем конструктор @RequiredArgsConstructor
    private final LessonTwoExampleImpl lessonTwoExample;

//    @Value("${les:defaultValue}")   //дефолтное значение если ничего не передано
    @Value("#{T(java.lang.Math).random()}") // можно вызвать метод - путь до класса и его метод
    private String value;

    public void create(){
        log.info("create() {} ", value);
        log.info("create() {} ", lessonTwoExample.getSomething());
    }

}
