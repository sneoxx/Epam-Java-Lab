package zaraev.epam.com;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CacheTest {

    @Test
    public void getWhenIndex20ThenThrowException1() throws CasheIndexOutOfBoundsException {
        CacheElement expectedResult = new CacheElement(11, 20);
        Cache cache = new Cache(10);
        cache.add(expectedResult, 0);
        assertTrue(cache.isPresent(expectedResult));
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
    public void isPresentWhenElementMissingReturnFalse() {
        CacheElement cacheElement = new CacheElement(11, 5);
        Cache cache = new Cache(10);
        boolean result = cache.isPresent(cacheElement);
        assertFalse(result);
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
    public void addWhenElementDoubleReturnTrue() {
        Cache<Double> cache = new Cache<>(1);
        cache.add(123456.00, 0);
        assertTrue(cache.isPresent(123456.00));
    }

    @Test
    public void addWhenElementLongReturnTrue() {
        Cache<Long> cache = new Cache<>(1);
        cache.add(123456789L, 0);
        assertTrue(cache.isPresent(123456789L));
    }

    @Test
    public void addWhenElementFloatReturnTrue() {
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

    @Test
    public void getWhenAddedTwoIdenticalElementsReturnEquals() throws CasheIndexOutOfBoundsException {
        CacheElement cacheElement1 = new CacheElement("testElement", 5);
        CacheElement cacheElement2 = new CacheElement("testElement", 5);
        Cache cache = new Cache<>(1);
        cache.add(cacheElement1, 0);
        cache.add(cacheElement2, 1);
        assertEquals(cache.get(0), cacheElement2);
    }

    @Test
    public void clearWhenIndexAddAndClearReturnFalse() {
        CacheElement cacheElement = new CacheElement(11, 5);
        Cache cache = new Cache(10);
        cache.add(cacheElement, 0);
        cache.clear();
        boolean result = cache.isPresent(0);
        assertFalse(result);
    }

    @Test
    public void clearWhenIndexAddAndClearAndAddReturnTrue() {
        CacheElement cacheElement = new CacheElement(11, 5);
        Cache cache = new Cache(10);
        cache.add(cacheElement, 0);
        cache.clear();
        cache.add(cacheElement, 0);
        boolean result = cache.isPresent(0);
        assertTrue(result);
    }

    @Test
    public void clearWhenElementAddAndClearAndAddReturnTrue() {
        CacheElement cacheElement = new CacheElement(11, 5);
        Cache cache = new Cache(10);
        cache.add(cacheElement, 0);
        cache.clear();
        cache.add(cacheElement, 2);
        cache.printCache();
        boolean result = cache.isPresent(cacheElement);
        assertTrue(result);
    }

    @Test
    public void clearWhenElementAddAndClearReturnFalse() {
        CacheElement cacheElement = new CacheElement(11, 5);
        Cache cache = new Cache(10);
        cache.add(cacheElement, 0);
        cache.clear();
        boolean result = cache.isPresent(cacheElement);
        assertFalse(result);
    }

    @Test
    public void shiftElementsLeftWhenCheckedIndexShiftReturnFalse() {
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

    public void shiftElementsLeftWhenAnotherIndexShiftReturnTrue() {
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

    @Test
    public void shiftElementsLeftWhenAnotherIndexShiftCheckTwoOtherElementsReturnEquals() throws CasheIndexOutOfBoundsException {
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

    @Test
    public void shiftElementsLeftWhenIndexShiftCheckTwoElementsReturnNotEquals() throws CasheIndexOutOfBoundsException {
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

    @Test
    public void getWhenIndex11ThenThrowException() throws CasheIndexOutOfBoundsException {
        Cache cache = new Cache(10);
        Assertions.assertThrows(CasheIndexOutOfBoundsException.class, () -> {
            cache.get(11);
        });
    }

    @Test
    public void createWhenElementsAddedReturnNotNull() throws CasheIndexOutOfBoundsException {
        Cache<Short> cache = new Cache<>(10);
        cache.add((short) 1, 0);
        cache.printCache();
        assertNotNull(cache.capacity);
    }

    @Test
    public void fillCacheIntegerWhenElementIsPresentReturnNotNull() throws CasheIndexOutOfBoundsException {
        Cache<Integer> cache = new Cache<>(10);
        cache.fillCacheInteger(cache);
        assertNotNull(cache.get(9));
    }

    @Test
    public void deleteWhenIsLastElementReturnEquals() throws CasheIndexOutOfBoundsException {
        Cache<Integer> cache = new Cache<>(10);
        cache.add(1,0);
        cache.add(2,1);
        cache.add(3,2);
        cache.delete(3);
        assertEquals(cache.get(1), 2);
    }

    @Test
    public void deleteWheDelElementReturnTrue() throws CasheIndexOutOfBoundsException {
        Cache<Integer> cache = new Cache<>(10);
        cache.add(1,0);
        cache.add(2,1);
        cache.add(3,2);
        cache.delete(1);
        cache.delete(3);
        boolean result = cache.isPresent(1);
        assertTrue(result);
    }

    @Test
    public void deleteWheDeletedTwoElementReturnTrue() throws CasheIndexOutOfBoundsException {
        Cache<Integer> cache = new Cache<>(10);
        cache.add(1,0);
        cache.add(2,1);
        cache.add(3,2);
        cache.delete(1);
        cache.delete(3);
        boolean result = cache.isPresent(1);
        assertTrue(result);
    }

    @Test
    public void toStringReturnTrue() throws CasheIndexOutOfBoundsException {
        Cache<Integer> cache = new Cache<>(10);
        cache.add(1,0);
        cache.add(2,1);
        cache.add(3,2);
        cache.toString();
        boolean result = cache.isPresent(1);
        assertTrue(result);
    }
}