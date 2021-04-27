package com.zaraev.epam;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Main {

    static final int AMOUNT_ELEMENTS = 10000;

    static String fileName = "src/main/resources/1.txt";

    public static void main(String[] args) {
        log.info("Старт программы");
        DataApiService dataApiService = new DataApiService();
        ForEachService forEachService = new ForEachService();
        StreamService streamService = new StreamService();
        log.info("Вариант через форы");
        List<String> listUuid = forEachService.createRandomUUIDArrayListViaForEach(AMOUNT_ELEMENTS);
        forEachService.writeListToFileViaForEach(listUuid, fileName);
        List<String> listUuid1 = forEachService.readListFromFile(fileName);
        int NumberElements = forEachService.NumberOfElementsWithASumGreaterThan100(listUuid1);
        dataApiService.findDoomsDay(NumberElements);
        log.info("Вариант через стримы");
        List<String> listUuid2 = streamService.createRandomUUIDArrayList(AMOUNT_ELEMENTS);
        streamService.writeArrayListToFile(listUuid2, fileName);
        long NumberElementsGreater100 = streamService.readListFromFileAndFilterSum(fileName);
        dataApiService.findDoomsDay(NumberElementsGreater100);
    }
}