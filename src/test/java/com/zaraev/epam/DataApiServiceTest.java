package com.zaraev.epam;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class DataApiServiceTest {

    public static final long TEST_COUNT = 3588;

    public static final String TEST_STRING = "125893325874632589";

    public static final String TEST_STRING2 = "925893395874632589";

    DataApiService dataApiService = new DataApiService();

    @Test
    public void sumOfStringWhenLessThan100ReturnFalse() {
        assertFalse(dataApiService.sumOfString(TEST_STRING));
    }

    @Test
    public void sumOfStringWhenMoreThan100ReturnTrue() {
        assertTrue(dataApiService.sumOfString(TEST_STRING2));
    }

    @Test
    public void findDoomsDayReturnNotNull() {
        String doomsDayIso = dataApiService.findDoomsDay(TEST_COUNT);
        assertNotNull(doomsDayIso);
    }
}