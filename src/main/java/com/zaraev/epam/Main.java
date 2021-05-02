package com.zaraev.epam;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Main {

    final static int AMOUNT_ELEMENTS = 10000;

    static String fileName = "src/main/resources/1.txt";

    final static String FILE_NAME_ENCODED_FILE = "src/main/resources/File.txt";

    public static void main(String[] args) {
        log.info("Старт программы");
        ForEachService forEachService = new ForEachService();
        StreamService streamService = new StreamService();
        log.info("Часть1 Ищем дату конца света");
        log.info("Вариант через форы");
        List<String> listUuid = forEachService.createRandomUUIDArrayListViaForEach(AMOUNT_ELEMENTS);
        forEachService.writeListToFileViaForEach(listUuid, fileName);
        List<String> listUuid1 = forEachService.readListFromFile(fileName);
        int NumberElements = forEachService.NumberOfElementsWithASumGreaterThan100(listUuid1);
        streamService.getDataApiService().findDoomsDay(NumberElements);
        log.info("Вариант через стримы");
        List<String> listUuid2 = streamService.createRandomUUIDArrayList(AMOUNT_ELEMENTS);
        streamService.writeArrayListToFile(listUuid2, fileName);
        long NumberElementsGreater100 = streamService.readListFromFileAndFilterSum(fileName);
        streamService.getDataApiService().findDoomsDay(NumberElementsGreater100);
        log.info("Часть2 Создаем Колбасу");
        log.info("Вариант через форы");
        List<String> listUuid5 = forEachService.readListFromFileEncodedFile(FILE_NAME_ENCODED_FILE);
        forEachService.createSausageFromList(listUuid5);
        log.info("Вариант через стримы");
        streamService.readListFromFileEncodedFileAndCreateSausage(FILE_NAME_ENCODED_FILE);
    }
}