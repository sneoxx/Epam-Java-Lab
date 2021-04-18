package zaraev.epam.com;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CacheElementTest {

    @Test
    public void createWhenTwoIdenticalElementsReturnEquals() {
        CacheElement<Integer> cacheElement = new CacheElement<>(11, 5);
        CacheElement<Integer> cacheElement1 = new CacheElement<>(11, 5);
        assertEquals(cacheElement,cacheElement1);
    }

    @Test
    public void createWhenTwodifferentElementReturnNotEquals() {
        CacheElement<Integer> cacheElement = new CacheElement<>(11, 5);
        CacheElement<Integer> cacheElement1 = new CacheElement<>(12, 5);
        assertNotEquals(cacheElement,cacheElement1);
    }

    @Test
    public void createWhenOneAssignedToOtherReturnSame() {
        CacheElement<Integer> cacheElement = new CacheElement<>(11, 5);
        CacheElement<Integer> cacheElement1 = cacheElement;
        assertSame(cacheElement,cacheElement1);
    }

    @Test
    public void createAtFirstItWasNullReturnNotNull() {
        CacheElement<Integer> cacheElement = null;
        cacheElement = new CacheElement<>(12, 5);
        assertNotNull(cacheElement);
    }

    @Test
    public void checkHashCodeReturnTrue() {
        CacheElement<Integer> cacheElement = new CacheElement<>(12, 5);
        assertTrue(cacheElement.hashCode() > 0);
    }
}