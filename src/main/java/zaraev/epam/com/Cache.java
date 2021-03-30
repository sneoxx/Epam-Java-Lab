package zaraev.epam.com;

import java.util.Arrays;

public class Cache<T> {
    public int capacity;
    public CacheElement<T>[] cache;

    /**
     * Конструктор параметризированного класса Cache, содержащий поле capacity с размером массива и поле Object[] cache - содержащее массив Object
     *
     * @param capacity - размер массива
     */
    public Cache(int capacity) {
        cache = new CacheElement[capacity];
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "{" +
                "cache=" + Arrays.toString(cache) +
                ", cacheCapacity=" + capacity +
                '}';
    }

    /**
     * добавление элемента в кэш
     * добавление всегда происходит в конец массива. Если мы выходим за длину массива, то необходимо удалить самый первый элемент в массиве, сдвинуть весь массив влево и добавить новый элемент в конец массива
     *
     * @param element - искомый элемент добавлемый в кеш
     */
    public void add(T element, int index) {
        if (element != null && !isPresent(element)) {
            if (cache[capacity - 1] == null) {
                for (int i = 0; i < capacity; i++) {
                    if (cache[i] == null) {
                        cache[i] = new CacheElement(element, index);
                        return;
                    }
                }
            }
                shiftElementsLeft(capacity - 1);
                cache[capacity - 1] = new CacheElement(element, index);
        }
    }

    /**
     * Удаление элемента из кэша, с последующим сдвигом всех элементов правее его влево
     *
     * @param element - удаляемый элемент
     */
    public void delete(T element) {
        for (int i = 0; i < capacity; i++) {
            if (cache[i] != null && element.equals(cache[i].element)) {
                cache[i] = null;
                shiftElementsLeft(i);
                cache[capacity - 1] = null;
                return;
            }
        }
    }

    /**
     * Поиск элемента в кэше
     *
     * @param element - элемент, который ищем
     * @return - вернет true при наличии элемента, при отсутствии элемента false
     */
    public boolean isPresent(T element) {
        for (int i = 0; i < capacity; i++) {
            if (cache[i] != null && cache[i].element.equals(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Поиск элемента в кэше
     *
     * @param index- индекс элемента, который ищем
     * @return - вернет true при наличии элемента, при отсутствии элемента false
     */
    public boolean isPresent(int index) {
        for (int i = 0; i < capacity; i++) {
            if (cache[i] != null && cache[i].index == index) {
                return true;
            }
        }
        return false;
    }

    /**
     * Получение элемента из кеша, при нахождении объекта переместить его в конец кеша, со сдвигом всех элементов правее его влево
     *
     * @param index - индекс получаемого элемента
     * @return - вернет элемент при наличии, при отсутствии вернет null
     */
    public T get(int index) {
        if (isPresent(index)) {
            for (int i = 0; i < cache.length; i++) {
                if (cache[i].index == index) {
                    CacheElement<T> tempCache = cache[i];
                    shiftElementsLeft(index);
                    cache[capacity - 1] = tempCache;
                    return tempCache.element;
                }
            }
        }
        return null;
    }

    /**
     * очистка кэша от всех элементов
     */
    void clear() {
        for (int i = 0; i < capacity; i++) {
            cache[i] = null;
        }
    }

    /**
     * Сдвиг всех элементов массива влево с удалением элемента стоящем на индексе из входного параметра
     *
     * @param index - индес удаляемого элемента в массиве
     */
    public void shiftElementsLeft(int index) {
        for (int i = index; i < capacity - 1; i++) {
            cache[i] = cache[i + 1];
        }
    }

    /**
     * Вывод в консоль всех элементов кеша
     */
    public void printCache() {
        System.out.print(Arrays.toString(cache));
        System.out.println();
    }
}




