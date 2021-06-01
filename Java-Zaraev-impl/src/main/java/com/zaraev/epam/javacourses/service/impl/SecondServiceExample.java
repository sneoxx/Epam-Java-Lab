package com.zaraev.epam.javacourses.service.impl;

import com.zaraev.epam.javacourses.service.IService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * Пример решения циклической зависимости.
 */
@Slf4j
@Service
public class SecondServiceExample implements IService {

    private final IService iService;

    /**
     * Данный сервис требует IService SecondServiceExample. - @Qualifier("firstServiceExample")
     * Сюда бин заинжектится, только по мере необходимости. Т.е. firstServiceExample проинициализируется раньше.
     * Если закомментировать @Lazy, то будет ошибка.
     * @param iService {@link FirstServiceExample}
     */
    public SecondServiceExample(@Lazy @Qualifier("firstServiceExample") IService iService) {
        this.iService = iService;
    }

    private void getSecond() {
        log.debug("SecondServiceExample - вызвался");
    }

    @Override
    public String getMsg() {
        getSecond();
        return iService.getMsg() + " and second service";
    }
}