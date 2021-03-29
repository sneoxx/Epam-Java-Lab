package zaraev.epam.com;

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

    /**
     * добавление элемента в кэш
     * добавление всегда происходит в конец массива. Если мы выходим за длину массива, то необходимо удалить самый первый элемент в массиве, сдвинуть весь массив влево и добавить новый элемент в конец массива
     *
     * @param element - искомый элемент добавлемый в кеш
     */
    public void add(T element, int index) {
        if (cache[capacity - 1] == null) {
            for (int i = 0; i < capacity; i++) {
                if (cache[i] == null) {
                    cache[i] = new CacheElement(element, index);
                    break;
                }
            }
        } else {
            if (!isPresent(element)) {
                shiftElementsLeft(capacity - 1);
                cache[capacity - 1] = new CacheElement(element, index);
            }
        }
    }

    /**
     * Удаление элемента из кэша, с последующим сдвигом всех элементов правее его влево
     *
     * @param element - удаляемый элемент
     */
    public void delete(T element) {
        for (int i = 0; i < capacity; i++) {
            if (cache[i] != null) {
                if (element.equals(cache[i].element)) {
                    cache[i] = null;
                    shiftElementsLeft(i);
                    cache[capacity - 1] = null;
                    break;
                }
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
            if (cache[i] != null) {
                if (cache[i].element.equals(element))
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
            if (cache[i] != null) {
                if (cache[i].index == index)
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
                    CacheElement tempCache = cache[i];
                    delete(cache[i].element);
                    add((T) tempCache.element, tempCache.index);
                    return (T) tempCache.element;
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
     * @param a - индес удаляемого элемента в массиве
     */
    public void shiftElementsLeft(int a) {
        for (int i = a; i < capacity - 1; i++) {
            cache[i] = cache[i + 1];
        }
    }

    /**
     * Вывод в консоль всех элементов кеша
     */
    public void printCache() {
        for (int i = 0; i < capacity; i++) {
            if (cache[i] != null)
                System.out.print("[" + cache[i].element + "," + cache[i].index + "] ");
        }
        System.out.println();
    }
}




