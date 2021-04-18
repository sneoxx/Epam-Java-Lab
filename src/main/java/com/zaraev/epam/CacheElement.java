package com.zaraev.epam;

import java.util.Objects;

/**
 * Параметриризированный класс CacheElement
 */
public class CacheElement<T> {

    private T element;

    private int index;

    public T getElement() {
        return element;
    }

    public int getIndex() {
        return index;
    }

    /**
     * Конструктор параметриризированного класса CacheElement
     *
     * @param element - элемент типа T
     * @param index   - индекс с которым был запрос элемента из массива.
     */
    public CacheElement(T element, int index) {
        this.element = element;
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CacheElement<?> that = (CacheElement<?>) o;
        return index == that.index && Objects.equals(element, that.element);
    }

    @Override
    public int hashCode() {
        return Objects.hash(element, index);
    }

    @Override
    public String toString() {
        return "{" +
                "element=" + element +
                ", index=" + index +
                '}';
    }
}