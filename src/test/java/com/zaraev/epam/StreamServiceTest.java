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

    final int amountElements = 10000;

    final static String fileName = "src/main/resources/1.txt";

    final static List<String> list = List.of("125896325874125896325", "1111111111111111111111", "ssdsdwwww", "12345564354545");

    StreamService streamService = new StreamService();

    @Test
    public void createRandomUUIDArrayListReturnNotNull() {
        assertNotNull(streamService.createRandomUUIDArrayList(amountElements));
    }

    @Test
    public void writeArrayListToFileAfterWritingReadReturnEquals() {
        streamService.writeArrayListToFile(list, fileName);
        File file = new File(fileName);
        List<String> list1 = null;
        try {
            list1 = Files.lines(Paths.get(file.getAbsolutePath()))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            log.error("readListFromFile() Ошибка ввода вывода при чтении из файла", e);
        }
        assertEquals(list, list1);
    }

    @Test
    public void readListFromFileAndFilterSumWhenTheCollectionIsKnownReturnEquals() {
        streamService.writeArrayListToFile(list, fileName);
        long count = streamService.readListFromFileAndFilterSum(fileName);
        assertEquals(count, 1);
    }
}