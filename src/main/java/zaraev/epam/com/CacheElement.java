package zaraev.epam.com;

public class CacheElement<T> {
    public T element;
    int index;

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

}

