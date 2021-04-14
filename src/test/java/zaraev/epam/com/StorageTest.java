package zaraev.epam.com;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    @Test
    public void addWhenElementIsPresentReturnEquals() throws CasheIndexOutOfBoundsException {
        Storage<Integer> storage = new Storage();
        Integer expectedElement = 1;
        storage.add(expectedElement);
        assertEquals(storage.get(0), 1);
    }

    @Test
    public void addWhenElementMissingReturnNotEquals() throws CasheIndexOutOfBoundsException {
        Storage<String> storage = new Storage();
        String expectedElement = "Test";
        storage.add(expectedElement);
        assertNotEquals(storage.get(0), "Test1");
    }

    @Test
    public void increaseArrayLengthWhenIndexMissingThrowException() throws NotExistElementException, CasheIndexOutOfBoundsException {
        Storage<String> storage = new Storage();
        storage.increaseArrayLength();
        Assertions.assertThrows(CasheIndexOutOfBoundsException.class, () -> {
            storage.get(14);
        });
    }

    @Test
    public void increaseArrayLengthElementIsPresentReturnTrue() throws CasheIndexOutOfBoundsException {
        Storage<String> storage = new Storage();
        storage.add("Test");
        storage.add("Test2");
        storage.add("Test3");
        storage.add("Test4");
        storage.add("Test5");
        storage.add("Test6");
        storage.add("Test7");
        storage.add("Test8");
        storage.add("Test9");
        storage.add("Test10");
        storage.add("Test11");
        storage.add("Test12");
        assertEquals(storage.get(11), "Test12");
    }

    @Test
    public void increaseArrayLengthCheckCapacityReturnTrue() {
        Storage<String> storage = new Storage();
        storage.add("Test");
        int actualCapacity = (int) (storage.capacity * 1.5);
        storage.increaseArrayLength();
        assertEquals(storage.capacity, actualCapacity);
    }

    @Test
    public void increaseArrayLengthCheckCapacityWhenTwoIncreaseReturnTrue() {
        Storage<String> storage = new Storage();
        storage.add("Test");
        int actualCapacity = (int) ((storage.capacity * 1.5) * 1.5);
        storage.increaseArrayLength();
        storage.printStorage();
        storage.increaseArrayLength();
        assertEquals(storage.capacity, actualCapacity);
    }

    @Test
    public void getLastWhenAddedThreeElementsReturnEquals() throws CasheIndexOutOfBoundsException {
        Storage<String> storage = new Storage();
        storage.add("Test");
        storage.add("Test2");
        storage.add("Test3");
        String actualResult = storage.get(2);
        assertEquals(storage.getLast(), actualResult);
    }

    @Test
    public void getLastWhenAddedThreeElementsReturnNotNull() {
        Storage<Integer> storage = new Storage();
        storage.add(1);
        storage.add(2);
        storage.add(3);
        assertNotNull(storage.getLast());
    }

    @Test
    public void getLastWhenElementsNotAddedReturnEquals() {
        Storage<String> storage = new Storage();
        storage.printStorage();
        assertEquals(storage.getLast(), null);
    }

    @Test
    public void getLastWhenElementsNotAddedReturnNull() {
        Storage<Integer> storage = new Storage();
        assertNull(storage.getLast());
    }

    @Test
    public void getLastWhenAddDoubleElementReturnNoTNull() {
        Storage<Double> storage = new Storage();
        storage.add(1.00);
        storage.printStorage();
        assertNotNull(storage.getLast());
    }

    @Test
    public void clearWhenElementMissingReturnNull() {
        Storage<Integer> storage = new Storage();
        storage.add(1);
        storage.add(2);
        storage.add(3);
        storage.clear();
        assertNull(storage.getLast());
    }

    @Test
    public void clearWhenElementIsPresentReturnEquals() {
        Storage<Integer> storage = new Storage();
        storage.add(1);
        storage.add(2);
        storage.add(3);
        storage.clear();
        storage.printStorage();
        storage.add(4);
        assertEquals(storage.getLast(), 4);
    }

    @Test
    public void clearWhenElementIsPresentNotNull() throws CasheIndexOutOfBoundsException {
        Storage<Integer> storage = new Storage();
        storage.add(1);
        storage.add(2);
        storage.add(3);
        storage.clear();
        storage.add(4);
        assertNotNull(storage.get(0));
    }

    @Test
    public void deleteWhenIsLastElementReturnEquals() {
        Storage<Integer> storage = new Storage();
        storage.add(1);
        storage.add(2);
        storage.add(3);
        storage.delete();
        assertEquals(storage.getLast(), 2);
    }

    @Test
    public void deleteWheDeletedTwiceReturnNotNull() throws CasheIndexOutOfBoundsException {
        Storage<Integer> storage = new Storage();
        Cache<Integer> cache = new Cache<>(10);
        storage.add(1);
        storage.add(2);
        storage.add(3);
        storage.delete();
        storage.delete();
        assertNotNull(storage.get(0));
    }

    @Test
    public void deleteWhenNotNull1() throws CasheIndexOutOfBoundsException {
        Storage<Integer> storage = new Storage();
        Cache<Integer> cache = new Cache<>(10);
        storage.add(1);
        storage.add(2);
        storage.add(3);
        cache.add(3,0);
        cache.add(2,1);
        storage.delete();
        storage.delete();
        assertEquals(cache.get(1), 2);
        //assertTrue(cache.isPresent((Integer) 1));
    }

    @Test
    public void getWhenElementIsPresentReturnNotNull() throws CasheIndexOutOfBoundsException {
        Storage<String> storage = new Storage();
        Cache<String> cache = new Cache<>(10);
        storage.add("Test");
        storage.add("Test2");
        storage.add("Test3");
        assertNotNull(storage.get(2));
    }

    @Test
    public void getWhenElementIsPresentReturnEquals() throws CasheIndexOutOfBoundsException {
        Storage<String> storage = new Storage();
        Cache<String> cache = new Cache<>(10);
        storage.add("Test");
        storage.add("Test2");
        storage.add("Test3");
        assertEquals(storage.get(1), "Test2");
    }

    @Test
    public void crateWhenInputArrayReturnEquals() throws CasheIndexOutOfBoundsException {
        String[] strings = {"Test"};
        Storage<String> storage = new Storage(strings);
        assertEquals(storage.get(0), "Test");
    }

    @Test
    public void addWhenElementNullThrowException(){
        String[] strings = {"Test"};
        Storage<String> storage = new Storage(strings);
        Assertions.assertThrows(NotExistElementException.class, () -> {
            storage.add(null);
        });
    }

    @Test
    public void toStringReturnEquals() throws CasheIndexOutOfBoundsException {
        Storage<String> storage = new Storage();
        Cache<String> cache = new Cache<>(10);
        storage.add("Test");
        storage.add("Test2");
        storage.add("Test3");
        storage.toString();
        assertEquals(storage.get(2), "Test3");
    }
}