package zaraev.epam.com;

import java.util.Arrays;

public class Storage<T> {
    public Object[] storage;
    public Cache<T> cacheStorage;
    public int capacity;

    /**
     * Параметризированный класс Storage
     * Cодержит storage - массив типа Т storage, capacity - длину массива storage, cacheStorage - объект типа Cache<T>
     * Содержит дефолтный конструктор, в котором создается наш массив типа Т, а так же объект кэша.
     * Второй конструктор принимает на вход массив элементов типа Т и сразузаполняет массив storage.
     */
    public Storage() {
        storage = new Object[10];
        capacity = 10;
        cacheStorage = new Cache<>(10);
    }

    public Storage(T[] arrayElements) {
        storage = new Object[10];
        for (int i = 0; i < arrayElements.length; i++) {
            storage[i] = arrayElements[i];
        }
        capacity = 10;
        cacheStorage = new Cache<>(10);
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
     * @param element - искомый элемент
     */
    public void add(T element) {
        for (int i = 0; i < storage.length; i++)
            if (storage[i] == null) {
                storage[i] = element;
                return;
            }
        int addIndex = capacity;
        increaseArrayLength();
        storage[addIndex] = element;
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
                return (T) storage[i];
            }
        }
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
    protected T get(int index) {
        if (cacheStorage.isPresent((T) storage[index])) {
            return cacheStorage.get(index);
        }
        cacheStorage.add((T) storage[index], index);
        return (T) storage[index];
    }

    /**
     * Вывод в консоль всех элементов Storage
     */
    public void printStorage() {
        System.out.print(Arrays.toString(storage));
        System.out.println();
    }
}