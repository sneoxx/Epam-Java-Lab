package zaraev.epam.com;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    @Test
    public void addWhenIndexMissingReturnFalse() throws CasheIndexOutOfBoundsException {
        Storage<Integer> storage = new Storage();
        storage.add(1);
        assertEquals(storage.get(0), 1);
    }

    @Test
    public void addWhenIndexMissingReturnTrue() throws CasheIndexOutOfBoundsException {
        Storage<String> storage = new Storage();
        storage.add("Test");
        assertEquals(storage.get(0), "Test");
    }

    @Test
    public void addWhenIndexMissingReturnTrue1() throws CasheIndexOutOfBoundsException {
        Storage<String> storage = new Storage();
        storage.add("Test");
        assertEquals(storage.get(0), "Test");
    }
    @Test
    public void increaseArrayLengthWhenIndexMissingReturnTrue3() throws NotExistElementException, CasheIndexOutOfBoundsException {
        Storage<String> storage = new Storage();
        storage.increaseArrayLength();
        Assertions.assertThrows(CasheIndexOutOfBoundsException.class, () -> {
            storage.get(14);
        });
    }

    @Test
    public void increaseArrayLengthWhenIndexMissingReturnTrue4() throws NotExistElementException, CasheIndexOutOfBoundsException {
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

//размер увеличился в 1.5 раза
    @Test
    public void increaseArrayLengthWhenIndexMissingReturnTrue5() throws NotExistElementException, CasheIndexOutOfBoundsException {
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
        assertEquals(storage.capacity, 15);
    }


    //размер увеличился в 1.5 раза
    @Test
    public void increaseArrayLengthWhenIndexMissingReturnTrue8() throws NotExistElementException, CasheIndexOutOfBoundsException {
        Storage<String> storage = new Storage();
        storage.add("Test");
        storage.increaseArrayLength();
        assertEquals(storage.capacity, 15);
    }

    //размер увеличился в 2.3 раза
    @Test
    public void increaseArrayLengthWhenIndexMissingReturnTrue9() throws NotExistElementException, CasheIndexOutOfBoundsException {
        Storage<String> storage = new Storage();
        storage.add("Test");
        storage.increaseArrayLength();
        storage.increaseArrayLength();
        assertEquals(storage.capacity, 22);
    }

    @Test
    public void getLastWhenIndexMissingReturnTrue4() throws NotExistElementException, CasheIndexOutOfBoundsException {
        Storage<String> storage = new Storage();
        storage.add("Test");
        storage.add("Test2");
        storage.add("Test3");
        assertEquals(storage.getLast(), "Test3");
    }

    @Test
    public void getLastWhenIndexMissingReturnTNull()  {
        Storage<String> storage = new Storage();
        assertEquals(storage.getLast(), null);
    }

    @Test
    public void getLastWhenIndexMissingReturnTNull1()  {
        Storage<Integer> storage = new Storage();
        assertNull(storage.getLast());
    }

    @Test
    public void getLastWhenIndexMissingReturnTNull3()  {
        Storage<Double> storage = new Storage();
        storage.add(1.00);
        assertNotNull(storage.getLast());
    }



    @Test
    public void getLastWhenIndexMissingReturnNoTNull()  {
        Storage<Integer> storage = new Storage();
        storage.add(1);
        storage.add(2);
        storage.add(3);
        assertNotNull(storage.getLast());
    }

    @Test
    public void clearWhenIndexMissingReturnNull6()  {
        Storage<Integer> storage = new Storage();
        storage.add(1);
        storage.add(2);
        storage.add(3);
        storage.clear();
        assertNull(storage.getLast());
    }

    @Test
    public void clearWhenIndexMissingReturnNotNull()  {
        Storage<Integer> storage = new Storage();
        storage.add(1);
        storage.add(2);
        storage.add(3);
        storage.clear();
        storage.add(4);
        assertNotNull(storage.getLast());
    }


    @Test
    public void clearWhenIndexMissingReturnNotNull4() throws CasheIndexOutOfBoundsException {
        Storage<Integer> storage = new Storage();
        storage.add(1);
        storage.add(2);
        storage.add(3);
        storage.clear();
        storage.add(4);
        assertNotNull(storage.get(0));
    }


    @Test
    public void deleteWhenIndexMissingReturnTwo()  {
        Storage<Integer> storage = new Storage();
        storage.add(1);
        storage.add(2);
        storage.add(3);
        storage.delete();
        assertEquals(storage.getLast(), 2);
    }

    @Test
    public void deleteWhenIndexMissingReturnTrue()  {
        Storage<Integer> storage = new Storage();
        Cache<Integer> cache = new Cache<>(10);
        storage.add(1);
        storage.add(2);
        storage.add(3);
        cache.add(3, 2);
        storage.delete();
        assertTrue(cache.isPresent(2));
    }

    @Test
    public void getWhenIndexMissingReturnNotNull() throws CasheIndexOutOfBoundsException {
        Storage<String> storage = new Storage();
        Cache<String> cache = new Cache<>(10);
        storage.add("Test");
        storage.add("Test2");
        storage.add("Test3");
        assertNotNull(storage.get(2));
    }

    @Test
    public void getWhenIndexMissingReturnNull() throws CasheIndexOutOfBoundsException {
        Storage<String> storage = new Storage();
        Cache<String> cache = new Cache<>(10);
        storage.add("Test");
        storage.add("Test2");
        storage.add("Test3");
        assertEquals(storage.get(2), "Test3");
    }




//    public void delete() {
//        T element = getLast();
//        if (cacheStorage.isPresent(element)) {
//            cacheStorage.delete(element);
//        }
//        for (int i = 0; i < storage.length; i++) {
//            if (storage[i].equals(element)) {
//                storage[i] = null;
//                log.debug("Элемент {} удален из хранилища storage", element);
//                return;
//            }
//        }
//    }

}