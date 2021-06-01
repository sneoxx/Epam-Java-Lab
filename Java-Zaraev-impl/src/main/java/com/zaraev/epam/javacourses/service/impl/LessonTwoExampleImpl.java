package com.zaraev.epam.javacourses.service.impl;

import com.zaraev.epam.javacourses.service.EService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LessonTwoExampleImpl implements EService {

    public String getSomething(){
        return "something";
    }

}
