package com.zaraev.epam;

import lombok.extern.slf4j.Slf4j;
import com.zaraev.epam.exceptions.CasheIndexOutOfBoundsException;
import com.zaraev.epam.exceptions.NotExistElementException;

import java.util.Arrays;

/**
 * Параметризированный класс Cache. Cодержит поля:
 * cache - массив элементов типа CacheElement
 * capacity - длина массива cache
 *
 * @param <T>
 */
@Slf4j
public class Cache<T> {

    private CacheElement<T>[] cache;

    private int capacity;

    public int getCapacity() {
        return capacity;
    }

    /**
     * Конструктор, в котором создается массив элементов типа CacheElement с размеров из входного парметра
     *
     * @param capacity - размер массива
     */
    @SuppressWarnings("unchecked")
    public Cache(int capacity) {
        // cache = new CacheElement[capacity];
        cache = (CacheElement<T>[]) new CacheElement<?>[capacity];
        this.capacity = capacity;
        log.info("Кеш на {} элементов успешно создан", capacity);
    }

    @Override
    public String toString() {
        return "{" +
                "cache=" + Arrays.toString(cache) +
                ", cacheCapacity=" + capacity +
                '}';
    }

    /**
     * Добавление элемента в конец кэша. Если кеш заполенен, удаляется первый элемент, весь сдвигается влево и элемент добавляется в конец
     *
     * @param element - искомый элемент добавлемый в кеш
     */
    public void add(T element, int index) throws NullPointerException {
        if (element != null) {
            if (!isPresent(element)) {
                if (cache[capacity - 1] == null) {
                    for (int i = 0; i < capacity; i++) {
                        if (cache[i] == null) {
                            cache[i] = new CacheElement<>(element, index);
                            log.info("{} успешно добавлен в кэш ", cache[i]);
                            return;
                        }
                    }
                }
                shiftElementsLeft(capacity - 1);
                cache[capacity - 1] = new CacheElement<>(element, index);
                log.info("{} успешно добавлен в кэш", cache[capacity - 1]);
                return;
            }
            log.info("Элемент {} {} уже был в кэше и добавлен не будет ", element, index);
            return;
        }
        log.warn("Попытка добавить null значение к кэш", element, index);
        return;
    }

    /**
     * Удаление элемента из кэша, с последующим сдвигом всех элементов правее его влево
     *
     * @param element - удаляемый элемент
     */
    public void delete(T element) throws NullPointerException {
        for (int i = 0; i < capacity; i++) {
            if (cache[i] != null && element.equals(cache[i].getElement())) {
                log.debug("Удаление элемента {} из кэша", cache[i].getElement());
                cache[i] = null;
                shiftElementsLeft(i);
                cache[capacity - 1] = null;
                return;
            }
        }
    }

    /**
     * Поиск элемента в кэше по полю element типа CacheElement
     *
     * @param element - поле элемент,элемента который ищем
     * @return - вернет true при наличии элемента, при отсутствии элемента false
     */
    public boolean isPresent(T element) {
        if (element != null) {
            for (int i = 0; i < capacity; i++) {
                if (cache[i] != null && cache[i].getElement().equals(element)) {
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
     * Поиск элемента в кэше по полю index типа CacheElement
     *
     * @param index - поле индекс элемента, который ищем
     * @return - вернет true при наличии элемента, при отсутствии элемента false
     */
    public boolean isPresent(int index) {
        for (int i = 0; i < capacity; i++) {
            if (cache[i] != null && cache[i].getIndex() == index) {
                log.debug("Элемент с индексом {} в кэше найден", index);
                return true;
            }
        }
        log.debug("Элемента с индексом {} в кэше нет", index);
        return false;
    }

    /**
     * Получение элемента из кеша по полю index, при нахождении, перемещение его в конец кеша, со сдвигом всех элементов правее его влево
     *
     * @param index - индекс получаемого элемента
     * @return - вернет элемент при наличии, при отсутствии вернет null
     */
    public T get(int index) throws CasheIndexOutOfBoundsException {
        if (isPresent(index)) {
            for (int i = 0; i < cache.length; i++) {
                if (cache[i].getIndex() == index) {
                    CacheElement<T> tempCache = cache[i];
                    shiftElementsLeft(index);
                    cache[capacity - 1] = tempCache;
                    log.info("Элемент {} c индексом {} из кэша успешно получен", tempCache.getElement(), index);
                    return tempCache.getElement();
                }
            }
        }
        log.info("Попытка получения элемента c индексом {} из кэша неудачна - такого элемента нет", index);
        throw new CasheIndexOutOfBoundsException("Попытка получения null элемента из кэша");
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
        log.debug("Сдвиг всех элементов кэша влево с удалением элемента стоящем на указанном индексе " + index);
    }

    /**
     * Вывод в консоль всех элементов кеша
     */
    public void printCache() {
        System.out.print(Arrays.toString(cache));
        System.out.println();
    }
}