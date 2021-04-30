package com.zaraev.epam;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class DataApiServiceTest {

    public static final long TEST_COUNT = 3588;

    public static final long TEST_COUNT1 = 358;

    public static final long TEST_COUNT2 = 35;

    public static final long TEST_COUNT3 = 3;

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
        String doomsDayIso1 = dataApiService.findDoomsDay(TEST_COUNT1);
        assertNotNull(doomsDayIso1);
        String doomsDayIso2 = dataApiService.findDoomsDay(TEST_COUNT2);
        assertNotNull(doomsDayIso2);
        String doomsDayIso3 = dataApiService.findDoomsDay(TEST_COUNT3);
        assertNotNull(doomsDayIso3);
    }
}