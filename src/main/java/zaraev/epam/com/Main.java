package zaraev.epam.com;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        log.info("Старт программы");
        Cache<Integer> myCache = new Cache<>(10);
        log.debug("Работа с кешем");
        myCache = myCache.fillCacheInteger(myCache);
        log.debug("Пытаемся добавить null значение в кеш");
        myCache.add(null, 10);
        myCache.printCache();
        myCache.add(114, 11);
        log.debug("Добавили новый уникальный элемент в заполненный кеш");
        myCache.printCache();
        myCache.add(94, 12);
        log.debug("Добавили повторяющийся элемент 94");
        myCache.printCache();
        log.debug("Проверяем наличие элемента по несуществующему полю индекс 99");
        System.out.println(myCache.isPresent(99));
        log.debug("Проверяем наличие элемента по индексу 9");
        System.out.println(myCache.isPresent(9));
        log.debug("Проверяем наличие элемента по несуществующему полю элемент 99");
        System.out.println(myCache.isPresent((Integer) 99));
        log.debug("Работа с кешем");
        System.out.println("Проверяем наличие элемента по элементу 94");
        System.out.println(myCache.isPresent((Integer) 94));
        System.out.println("Проверяем наличие элемента по элементу null");
        try {
            System.out.println(myCache.isPresent((Integer) null));
        } catch (NotExistElementException e) {
            log.warn("Попытка поиска в кэше null элемента", e);
        }
        log.debug("Удаляем элемент 11");
        myCache.delete(11);
        myCache.printCache();
        log.debug("Пытаемся удалить null элемент из кеша");
        try {
            myCache.delete(null);
        } catch (NullPointerException e) {
            log.warn("Попытка удаления из кеша null элемента", e);
        }
        myCache.printCache();
        log.debug("Получение элемента по несуществующему полю индекс 99");
        try {
            System.out.println(myCache.get(99));
        } catch (CasheIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        log.debug("Получение элемента по полю индекс 9");
        try {
            System.out.println(myCache.get(9));
        } catch (CasheIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        myCache.printCache();
        log.debug("Очищаем кеш");
        myCache.clear();
        myCache.printCache();
        log.debug("Добавляем 3 элемента");
        myCache.add(84, 14);
        myCache.add(94, 15);
        myCache.add(104, 16);
        myCache.printCache();
        System.out.println();
        System.out.println();
        log.debug("Работа с Storage");
        log.debug("Создаем хранилище myStorageDefault по умолчанию");
        Storage<Integer> myStorageDefault = new Storage<>();
        myStorageDefault.printStorage();
        myStorageDefault.cacheStorage.printCache();
        log.debug("Создаем новое хранилище myStorage и сразу заполняем его");
        Storage<Integer> myStorage = new Storage<>(new Integer[]{1, 2, 3, 4, 5});
        myStorage.printStorage();
        log.debug("Добавляем элемент 6 в хранилище myStorage");
        myStorage.add(6);
        myStorage.printStorage();
        System.out.println("Добавляем элемент 7 в хранилище myStorage");
        myStorage.add(7);
        log.debug("Попытка получения последнего элемента из хранилища myStorage");
        System.out.println(myStorage.getLast());
        try {
            myStorage.add(null);
        } catch (NotExistElementException e) {
            log.warn("Попытка добавить null в хранилище myStorage", e);
        }
        myStorage.printStorage();
        log.debug("Попытка получения последнего элемента из хранилища myStorage");
        System.out.println(myStorage.getLast());
        myStorage.printStorage();
        myStorage.cacheStorage.printCache();
        log.debug("Попытка получения элемента из массива по индексу, которого еще нет в кэше");
        try {
            System.out.println(myStorage.get(5));
        } catch (CasheIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        myStorage.printStorage();
        myStorage.cacheStorage.printCache();
        log.debug("Попытка получения элемента из массива по индексу, который уже есть к кеше");
        try {
            System.out.println(myStorage.get(5));
        } catch (CasheIndexOutOfBoundsException e) {
            log.debug("Элемента в хранилище Storage нет", e);
        }
        myStorage.printStorage();
        myStorage.cacheStorage.printCache();
        log.debug("Попытка получения элемента из массива по индексу, которого вообще нет");
        try {
            System.out.println(myStorage.get(7));
        } catch (CasheIndexOutOfBoundsException e) {
            log.debug("Элемента в хранилище Storage нет");
        }
        myStorage.printStorage();
        myStorage.cacheStorage.printCache();
        log.debug("Попытка удаления последнего элемента из хранилища myStorage");
        myStorage.delete();
        myStorage.printStorage();
        myStorage.cacheStorage.printCache();
        log.debug("Очистка хранилища myStorage");
        myStorage.clear();
        myStorage.printStorage();
        myStorage.cacheStorage.printCache();
        log.debug("Попытка создания хранилище myStorageNewTest и заполнения его до максимума");
        Storage<String> myStorageNewTest = new Storage<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"});
        System.out.println("Попытка создания хранилища myStorage3 на 11 элементов");
        Storage<String> myStorage3 = new Storage<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"});
        myStorage3.printStorage();
        System.out.println(myStorage3.capacity);
        log.debug("Попытка добавления нового элемент в хранилище myStorageNewTest");
        myStorageNewTest.add("6");
        myStorageNewTest.printStorage();
        System.out.println(myStorageNewTest.capacity);
    }
}