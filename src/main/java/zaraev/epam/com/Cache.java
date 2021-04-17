package zaraev.epam.com;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
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
        log.debug("Кеш на {} элементов успешно создан", capacity);
    }

    /**
     * Заполнение кеша Integer элементами
     *
     * @param cache - Инстанс Кеша типа Integer
     * @return - Заполенный инстанс кеша типа Integer
     */
    public Cache<Integer> fillCacheInteger(Cache<Integer> cache) {
        cache.add(11, 0);
        cache.add(24, 1);
        cache.add(34, 2);
        cache.add(44, 3);
        cache.add(54, 4);
        cache.add(64, 5);
        cache.add(74, 7);
        cache.add(84, 8);
        cache.add(94, 9);
        cache.add(104, 10);
        log.debug("Кеш {} заполнен полностью", cache);
        return cache;
    }

    @Override
    public String toString() {
        return "{" +
                "cache=" + Arrays.toString(cache) +
                ", cacheCapacity=" + capacity +
                '}';
    }

    /**
     * Добавление элемента в конец кэша. Если кеш заполенен, сдвигается
     * Если мы выходим за длину массива, то необходимо удалить самый первый элемент в массиве, сдвинуть весь массив влево и добавить новый элемент в конец массива
     *
     * @param element - искомый элемент добавлемый в кеш
     */
    public void add(T element, int index) throws NullPointerException {
        if (element != null) {
            if (!isPresent(element)) {
                if (cache[capacity - 1] == null) {

                    for (int i = 0; i < capacity; i++) {
                        if (cache[i] == null) {
                            cache[i] = new CacheElement(element, index);
                            log.debug("{} успешно добавлен в кеш ", cache[i]);
                            return;
                        }
                    }
                }
                shiftElementsLeft(capacity - 1);
                cache[capacity - 1] = new CacheElement(element, index);
                log.debug("{} добавлен в кеш", cache[capacity - 1]);
            }
        }
        log.warn("Попытка добавить null значение к кеш", element, index);
        return;
    }

    /**
     * Удаление элемента из кэша, с последующим сдвигом всех элементов правее его влево
     *
     * @param element - удаляемый элемент
     */
    public void delete(T element) throws NullPointerException {
        for (int i = 0; i < capacity; i++) {
            if (cache[i] != null && element.equals(cache[i].element)) {
                log.debug("Удаление элемента {} из кеша", cache[i].element);
                cache[i] = null;
                shiftElementsLeft(i);
                cache[capacity - 1] = null;
                return;
            }
        }
    }

    /**
     * Поиск элемента в кэше полю элемент
     *
     * @param element - элемент, который ищем
     * @return - вернет true при наличии элемента, при отсутствии элемента false
     */
    public boolean isPresent(T element) {
        if (element != null) {
            for (int i = 0; i < capacity; i++) {
                if (cache[i] != null && cache[i].element.equals(element)) {
                    log.debug("Элемент {} в кэше найден", element);
                    return true;
                }

            }
            log.debug("Элемента {} в кэше нет", element);
            return false;
        }
        throw new NotExistElementException("Попытка поиска в кэше null элемента");
    }

    /**
     * Поиск элемента в кэше по полю индекс
     *
     * @param index - поле индекс элемента, который ищем
     * @return - вернет true при наличии элемента, при отсутствии элемента false
     */
    public boolean isPresent(int index) {
        for (int i = 0; i < capacity; i++) {
            if (cache[i] != null && cache[i].index == index) {
                log.debug("Элемент с индексом {} в кеше найден", index);
                return true;
            }
        }
        log.debug(String.format("Элемента с индексом {} в кеше нет", index));
        return false;
    }

    /**
     * Получение элемента из кеша, при нахождении объекта перемещение его в конец кеша, со сдвигом всех элементов правее его влево
     *
     * @param index - индекс получаемого элемента
     * @return - вернет элемент при наличии, при отсутствии вернет null
     */
    public T get(int index) throws CasheIndexOutOfBoundsException {
        if (isPresent(index)) {
            for (int i = 0; i < cache.length; i++) {
                if (cache[i].index == index) {
                    CacheElement<T> tempCache = cache[i];
                    shiftElementsLeft(index);
                    cache[capacity - 1] = tempCache;
                    log.debug("Элемент {} c индексом {} из кеша успешно получен", tempCache.element, index);
                    return tempCache.element;
                }
            }
        }
        log.debug("Попытка получения элемента c индексом {} из кеша неудачна - такого элемента нет", index);
        throw new CasheIndexOutOfBoundsException("Попытка получения null элемента из кеша");
    }

    /**
     * Очистка кэша от всех элементов
     */
    void clear() {
        Arrays.fill(cache, null);
        log.debug("Кэш успешно очищен");
    }

    /**
     * Сдвиг всех элементов массива влево с удалением элемента стоящем на индексе из входного параметра
     *
     * @param index - индекс удаляемого элемента в массиве
     */
    public void shiftElementsLeft(int index) {
        for (int i = index; i < capacity - 1; i++) {
            cache[i] = cache[i + 1];
        }
        log.debug("Сдвиг всех элементов кеша влево с удалением элемента стоящем на указанном индексе" + index);
    }

    /**
     * Вывод в консоль всех элементов кеша
     */
    public void printCache() {
        System.out.print(Arrays.toString(cache));
        System.out.println();
    }
}