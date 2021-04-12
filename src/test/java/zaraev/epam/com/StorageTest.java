package zaraev.epam.com;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    @Test
    public void addWhenIndexMissingReturnFalse() throws CasheIndexOutOfBoundsException {
        Storage<Integer> storage = new Storage();
        storage.add(1);
        assertEquals(storage.get(0), 1);
    }

//    public void add(T element) throws NotExistElementException {
//        if (element == null) {
//            throw new NotExistElementException("Не добавляй нулевой элемент");
//        }
//        for (int i = 0; i < storage.length; i++)
//            if (storage[i] == null) {
//                storage[i] = element;
//                return;
//            }
//        int addIndex = capacity;
//        increaseArrayLength();
//        storage[addIndex] = element;
//        log.debug("Элемент {} успешно добавлен в хранилище storage", element);
//    }

}