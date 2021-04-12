package zaraev.epam.com;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class Storage<T> {
    public Object[] storage;
    public Cache<T> cacheStorage;
    public int capacity;

    /**
     * Параметризированный класс Storage
     * Cодержит storage - массив типа Т storage, capacity - длину массива storage, cacheStorage - объект типа Cache<T>
     * Содержит дефолтный конструктор, в котором создается наш массив типа Т, а так же объект кэша.
     * Второй конструктор принимает на вход массив элементов типа Т и сразу заполняет массив storage.
     */
    public Storage() {
        storage = new Object[10];
        capacity = 10;
        cacheStorage = new Cache<>(10);
        log.debug("Успешное создание и инициализация пустого хранилища размером в 10 элементов");
    }

    public Storage(T[] arrayElements) {
        try {
            storage = new Object[10];
            for (int i = 0; i < arrayElements.length; i++) {
                storage[i] = arrayElements[i];
            }
            capacity = 10;
            cacheStorage = new Cache<>(10);
        } catch (ArrayIndexOutOfBoundsException e) {
            log.warn("Попытка превышения размера хранилища при создании", e);
        }
        log.debug("Успешное создание, инициализация и заполнение хранилища размером в 10 элементов");
    }

    @Override
    public String toString() {
        return "Storage{" +
                "storage=" + Arrays.toString(storage) +
                ", cache=" + cacheStorage +
                '}';
    }

    /**
     * Добавления элемента в массив storage
     * Если мы достигли предела длины массива,
     * увеличиваем емкость нашего массива storage в 1.5 раза через отдельный метод.
     *
     * @param element - искомый элемент
     */
    public void add(T element) throws NotExistElementException {
        if (element == null) {
            throw new NotExistElementException("Не добавляй нулевой элемент");
        }
        for (int i = 0; i < storage.length; i++)
            if (storage[i] == null) {
                storage[i] = element;
                return;
            }
        int addIndex = capacity;
        increaseArrayLength();
        storage[addIndex] = element;
        log.debug("Элемент {} успешно добавлен в хранилище storage", element);
    }


    /**
     * Увеличение емкости нашего массива storage в 1.5 раза
     */
    public void increaseArrayLength() {
        capacity = (int) (capacity * 1.5);
        Object[] tempStorage = new Object[capacity];
        for (int i = 0; i < storage.length; i++) {
            tempStorage[i] = storage[i];
        }
        storage = tempStorage;
        log.debug("Размер хранилища Storage увеличен в 1.5 раза и составляет " + capacity);
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
                log.debug("Элемент {} удален из хранилища storage", element);
                return;
            }
        }
    }

    /**
     * Очищение всех элементов из нашего массива storage и кэша
     */
    public void clear() {
        for (int i = 0; i < storage.length; i++) {
            storage[i] = null;
        }
        log.info("Хранилище Storage очищено");
        cacheStorage.clear();
    }

    /**
     * Получение последнего элемента из массива (не null)
     *
     * @return - вернет элемент класса T
     */
    public T getLast() {
        for (int i = storage.length - 1; i >= 0; i--) {
            if (storage[i] != null) {
                log.debug("Последний не null элемент {} из Storage успешно получен", storage[i]);
                return (T) storage[i];
            }
        }
        log.debug("Последний элемент из Storage не получен - хранилище Storage пустое");
        return null;
    }

    /**
     * Получения элемента из массива Storage по индексу
     * Если такой элемент уже есть в кеше, возвращаем его из кеша
     * Если его нет в кеше, добавляаем объект из массива в кеш и возвращаем его
     *
     * @param index - индекс элемента в массиве
     * @return - вернет элемент класса T, найденный по индексу
     */
    @SuppressWarnings("unchecked")
    public T get(int index) throws CasheIndexOutOfBoundsException {
        if (storage[index] != null) {
            if (cacheStorage.isPresent((T) storage[index])) {
                log.debug("Элемент массива storage {} уже был в кеше и успешно получен из него", cacheStorage.get(index));
                return cacheStorage.get(index);
            } else {
                cacheStorage.add((T) storage[index], index);
                return (T) storage[index];
            }
        }
        throw new CasheIndexOutOfBoundsException("Нельзя получить нулевой элемент");
    }


    /**
     * Вывод в консоль всех элементов Storage
     */
    public void printStorage() {
        System.out.print(Arrays.toString(storage));
        System.out.println();
    }
}