package com.zaraev.epam.javacourses.service.impl;

import com.zaraev.epam.javacourses.service.IService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Пример решения циклической зависимости.
 */
@Slf4j
@Service // указываем что это сприговый бин
public class FirstServiceExample implements IService {

    private final IService iService;

    /**
     * Данный сервис требует IService SecondServiceExample. - @Qualifier("secondServiceExample")
     * @param iService {@link SecondServiceExample}
     */
    public FirstServiceExample(@Qualifier("secondServiceExample") IService iService) {
        this.iService = iService;
    }

    private void getFirst() {
        log.debug("FirstServiceExample - вызвался");
    }

    @Override
    public String getMsg() {
        getFirst();
        return "first service";
    }
}