package com.zaraev.epam;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
class ForEachServiceTest {

    public final static int AMOUNT_ELEMENTS = 10000;

    public final static String FILE_NAME = "src/main/resources/1.txt";

    public final static String FILE_NAME_ENCODED_FILE = "src/main/resources/File.txt";

    public final static List<String> LIST_TEST = List.of("125896325874125896325", "1111111111111111111111", "ssdsdwwww", "12345564354545");

    ForEachService forEachService = new ForEachService();

    @Test
    public void createRandomUUIDArrayListViaForEachReturnNotNull() {
        assertNotNull(forEachService.createRandomUUIDArrayListViaForEach(AMOUNT_ELEMENTS));
    }

    @Test
    public void writeListToFileViaForEachAfterWritingReadReturnEquals() {
        forEachService.writeListToFileViaForEach(LIST_TEST, FILE_NAME);
        List<String> list1 = forEachService.readListFromFile(FILE_NAME);
        assertEquals(LIST_TEST, list1);
    }

    @Test
    public void NumberOfElementsWithASumGreaterThan100WhenTheCollectionIsKnownReturnEquals() {
        int count = forEachService.NumberOfElementsWithASumGreaterThan100(LIST_TEST);
        assertEquals(count, 1);
    }

    @Test
    public void readListFromFileEncodedFileReturnNotNull() {
        assertNotNull(forEachService.readListFromFileEncodedFile(FILE_NAME_ENCODED_FILE));
    }

    @Test
    public void createSausageFromListReturnNotNull() {
        List<String> test = forEachService.readListFromFileEncodedFile(FILE_NAME_ENCODED_FILE);
        assertNotNull(forEachService.createSausageFromList(test));
    }
}