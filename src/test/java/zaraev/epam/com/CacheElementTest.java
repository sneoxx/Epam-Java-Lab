package zaraev.epam.com;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CacheElementTest {

    @Test
    public void createWhenTwoIdenticalElementsReturnEquals() {
        CacheElement cacheElement = new CacheElement(11, 5);
        CacheElement cacheElement1 = new CacheElement(11, 5);
        assertEquals(cacheElement,cacheElement1);
    }

    @Test
    public void createWhenTwodifferentElementReturnNotEquals() {
        CacheElement cacheElement = new CacheElement(11, 5);
        CacheElement cacheElement1 = new CacheElement(12, 5);
        assertNotEquals(cacheElement,cacheElement1);
    }

    @Test
    public void createWhenOneAssignedToOtherReturnSame() {
        CacheElement cacheElement = new CacheElement(11, 5);
        CacheElement cacheElement1 = cacheElement;
        assertSame(cacheElement,cacheElement1);
    }

    @Test
    public void createAtFirstItWasNullReturnNotNull() {
        CacheElement cacheElement = null;
        cacheElement = new CacheElement(12, 5);
        assertNotNull(cacheElement);
    }
}