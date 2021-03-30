package zaraev.epam.com;

import java.util.Objects;

public class CacheElement<T> {
    public T element;
    public int index;

    /**
     * Парметриризированный класс CacheElement
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