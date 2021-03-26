package zaraev.epam.com;

public class Cache <T> {
    public int capacity;
    public Object[] cache;

    public Cache(int capacity) {
        cache = new Object[capacity];
        this.capacity = capacity;
    }


    //   voidadd(element) метод добавления элемента в кэш (наш массив). ВАЖНО добавление всегда происходит в конец массива. Если мы выходим за длину массива, то необходимо удалить самый первый элемент в массиве, сдвинуть весь массив влево и добавить новый элемент в конец массива.
    public void add(T element) {
        if (cache[capacity] != null) {
            for (int i = 0; i < capacity; i++) {
                if (cache[i] == null)
                    cache[i] = element;

            }
        } else{
                Object[] tempArray = new Object[capacity];
                System.arraycopy(cache, 1, tempArray, 0, 9);
                System.arraycopy(tempArray, 0, cache, 0, 10);
                cache[capacity] = element;
            }

    }


    //  void delete(element) метод удаления элемента из кэша. При удалении мы должны будем сдвинуть оставшуюся часть массива влево.
    public <T> void delete(T element) {
        if (this.isPresent(element)) {
            for (int i = 0; i < capacity; i++) {
                if (cache[i].equals(element)) {
                    cache[i] = null;
                    Object[] tempArray = new Object[capacity - i - 1];
                    System.arraycopy(cache, i + 1, tempArray, 0, capacity - i - 1);
                    System.arraycopy(tempArray, 0, cache, i + 1, capacity - i - 1);
                    }
            }
        }
    }

    // boolean isPresent(element) метод определения есть ли искомый элемент в кэше.
    public <T> boolean isPresent(T element) {
        for (int i = 0; i < capacity; i++) {
            if (cache[i].equals(element))
            return true;
        }
        return false;
    }

    // elementget(element) метод получения объекта из кэша. При нахождении элемента в кэше его необходимо поместить в конец массива, с учетом сдвига остальных элементов влево.

    @SuppressWarnings("unchecked")
    public <T> T get(T element) {
        Object tmpCache = null;
        if (this.isPresent(element)) {
            for (int i = 0; i < capacity; i++) {
                if (cache[i].equals(element)) {
                    tmpCache = cache[i];
                    Object[] tempArray = new Object[capacity - i - 1];
                    System.arraycopy(cache, i + 1, tempArray, 0, capacity - i - 1);
                    System.arraycopy(tempArray, 0, cache, i + 1, capacity - i - 1);
                    this.get(element);
                }

            }
        }
        return (T) tmpCache;
    }

    void clear() {
        for (int i = 0; i < capacity; i++) {
            cache[i] = null;
        }
    }

}

