package zaraev.epam.com;

import org.junit.*;

import java.lang.annotation.ElementType;

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


    @Test
    public void addWhenElement11Index5ReturnElement11Index5() throws CasheIndexOutOfBoundsException {
        CacheElement expectedResult = new CacheElement(11, 5);
        Cache cache = new Cache(10);
        cache.add(11, 5);
        CacheElement actualResult = (CacheElement)cache.get(5);
        assertEquals(expectedResult, actualResult);
    }

    @Test(expected = CasheIndexOutOfBoundsException.class)
    public void addWhenIndex20ThenThrowException() throws CasheIndexOutOfBoundsException {
        CacheElement expectedResult = new CacheElement(11, 20);
        Cache cache = new Cache(10);
        cache.add(11, 20);
    }

    @Test
    public void deleteWhenElement1ReturnFalse() {
        CacheElement expectedResult = new CacheElement(11, 5);
        Cache cache = new Cache(10);
        cache.delete(11);
        assertFalse(cache.isPresent(expectedResult));
    }

    @Test
    public void isPresentWhenElementIsPresentReturnTrue() {
        CacheElement cacheElement = new CacheElement(11, 5);
        Cache cache = new Cache(10);
        cache.add(cacheElement,0);
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
        cache.add(cacheElement,0);
        boolean result = cache.isPresent(5);
        assertTrue(result);
    }

    @Test
    public void isPresentWhenIndexMissingReturnFalse() {
        CacheElement cacheElement = new CacheElement(11, 5);
        Cache cache = new Cache(10);
        cache.add(cacheElement,0);
        boolean result = cache.isPresent(3);
        assertFalse(result);
    }


    @Test
    public void getWhenIndexIsPresentReturnElement() {
        CacheElement expectedResult = new CacheElement(11, 5);
        Cache cache = new Cache(10);
        cache.add(expectedResult,0);
        CacheElement actualResult =(CacheElement) cache.get(5);
        assertEquals(expectedResult, actualResult);
    }

    @Test(expected = CasheIndexOutOfBoundsException.class)
    public void getWhenIndexIsPresentReturnElelment() throws CasheIndexOutOfBoundsException {
        CacheElement expectedResult = new CacheElement(11, 5);
        Cache cache = new Cache(10);
        cache.add(expectedResult,0);
        CacheElement actualResult =(CacheElement) cache.get(55);
    }

    /**
     * очистка кэша от всех элементов
     */
    void clear() {
        for (int i = 0; i < capacity; i++) {
            cache[i] = null;
        }
        log.debug("Очистка кеша");
    }

}

