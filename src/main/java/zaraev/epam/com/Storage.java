package zaraev.epam.com;

import java.util.Arrays;
import java.util.Objects;

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
        cacheStorage = new Cache<>(arrayElements.length);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Storage<?> storage1 = (Storage<?>) o;
        return capacity == storage1.capacity && Arrays.equals(storage, storage1.storage) && Objects.equals(cacheStorage, storage1.cacheStorage);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(cacheStorage, capacity);
        result = 31 * result + Arrays.hashCode(storage);
        return result;
    }

    /**
     * Добавления элемента в массив storage
     * Если мы достигли предела длины массива, увеличиваем емкость нашего массива storage в 1.5 раза.
     *
     * @param element - искомый элемент
     */
    public void add(T element) {
        for (int i = 0; i < capacity; i++)
            if (storage[i] == null) {
                storage[i] = element;
                return;
            }
        if (storage[capacity - 1] != null) {
            int tempLength = capacity;
            capacity = (int) (capacity * 1.5);
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
            if (storage[i].equals(element)) {
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
        for (int i = capacity - 1; i >= 0; i--) {
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
    /*
    public T get(int index) {
        if (cacheStorage.isPresent((T) storage[index])) {
            return cacheStorage.get(index);
        }
         cacheStorage.add((T) storage[index], index);
         return (T) storage[index];
        }
     */
    public T get(int index) {
        if (cacheStorage.isPresent(index)) {
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

    public String toString() {
        return "Storage{" +
                "storage=" + Arrays.toString(storage) +
                ", cache=" + cacheStorage +
                '}';
    }
}




