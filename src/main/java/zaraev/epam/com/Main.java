package zaraev.epam.com;

import lombok.extern.slf4j.Slf4j;

@Slf4j

public class Main {
    public static void main(String[] args) {
        log.info("Старт программы");
        Cache<Integer> myCache = new Cache<>(10);
        myCache.printCache();
        System.out.println("Работа с кешем");
        myCache.add(11, 0);
        myCache.add(24, 1);
        myCache.add(34, 2);
        myCache.add(44, 3);
        myCache.add(54, 4);
        myCache.add(64, 5);
        myCache.add(74, 7);
        myCache.add(84, 8);
        myCache.add(94, 9);
        myCache.add(104, 10);
        log.info("Кеш заполнен полностью");
        myCache.add(null, 10);
        System.out.println("Заполнили кеш полностью разными элементами");
        myCache.printCache();
        myCache.add(114, 11);
        System.out.println("Добавили новый уникальный элемент 114");
        myCache.printCache();
        myCache.add(94, 12);
        System.out.println("Добавили повторяющийся элемент 94");
        myCache.printCache();
        System.out.println("Проверяем наличие элемента по несуществующему полю индекс 99");
        System.out.println(myCache.isPresent(99));
        System.out.println("Проверяем наличие элемента по индексу 9");
        System.out.println(myCache.isPresent(9));
        System.out.println("Проверяем наличие элемента по несуществующему полю элемент 99");
        System.out.println(myCache.isPresent((Integer) 99));
        System.out.println("Проверяем наличие элемента по элементу 94");
        System.out.println(myCache.isPresent((Integer) 94));
        System.out.println("Удаляем элемент 11");
        myCache.delete(11);
        myCache.printCache();
        log.warn("Попытка удаления null элемента");
        myCache.delete(null);
        myCache.printCache();
        System.out.println("Получение элемента по несуществующему полю индекс 99");
        try {
            System.out.println(myCache.get(99));
        } catch (CasheIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        System.out.println("Получение элемента по полю индекс 9");
        try {
            System.out.println(myCache.get(9));
        } catch (CasheIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        myCache.printCache();
        System.out.println("Очищаем кеш");
        myCache.clear();
        log.info("Очистка кеша");
        myCache.printCache();
        System.out.println("Добавляем 3 элемента");
        myCache.add(84, 14);
        myCache.add(94, 15);
        myCache.add(104, 16);
        myCache.printCache();
        myCache.printCache();
        System.out.println();
        System.out.println();
        System.out.println("Работа с Storage");
        Storage<Integer> myStorage = new Storage<>();
        log.info("Создано новое хранилище myStorage");
        System.out.println("Создаем хранилище по умолчанию и получаем его длину");
        System.out.println(myStorage.capacity);
        myStorage.printStorage();
        System.out.println("Создаем новое хранилище и сразу заполняем его");
        Storage<Integer> myStorage1 = new Storage<>(new Integer[]{1, 2, 3, 4, 5});
        log.info("Создано новое хранилище myStorage1");
        myStorage1.printStorage();
        System.out.println("Добавляем элемент");
        myStorage1.add(6);
        myStorage1.printStorage();
        System.out.println("Получаем последний элемент");
        System.out.println(myStorage1.getLast());
        System.out.println("Добавляем элемент");
        myStorage1.add(7);
        try {
            myStorage1.add(null);
        } catch (NotExistElementException e) {
            log.info("Попытка добавить null", e);
        }
        myStorage1.printStorage();
        System.out.println("Получаем последний элемент");
        System.out.println(myStorage1.getLast());
        myStorage1.printStorage();
        System.out.println("Содержимое кеша");
        myStorage1.cacheStorage.printCache();
        System.out.println("Получение элемента из массива по индексу, если его нет в кеше занесем в кеш");
        try {
            System.out.println(myStorage1.get(5));
        } catch (CasheIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        myStorage1.cacheStorage.printCache();
        System.out.println("Получение элемента из массива по индексу, если его нет в кеше занесем в кеш");
        try {
            System.out.println(myStorage1.get(6));
        } catch (CasheIndexOutOfBoundsException e) {
            log.info("Элемента в хранилище Storage нет", e);
        }
        System.out.print("Cashe");
        myStorage1.cacheStorage.printCache();
        System.out.println("Получение элемента из массива по индексу, который уже есть к кеше");
        try {
            System.out.println(myStorage1.get(6));
        } catch (CasheIndexOutOfBoundsException e) {
            log.info("Элемента в хранилище Storage нет", e);
        }
        myStorage1.printStorage();
        System.out.print("Cashe");
        myStorage1.cacheStorage.printCache();
        System.out.println("Получение элемента из массива по индексу, которого вообще нет");
        try {
            System.out.println(myStorage1.get(7));
        } catch (CasheIndexOutOfBoundsException e) {
            log.info("Элемента в хранилище Storage нет", e);
        }
        myStorage1.printStorage();
        System.out.print("Cashe");
        myStorage1.cacheStorage.printCache();
        System.out.println("Удаление последнего элемента из массива и удаление его из кеша");
        myStorage1.delete();
        myStorage1.printStorage();
        System.out.print("Cashe");
        myStorage1.cacheStorage.printCache();
        System.out.println("Очистка массива и кеша");
        myStorage1.clear();
        log.info("Очистка хранилища myStorage1 и его кеша");
        myStorage1.printStorage();
        System.out.print("Cashe");
        myStorage1.cacheStorage.printCache();
        System.out.println("Создаем новое хранилище и сразу заполняем его до максимума");
        Storage<String> myStorage2 = new Storage<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"});
        log.info("Создано новое хранилище myStorage1 и заполнено до максимума");
        System.out.println("Попытка создания хранилища на 11 элементов");
        Storage<String> myStorage3 = new Storage<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"});
        myStorage2.printStorage();
        System.out.println(myStorage2.capacity);
        System.out.println("Добавляем элемент и расширяем хранилище");
        myStorage2.add("6");
        log.info("Хранилище myStorage1 расширено в 1,5 раза после добавления нового элемента");
        myStorage2.printStorage();
        System.out.println(myStorage2.capacity);
    }
}