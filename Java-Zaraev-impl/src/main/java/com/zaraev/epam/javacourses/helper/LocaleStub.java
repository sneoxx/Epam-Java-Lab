package com.zaraev.epam.javacourses.helper;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Класс Locale для возможности переключения локали через set значения
 */
@Component
@Data
@Slf4j
public class LocaleStub {
    private Locale locale;

    public LocaleStub() {
        this.locale = new Locale("ru", "RU");
    }
}