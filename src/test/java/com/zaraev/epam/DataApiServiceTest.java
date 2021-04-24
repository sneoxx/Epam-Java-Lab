package com.zaraev.epam;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class DataApiServiceTest {

    public static final long count = 3588;

    public static final String string = "125893325874632589";

    public static final String string1 = "925893395874632589";

    DataApiService dataApiService = new DataApiService();

    @Test
    public void sumOfStringWhenLessThan100ReturnFalse() {
        assertFalse(dataApiService.sumOfString(string));
    }

    @Test
    public void sumOfStringWhenMoreThan100ReturnTrue() {
        assertTrue(dataApiService.sumOfString(string1));
    }

    @Test
    public void findDoomsDayReturnNotNull() {
        String doomsDayIso = dataApiService.findDoomsDay(count);
        assertNotNull(doomsDayIso);
    }
}