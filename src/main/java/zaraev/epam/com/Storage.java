package zaraev.epam.com;

import java.util.Arrays;

public class Storage<T> {
    public Object[] storage;
    public Cache<T> cacheStorage;
    public int capacity;

    /**
     * Параметризированный класс Storage
     * Cодержит storage - массивтипа Т storage, capacity - длину массива storage, cacheStorage - объект типа Cache<T>
     * Содержит дефолтный конструктор, в котором создается наш массив типа Т, а так же объект кэша.
     * Второй конструктор принимает на вход массив элементов типа Т и сразузаполняет массив storage.
     */
    public Storage() {
        storage = new Object[10];
        this.capacity = 10;
        cacheStorage = new Cache<>(10);
    }

    public Storage(Object[] arrayElements) {
        this.storage = arrayElements;
        this.capacity = arrayElements.length;
    }

    /**
     * Добавления элемента в массив storage
     * Если мы достигли предела длины массива, увеличиваем емкость нашего массива storage в 1.5 раза.
     *
     * @param element - искомый элемент
     */
    public void add(T element) {
        if (storage[capacity - 1] == null) {
            for (int i = 0; i < capacity; i++) {
                if (storage[i] == null) {
                    storage[i] = element;
                }
            }
        } else {
            int tempLength = capacity;
            this.capacity = (int) (capacity * 1.5);
            Object[] tempStorage = new Object[(int) (capacity * 1.5)];
            for (int i = 0; i < tempLength; i++) {
                tempStorage[i] = storage[i];
            }
            storage = tempStorage;
            storage[tempLength] = element;
        }
    }

    /**
     * Удаление последнего элемента из массива storage и удаления его из кеша
     */
    public void delete() {
        T element = getLast();
        if (cacheStorage.isPresent(element)) {
            cacheStorage.delete(element);
        }
        for (int i = 0; i < capacity; i++) {
            if (storage.equals(element)) {
                storage[i] = null;
            }
        }

    }

    /**
     * Очищение всех элементов из нашего массива storage и кэша
     */
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            storage[i] = null;
        }
        cacheStorage.clear();
    }

    /**
     * Получение последнего элемента из массива (не null)
     *
     * @return - вернет элемент класса T
     */
    public T getLast() {
        for (int i = 0; i < capacity; i++) {
            if (storage[i] == null) {
                return (T) storage[i - 1];
            }
        }
        return (T) storage[capacity - 1];
    }

    /**
     * Получения элемента из массива Storage по индексу
     * Если такой элемент уже есть в кеше, возвращаем его из кеша
     * Если его нет в кеше, добавляаем объект из массива  в кеш и возвращаем его
     *
     * @param index - индекс элемента в массиве
     * @return - вернет элемент класса T, найденный по индексу
     */
    public T get(int index) {
        if (cacheStorage.isPresent((T) storage[index]))
            return cacheStorage.get(index);
        else
            cacheStorage.add((T) storage[index], index);
        return (T) storage[index];
    }

    /**
     * Вывод в консоль всех элементов Storage
     */
    public void printStorage() {
        //for (int i = 0; i < capacity; i++) {
        // if (storage[i] != null) {
        System.out.print(Arrays.toString(storage));
        //  System.out.print("[" + storage[i] + "] ");
        //  }
        //}
        System.out.println();
    }
}



