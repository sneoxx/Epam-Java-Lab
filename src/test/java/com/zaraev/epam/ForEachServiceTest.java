package com.zaraev.epam;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
class ForEachServiceTest {

    final int amountElements = 10000;

    final static String fileName = "src/main/resources/1.txt";

    final static List<String> list = List.of("125896325874125896325","1111111111111111111111", "ssdsdwwww", "12345564354545");

    ForEachService forEachService = new ForEachService();


    @Test
    public void createRandomUUIDArrayListViaForEachReturnNotNull() {
        assertNotNull(forEachService.createRandomUUIDArrayListViaForEach(amountElements));
    }


    @Test
    public void writeListToFileViaForEachAfterWritingReadReturnEquals() {
        forEachService.writeListToFileViaForEach(list, fileName);
        List<String> list1 = forEachService.readListFromFile(fileName);
        assertEquals(list,list1);
    }


    @Test
    public void NumberOfElementsWithASumGreaterThan100WhenTheCollectionIsKnownReturnEquals() {
      int count = forEachService.NumberOfElementsWithASumGreaterThan100(list);
      assertEquals(count, 1);
    }
}