package zaraev.epam.com;

public class Cache<T> {
    public int capacity;
    public Object[] cache;

    public Cache(int capacity) {
        cache = new Object[capacity];
        this.capacity = capacity;
    }

    /**
     * добавление элемента в кэш
     * добавление всегда происходит в конец массива. Если мы выходим за длину массива, то необходимо удалить самый первый элемент в массиве, сдвинуть весь массив влево и добавить новый элемент в конец массива
     * @param element - искомый элемент добавлемый в кеш
     */
    public void add(T element) {
        if (cache[capacity - 1] != null) {
            for (int i = 0; i < capacity; i++) {
                if (cache[i] == null && !cache[i].equals(element)) {
                    cache[i] = element;
                    break;
                }
            }
        } else {
            shiftElementsLeft(capacity - 1);
            cache[capacity - 1] = element;
        }
    }

    /**
     * Удаление элемента из кэша, с последующим сдвигом всех элементов правее его влево
     * @param element - удаляемый элемент
     */
    public void delete(T element) {
        for (int i = 0; i < capacity; i++) {
            if (cache[i].equals(element)) {
                cache[i] = null;
                shiftElementsLeft(i);
                break;
            }
        }
    }

    /**
     * Поиск элемента в кэше
     * @param element - элемент, который ищем
     * @return - вернет true при наличии элемента, при отсутствии элемента false
     */
    public boolean isPresent(T element) {
        for (int i = 0; i < capacity; i++) {
            if (cache[i].equals(element))
                return true;
        }
        return false;
    }

    /**
     * Получение элемента из кеша, при нахождении объекта переместить его в конец кеша, со сдвигом всех элементов правее его влево
     * @param element - получаемый элемент
     * @return - вернет элемент при наличии, при отсутствии вернет null
     */
   @SuppressWarnings("unchecked")
    public T get(T element) {
        if (!isPresent(element)) {
            return null;
        }
        delete(element);
        add(element);
        return element;
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
     * @param a - индес удаляемого элемента в массиве
     */
    public void shiftElementsLeft(int a) {
        for (int i = a; i < capacity - 1; i++) {
            cache[i] = cache[i + 1];
        }
    }

}

