package com.zaraev.epam;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
class StreamServiceTest {

    public final static int AMOUNT_ELEMENTS = 10000;

    public final static String FILE_NAME = "src/main/resources/1.txt";

    public final static List<String> LIST_TEST = List.of("125896325874125896325", "1111111111111111111111", "ssdsdwwww", "12345564354545");

    StreamService streamService = new StreamService();

    @Test
    public void createRandomUUIDArrayListReturnNotNull() {
        assertNotNull(streamService.createRandomUUIDArrayList(AMOUNT_ELEMENTS));
    }

    @Test
    public void writeArrayListToFileAfterWritingReadReturnEquals() {
        streamService.writeArrayListToFile(LIST_TEST , FILE_NAME);
        File file = new File(FILE_NAME);
        List<String> list1 = null;
        try {
            list1 = Files.lines(Paths.get(file.getAbsolutePath()))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            log.error("readListFromFile() Ошибка ввода вывода при чтении из файла", e);
        }
        assertEquals(LIST_TEST, list1);
    }

    @Test
    public void readListFromFileAndFilterSumWhenTheCollectionIsKnownReturnEquals() {
        streamService.writeArrayListToFile(LIST_TEST , FILE_NAME);
        long count = streamService.readListFromFileAndFilterSum(FILE_NAME);
        assertEquals(count, 1);
    }
}