package zaraev.epam.com;

public class Main {
    public static void main(String[] args) {

        Cache<Integer> myCache = new Cache<>(10);
        System.out.println("Работа с кешем");
        myCache.add(11, 0);
        myCache.add(24, 1);
        myCache.add(34, 2);
        myCache.add(44, 3);
        myCache.add(54, 4);
        myCache.add(64, 5);
        myCache.add(74, 7);
        myCache.add(84, 8);
        myCache.add(94, 9);
        myCache.add(104, 10);
        System.out.println("Заполнили кеш полностью разными элементами");
        myCache.printCache();
        myCache.add(114, 11);
        System.out.println("Добавили новый уникальный элемент 114");
        myCache.printCache();
        myCache.add(94, 12);
        System.out.println("Добавили повторяющийся элемент 94");
        myCache.printCache();
        System.out.println("Проверяем наличие элемента по несуществующему полю индекс 99");
        System.out.println(myCache.isPresent(99));
        System.out.println("Проверяем наличие элемента по индексу 9");
        System.out.println(myCache.isPresent(9));
        System.out.println("Проверяем наличие элемента по несуществующему полю элемент 99");
        System.out.println(myCache.isPresent((Integer) 99));
        System.out.println("Проверяем наличие элемента по элементу 94");
        System.out.println(myCache.isPresent((Integer) 94));
        System.out.println("Удаляем элемент 11");
        myCache.delete(11);
        myCache.printCache();
        System.out.println("Получение элемента по несуществующему полю индекс 99");
        System.out.println(myCache.get(99));
        System.out.println("Получение элемента по полю индекс 9");
        System.out.println(myCache.get(9));
        myCache.printCache();
        System.out.println("Очищаем кеш");
        myCache.clear();
        myCache.add(84, 14);
        myCache.add(94, 15);
        myCache.add(104, 16);
        myCache.printCache();
        System.out.println("Работа с Storage");
        Storage<Integer> myStorage = new Storage<>();
        System.out.println(myStorage.capacity);
        myStorage.printStorage();
        Storage<Integer> myStorage1 = new Storage<>(new Integer[]{1, 2, 3, 4, 5});
        myStorage1.printStorage();
        //System.out.println(myStorage1.toString());
        //myStorage1.printStorage();
        //myStorage.printStorage();
        //myStorage =  new Storage();
        //myStorage.printStorage();
    }
}



