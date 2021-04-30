package com.zaraev.epam;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;


/**
 * Сервисный класс предоставляющий методы для работы с Data Api
 */
@Slf4j
public class DataApiService {

    /**
     * Подсчет всех суммы всех чисел строки из входного значения
     *
     * @param string - строка String
     * @return - при сумму больше 100 вернет true
     */
    public Boolean sumOfString(String string) {
        int sum = 0;
        for (int i = 0; i < string.length(); i++) {
            sum += (int) string.charAt(i) - 48;
        }
        return sum > 100;
    }

    /**
     * Найти дату конца света по формуле: сегодня + N месяцев + M дней,
     * где N – первые два числа от полученного значения, а М –вторые.
     *
     * @param count - число типа long
     */
    public String findDoomsDay(long count) {
        String stringCount = String.valueOf(count);
        String month;
        String day;
        int lengthStringCount = stringCount.length();
        if (lengthStringCount == 3) {
            stringCount = "0" + stringCount;
        } else if (lengthStringCount == 2) {
            stringCount = "00" + stringCount;
        } else if (lengthStringCount == 1) {
            stringCount = "000" + stringCount;
        } else if (lengthStringCount == 0) {
            stringCount = "0000";
        }
        log.debug("findDoomsDay() stringCount : {} ", stringCount);
        month = stringCount.substring(0, 2);
        day = stringCount.substring(2, 4);
        log.debug("findDoomsDay() Month for the formula : {} ", month);
        log.debug("findDoomsDay() Day for the formula : {} ", day);
        ZoneId timeZoneUTC = ZoneId.ofOffset("UTC", ZoneOffset.of("-08:00:00"));
        DateTimeFormatter iso = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime doomsDay = LocalDateTime.now(timeZoneUTC)
                .plusMonths(Long.parseLong(month))
                .plusDays(Long.parseLong(day));
        String doomsDayIso = iso.format(doomsDay);
        log.info("findDoomsDay() День конца света: {} ", doomsDayIso);
        return doomsDayIso;
    }
}