package zaraev.epam.com;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CacheElementTest {

    @Test
    public void createCacheElement() {
        CacheElement cacheElement = new CacheElement(11, 5);
        CacheElement cacheElement1 = new CacheElement(11, 6);
        assertFalse(cacheElement.equals(cacheElement1));
    }

    @Test
    public void createCacheElement1() {
        CacheElement cacheElement = new CacheElement(11, 5);
        CacheElement cacheElement1 = new CacheElement(11, 5);
        assertEquals(cacheElement,cacheElement1);
    }

    @Test
    public void createCacheElement2() {
        CacheElement cacheElement = new CacheElement(11, 5);
        CacheElement cacheElement1 = new CacheElement(12, 5);
        assertNotEquals(cacheElement,cacheElement1);
    }

    @Test
    public void createCacheElement3() {
        CacheElement cacheElement = new CacheElement(11, 5);
        CacheElement cacheElement1 = null;
        assertNull(cacheElement1);
    }

    @Test
    public void createCacheElement4() {
        CacheElement cacheElement = new CacheElement(11, 5);
        CacheElement cacheElement1 = new CacheElement(12, 5);
        assertNotNull(cacheElement1);
    }


}