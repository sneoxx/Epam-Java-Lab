package com.zaraev.epam;

import lombok.extern.slf4j.Slf4j;
import com.zaraev.epam.exceptions.CasheIndexOutOfBoundsException;
import com.zaraev.epam.exceptions.NotExistElementException;

import java.util.Arrays;

/**
 * Параметризированный класс Storage. Cодержит поля:
 * storage - массив типа Т storage
 * capacity - длина массива storage,
 * cacheStorage - объект типа Cache<T>
 *
 * @param <T>
 */
@Slf4j
public class Storage<T> {

    private Object[] storage;

    private Cache<T> cacheStorage;

    private int capacity;

    public Cache<T> getCacheStorage() {
        return cacheStorage;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "storage=" + Arrays.toString(storage) +
                ", cache=" + cacheStorage +
                '}';
    }

    /**
     * Дефолтный конструктор, в котором создается массив storage типа Т и объект кэша.
     */
    public Storage() {
        storage = new Object[10];
        capacity = 10;
        log.info("Успешное создание и инициализация пустого хранилища storage размером в 10 элементов");
        cacheStorage = new Cache<>(10);
    }

    /**
     * Конструктор принимает на вход массив элементов типа Т и заполняет ими массив storage. Также создает объект кэша
     *
     * @param arrayElements - массив элементов типа Т
     */
    public Storage(T[] arrayElements) {
        try {
            storage = new Object[10];
            System.arraycopy(arrayElements, 0, storage, 0, arrayElements.length);
            capacity = 10;
            log.info("Успешное создание и заполенение хранилища storage размером в 10 элементов");
            cacheStorage = new Cache<>(10);
        } catch (ArrayIndexOutOfBoundsException e) {
            log.warn("Попытка превышения размера хранилища при создании", e);
        }
    }

    /**
     * Добавления элемента в первую свободную ячейку массива storage
     * Если массив уже заполенен, его емкость увеличится в 1,5 раза и элемент будет добавлен в первую свободную ячейку
     *
     * @param element - добавялемый элемент типа T
     */
    public void add(T element) throws NotExistElementException {
        if (element == null) {
            throw new NotExistElementException("Попытка добавить null в хранилище");
        }
        for (int i = 0; i < storage.length; i++)
            if (storage[i] == null) {
                storage[i] = element;
                log.info("Элемент {} успешно добавлен в хранилище storage", element);
                return;
            }
        int addIndex = capacity;
        increaseArrayLength();
        storage[addIndex] = element;
        log.info("Элемент {} успешно добавлен в хранилище storage", element);
    }

    /**
     * Увеличение емкости массива storage в 1.5 раза
     */
    public void increaseArrayLength() {
        capacity = (int) (capacity * 1.5);
        Object[] tempStorage = new Object[capacity];
        for (int i = 0; i < storage.length; i++) {
            tempStorage[i] = storage[i];
        }
        storage = tempStorage;
        log.info("Размер хранилища увеличен в 1.5 раза и составляет " + capacity);
    }

    /**
     * Удаление последнего элемента из массива storage и удаления его из кеша
     */
    public void delete() {
        T element = getLast();
        if (cacheStorage.isPresent(element)) {
            cacheStorage.delete(element);
        }
        for (int i = 0; i < storage.length; i++) {
            if (storage[i].equals(element)) {
                storage[i] = null;
                log.info("Элемент {} удален из хранилища storage", element);
                return;
            }
        }
    }

    /**
     * Удаление всех элементов из нашего массива storage и кэша
     */
    public void clear() {
        Arrays.fill(storage, null);
        log.info("Хранилище Storage очищено");
        cacheStorage.clear();
    }

    /**
     * Получение последнего элемента из массива storage
     *
     * @return - элемент класса T
     */
    public T getLast() throws NotExistElementException {
        for (int i = storage.length - 1; i >= 0; i--) {
            if (storage[i] != null) {
                log.info("Последний элемент из хранилища Storage успешно получен и равен: " + storage[i]);
                return (T) storage[i];
            }
        }
        log.info("Последний элемент из Storage не получен - хранилище Storage пустое");
        throw new NotExistElementException("Последний элемент получить не удалось - хранилище Storage пустое");
    }

    /**
     * Получение элемента CacheElement из массива Storage по полю index
     *
     * @param index - индекс элемента в массиве
     * @return - вернет элемент класса T, найденный по индексу
     */
    @SuppressWarnings("unchecked")
    public T get(int index) throws CasheIndexOutOfBoundsException {
        if (storage[index] != null) {
            if (!cacheStorage.isPresent((T) storage[index])) {
                cacheStorage.add((T) storage[index], index);
                log.info("Элемент массива storage element={} успешно получен", storage[index]);
                return (T) storage[index];
            } else {
                log.info("Элемент массива storage element={} уже был в кеше и успешно получен из него", storage[index]);
                return cacheStorage.get(index);
            }
        }
        throw new CasheIndexOutOfBoundsException("Попытка получения null элемента из storage");
    }

    /**
     * Вывод в консоль всех элементов Storage
     */
    public void printStorage() {
        System.out.print(Arrays.toString(storage));
        System.out.println();
    }
}