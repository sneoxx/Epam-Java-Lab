package zaraev.epam.com;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CacheTest {
    //JUnit пример
    //Код который нужно проверить:
//    public class LogAnalyzer {
//        public boolean isValidLogFileName(String fileName) {
//            if (fileName.endsWith("log")){
//                return  true;
//            }
//            return false;
//        }
//    }
//
//    //Код теста:
//    class  LogAnalyzerTest {
//        @Test
//        public void isValidLogFileNameWhenBadExtensionReturnFalse() {
//            LogAnalyzer analyzer = new LogAnalyzer();
//            boolean result = analyzer.isValidLogFileName("filetest.txt");
//            assertFalse(result);

//    @Test //вешаем аанотацию
//    public void sumWhenTwoPlusTwoReturbFour() { //создаем понятное имя
//        double expectedResult = 4.0; // пременная того же типа что возвращает, тестируемый метод, что должно получится
//        Calculator calculator = new Calculator(); // создаем экземпляр тестируемого класса
//        double actualResult = calculator.sum(2, 2); //вызвваем метод тестируемого класса
//        assertEquals(expected, actualResult); //ассершен
//    }
//
//    @Test(expected = RuntimeException.class) //вешаем аанотацию проверки на выброс исключения
//    public void devisionWhenArgIsZeroThenThrowException() { //создаем понятное имя
//        Calculator calculator = new Calculator(); // создаем экземпляр тестируемого класса
//        double actualResult = calculator.division(2.0, 0.0); //вызвваем метод тестируемого класса
//    }


//    @Test
//    public void addWhenElement11Index5ReturnElement11Index5() throws CasheIndexOutOfBoundsException {
//        Integer expectedResult = new Integer(11, 5);
//        Cache<CacheElement> cache = new Cache(10);
//        cache.add();
//        CacheElement actualResult = (CacheElement) cache.get(5);
//        assertEquals(expectedResult, actualResult);
//    }

//    @Test
//    void addNotNull() throws CasheIndexOutOfBoundsException {
//        Cache<String> cache = new Cache<>(10);
//         assertNull(cache.get(1));
//    }


    // получаем не существующий индекс элемента из кеша и получаем ошибку
    @Test
    public void getWhenIndex20ThenThrowException() throws CasheIndexOutOfBoundsException {
        CacheElement expectedResult = new CacheElement(11, 20);
        Cache cache = new Cache(10);
        Assertions.assertThrows(CasheIndexOutOfBoundsException.class, () -> {
            cache.get(11);
        });
    }

    // Добавляем элемент в кеш и проверяем добавился ли он через isPresent по элементу
    @Test
    public void getWhenIndex20ThenThrowException1() throws CasheIndexOutOfBoundsException {
        CacheElement expectedResult = new CacheElement(11, 20);
        Cache cache = new Cache(10);
        cache.add(expectedResult, 0);
        assertTrue(cache.isPresent(expectedResult));
    }




    @Test
    public void isPresentWhenElementIsPresentReturnTrue() {
        CacheElement cacheElement = new CacheElement(11, 5);
        Cache cache = new Cache(10);
        cache.add(cacheElement, 0);
        boolean result = cache.isPresent(cacheElement);
        assertTrue(result);
    }

    @Test
    public void isPresentWhenElementMissingReturnFalse() {
        CacheElement cacheElement = new CacheElement(11, 5);
        Cache cache = new Cache(10);
        boolean result = cache.isPresent(cacheElement);
        assertFalse(result);
    }

    @Test
    public void isPresentWhenIndexIsPresentReturnTrue() {
        CacheElement cacheElement = new CacheElement(11, 5);
        Cache cache = new Cache(10);
        cache.add(cacheElement, 0);
        boolean result = cache.isPresent(0);
        assertTrue(result);
    }

    @Test
    public void isPresentWhenIndexMissingReturnFalse() {
        CacheElement cacheElement = new CacheElement(11, 5);
        Cache cache = new Cache(10);
        cache.add(cacheElement, 0);
        boolean result = cache.isPresent(3);
        assertFalse(result);
    }

    @Test
    public void testIsPresentByValueShouldReturnTrue() {
        Cache<Double> cache = new Cache<>(1);
        cache.add(123456.00, 0);
        assertTrue(cache.isPresent(123456.00));
    }

    @Test
    public void testIsPresentTrue2() {
        Cache<Long> cache = new Cache<>(1);
        cache.add(123456789L, 0);
        assertTrue(cache.isPresent(123456789L));
    }

    @Test
    public void testIsPresentTrue3() {
        Cache<Float> cache = new Cache<>(1);
        cache.add(123456789.00F, 0);
        assertTrue(cache.isPresent(123456789.00F));
    }

    @Test
    public void get() throws CasheIndexOutOfBoundsException {
        Cache<String> cache = new Cache<>(1);
        cache.add("testElement", 0);
        assertEquals(cache.get(0), "testElement");
    }

    // проверяем get через assertNotEquals добавялем "testElement", 0, сравниваем
    @Test
    public void get2() throws CasheIndexOutOfBoundsException {
        CacheElement cacheElement1 = new CacheElement("testElement", 5);
        CacheElement cacheElement2 = new CacheElement("testElement", 5);
        Cache cache = new Cache<>(1);
        cache.add(cacheElement1, 0);
        cache.add(cacheElement2, 1);
        assertEquals(cache.get(0), cacheElement2);
    }

    // проверяяем очистку, добавялем индекс 0 чистим проверяем осталося ли 0
    @Test
    public void clearWhenIndexMissingReturnFalse1() {
        CacheElement cacheElement = new CacheElement(11, 5);
        Cache cache = new Cache(10);
        cache.add(cacheElement, 0);
        cache.clear();
        boolean result = cache.isPresent(0);
        assertFalse(result);
    }

    // проверяяем очистку, добавяем индекс 0 чистим опять добавляем 0 проверяем  0
    @Test
    public void clearWhenIndexMissingReturnFalse3() {
        CacheElement cacheElement = new CacheElement(11, 5);
        Cache cache = new Cache(10);
        cache.add(cacheElement, 0);
        cache.clear();
        cache.add(cacheElement, 0);
        boolean result = cache.isPresent(0);
        assertTrue(result);
    }


    // проверяяем очистку, добавяем индекс 0 чистим опять добавляем 0 проверяем  по элементу
    @Test
    public void clearWhenIndexMissingReturnFalse4() {
        CacheElement cacheElement = new CacheElement(11, 5);
        Cache cache = new Cache(10);
        cache.add(cacheElement, 0);
        cache.clear();
        cache.add(cacheElement, 2);
        boolean result = cache.isPresent(cacheElement);
        assertTrue(result);
    }

    // проверяяем очистку, добавяем индекс 0 чистим  проверяем  по элементу
    @Test
    public void clearWhenIndexMissingReturnFalse5() {
        CacheElement cacheElement = new CacheElement(11, 5);
        Cache cache = new Cache(10);
        cache.add(cacheElement, 0);
        cache.clear();
        boolean result = cache.isPresent(cacheElement);
        assertFalse(result);
    }

    // проверяяем очистку, добавяем индекс 0 чистим опять добавляем 0 проверяем  по элементу
    @Test
    public void clearWhenIndexMissingReturnFalse6() {
        CacheElement cacheElement = new CacheElement(11, 5);
        Cache cache = new Cache(10);
        cache.add(cacheElement, 0);
        cache.clear();
        cache.add(cacheElement, 2);
        boolean result = cache.isPresent(cacheElement);
        assertTrue(result);
    }

  // проверяем сдвиг элементов, добавляем 3 элемента, делаем сдвиг 1 индекса, он удаляется, проверяем есть ли элемент с 1 индексом - нету
    @Test
    public void shiftElementsLeftWhenIndexMissingReturnFalse() {
        CacheElement cacheElement = new CacheElement(11, 5);
        CacheElement cacheElement1 = new CacheElement(12, 6);
        CacheElement cacheElement2 = new CacheElement(15, 7);
        Cache cache = new Cache(10);
        cache.add(cacheElement, 0);
        cache.add(cacheElement1, 1);
        cache.add(cacheElement2, 2);
        cache.shiftElementsLeft(1);
        boolean result = cache.isPresent(cacheElement1);
        assertFalse(result);
    }

    // проверяем сдвиг элементов, добавляем 3 элемента, делаем сдвиг 1 индекса, он удаляется, проверяем есть ли элемент с 2 индексом - есть
    @Test
    public void shiftElementsLeftWhenIndexMissingReturnTrue() {
        CacheElement cacheElement = new CacheElement(11, 5);
        CacheElement cacheElement1 = new CacheElement(12, 6);
        CacheElement cacheElement2 = new CacheElement(15, 7);
        Cache cache = new Cache(10);
        cache.add(cacheElement, 0);
        cache.add(cacheElement1, 1);
        cache.add(cacheElement2, 2);
        cache.shiftElementsLeft(1);
        boolean result = cache.isPresent(cacheElement2);
        assertTrue(result);
    }

    // проверяем сдвиг элементов, добавляем 3 элемента, делаем сдвиг 0 индекса, он удаляется, проверяем не налл ли элемент с 2 индексом - не налл
    @Test
    public void shiftElementsLeftWhenIndexMissingReturnNull() throws CasheIndexOutOfBoundsException {
        CacheElement cacheElement = new CacheElement(11, 5);
        CacheElement cacheElement1 = new CacheElement(12, 6);
        CacheElement cacheElement2 = new CacheElement(15, 7);
        Cache cache = new Cache(10);
        cache.add(cacheElement, 0);
        cache.add(cacheElement1, 1);
        cache.add(cacheElement2, 2);
        cache.shiftElementsLeft(0);
        Object result = cache.get(2);
        assertNotNull(result);
    }

    // проверяем сдвиг элементов, добавляем 3 элемента, делаем сдвиг 0 индекса, он удаляется, проверяем равен ли элемент с 1 индексом элементу1 - да
    @Test
    public void shiftElementsLeftWhenIndexMissingReturnNull8() throws CasheIndexOutOfBoundsException {
        CacheElement cacheElement = new CacheElement("Test1", 5);
        CacheElement cacheElement1 = new CacheElement("Test2", 6);
        CacheElement cacheElement2 = new CacheElement("Test3", 7);
        Cache cache = new Cache(10);
        cache.add(cacheElement, 0);
        cache.add(cacheElement1, 1);
        cache.add(cacheElement2, 2);
        cache.shiftElementsLeft(0);
        assertEquals(cache.get(1), cacheElement1);
    }


    // проверяем сдвиг элементов, добавляем 3 элемента, делаем сдвиг 0 индекса, он удаляется, проверяем равен ли элемент с 2 индексом cacheElement - нет
    @Test
    public void shiftElementsLeftWhenIndexMissingReturnNull9() throws CasheIndexOutOfBoundsException {
        CacheElement cacheElement = new CacheElement("Test1", 5);
        CacheElement cacheElement1 = new CacheElement("Test2", 6);
        CacheElement cacheElement2 = new CacheElement("Test3", 7);
        Cache cache = new Cache(10);
        cache.add(cacheElement, 0);
        cache.add(cacheElement1, 1);
        cache.add(cacheElement2, 2);
        cache.shiftElementsLeft(0);
        assertNotEquals(cache.get(2), cacheElement);
    }




}

