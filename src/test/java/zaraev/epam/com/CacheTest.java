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




    @Test
    public void getWhenIndex20ThenThrowException() throws CasheIndexOutOfBoundsException {
        CacheElement expectedResult = new CacheElement(11, 20);
        Cache cache = new Cache(10);
        Assertions.assertThrows(CasheIndexOutOfBoundsException.class, () -> {
            cache.get(11);
        });
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
        boolean result = cache.isPresent(0);
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
    void testIsPresentByValueShouldReturnTrue() {
        Cache<Double> cache = new Cache<>(1);
        cache.add(123456.00,0);
        assertTrue(cache.isPresent(123456.00));
    }

    @Test
    void testIsPresentTrue2() {
        Cache<Long> cache = new Cache<>(1);
        cache.add(123456789L,0);
        assertTrue(cache.isPresent(123456789L));
    }

    @Test
    void get() throws CasheIndexOutOfBoundsException {
        Cache<String> cache = new Cache<>(1);
        cache.add("testElement",0);
        assertEquals(cache.get(0),"testElement");
    }

    @Test
    void get2() throws CasheIndexOutOfBoundsException {
        Cache<String> cache = new Cache<>(1);
        cache.add("testElement",0);
        assertNotEquals(cache.get(0),"Element");
    }



//    @Test
//    public void getWhenIndexIsPresentReturnElement() throws CasheIndexOutOfBoundsException {
//        CacheElement expectedResult = new CacheElement(11, 5);
//        Cache cache = new Cache(10);
//        cache.add(expectedResult,0);
//        CacheElement actualResult =(CacheElement) cache.get(5);
//        assertEquals(expectedResult, actualResult);
//    }

//    @Test(expected = CasheIndexOutOfBoundsException.class)
//    public void getWhenIndexIsPresentReturnElelment() throws CasheIndexOutOfBoundsException {
//        CacheElement expectedResult = new CacheElement(11, 5);
//        Cache cache = new Cache(10);
//        cache.add(expectedResult,0);
//        CacheElement actualResult =(CacheElement) cache.get(55);
//    }

    @Test
    public void clearWhenIndexMissingReturnFalse1() {
        CacheElement cacheElement = new CacheElement(11, 5);
        Cache cache = new Cache(10);
        cache.add(cacheElement,0);
        cache.clear();
        boolean result = cache.isPresent(0);
        assertFalse(result);
    }



//    @Test
//    public void isPresentWhenIndexMissingReturnFalse2() throws CasheIndexOutOfBoundsException{
//        CacheElement cacheElement = new CacheElement(11, 5);
//        Cache cache = new Cache(10);
//        cache.add(cacheElement,0);
//        Object result = cache.get(1);
//        Assertions.assertThrows(CasheIndexOutOfBoundsException.class, () -> {
//            cache.get(1);
//        });
//            }

//    @Test
//    public void shiftElementsLeftWhenIndexMissingReturnFalse1() {
//        CacheElement cacheElement = new CacheElement(11, 5);
//        Cache cache = new Cache(10);
//        cache.add(cacheElement,0);
//        cache.add(cacheElement,1);
//        cache.shiftElementsLeft(0);
//        boolean result = cache.isPresent(0);
//        assertFalse(result);
//    }

    @Test
    public void shiftElementsLeftWhenIndexMissingReturnFalse() {
        CacheElement cacheElement = new CacheElement(11, 5);
        CacheElement cacheElement1 = new CacheElement(12, 6);
        CacheElement cacheElement2 = new CacheElement(15, 7);
        Cache cache = new Cache(10);
        cache.add(cacheElement,0);
        cache.add(cacheElement1,1);
        cache.add(cacheElement2,2);
        cache.shiftElementsLeft(1);
        boolean result = cache.isPresent(cacheElement1);
        assertFalse(result);
    }

    @Test
    public void shiftElementsLeftWhenIndexMissingReturnTrue() {
        CacheElement cacheElement = new CacheElement(11, 5);
        CacheElement cacheElement1 = new CacheElement(12, 6);
        CacheElement cacheElement2 = new CacheElement(15, 7);
        Cache cache = new Cache(10);
        cache.add(cacheElement,0);
        cache.add(cacheElement1,1);
        cache.add(cacheElement2,2);
        cache.shiftElementsLeft(0);
        boolean result = cache.isPresent(cacheElement1);
        assertTrue(result);
    }


    @Test
    public void shiftElementsLeftWhenIndexMissingReturnNull() throws CasheIndexOutOfBoundsException {
        CacheElement cacheElement = new CacheElement(11, 5);
        CacheElement cacheElement1 = new CacheElement(12, 6);
        CacheElement cacheElement2 = new CacheElement(15, 7);
        Cache cache = new Cache(10);
        cache.add(cacheElement,0);
        cache.add(cacheElement1,1);
        cache.add(cacheElement2,2);
        cache.shiftElementsLeft(0);
        Object result = cache.get(1);
        assertNotNull(result);
    }




//    public void printCache() {
//        System.out.print(Arrays.toString(cache));
//        System.out.println();
//    }

//    public void shiftElementsLeft(int index) {
//        for (int i = index; i < capacity - 1; i++) {
//            cache[i] = cache[i + 1];
//        }
//        log.debug("Сдвиг всех элементов кеша влево с удалением элемента стоящем на индексе" + index);
//    }

}

