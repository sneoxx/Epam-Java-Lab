package zaraev.epam.com;


import java.util.*;

public class WorkWithCollections {

    /**
     * Вывод на консоль коллекций listHuman
     */
    public void printHumanList(List<Human> list) {
        for (Human human : list) {
            System.out.println(human);
        }
        System.out.println();
    }

    /**
     * Вывод на консоль коллекции mapHuman
     */
    public void printHumanMap(Map<Integer, String> map) {
        for (Map.Entry<Integer, String> pair : map.entrySet()) {
            String key = pair.getKey().toString();
            String value = pair.getValue();
            System.out.println(key + ":" + value);
        }
        System.out.println();
    }

    /**
     * Находит дубли в коллекции listHuman и выводит их в консоль.
     */
    public void viewDuplicatesListHuman(List<Human> listHuman) {
        System.out.println("Выводим дубли");
        Set<Human> humanDuplicateSet = new HashSet<>();
        for (Human human : listHuman) {
            if (listHuman.indexOf(human) != listHuman.lastIndexOf(human)) {
                humanDuplicateSet.add(human);
            }
        }
        for (Human human : humanDuplicateSet) {
            System.out.println(human);
        }
        System.out.println();

    }

     /**
     * Удяляет дубли в коллекции listHuman
     */
    public void removeDuplicatesListHuman(List<Human> listHuman) {
        System.out.println("Удаляем дубли");
        Set<Human> tempSet = new LinkedHashSet<>(listHuman);
        listHuman.clear();
        listHuman.addAll(tempSet);
    }

    /**
     * Сортирует коллекцию listHuman по ФИО
     */
    public void sortByFullNameHuman(List<Human> listHuman) {
        System.out.println("Сортировка по ФИО");
        listHuman.sort(Comparator.comparing(Human::getFullName));
    }

    /**
     * Сортирует коллекцию listHuman по возрасту
     */
    public void sortByAgeHuman(List<Human> listHuman) {
        System.out.println("Сортировка по возрасту");
        listHuman.sort(Comparator.comparing(Human::getAge));
    }

    /**
     * Сортирует коллекцию listHuman по адресу
     */
    public void sortByAddressHuman(List<Human> listHuman) {
        System.out.println("Сортировка по адресу");
        Comparator comparator = Comparator.comparing(Human::getAddress);
        Collections.sort(listHuman, comparator);
    }

    /**
     * Сортирует коллекцию mapHuman по полю ключ
     */
    public <K, V> HashMap<K, V> sortMapByKey(HashMap<K, V> mapHuman) {
        System.out.println("Сортировка Мапы по ключу");
        TreeMap<K, V> sortedTreeMap = new TreeMap<>(mapHuman);
        HashMap<K, V> sortedMapHuman = new LinkedHashMap<>(sortedTreeMap);
        return sortedMapHuman;
    }

    /**
     * Сортирует коллекцию mapHuman по полю значение
     */
    public <T, V> HashMap<Integer, String> sortByValue(HashMap<Integer, String> mapHuman) {
        System.out.println("Сортировка Мапы по значению");
        List<Map.Entry<Integer, String>> list = new LinkedList<Map.Entry<Integer, String>>(mapHuman.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, String>>() {
            public int compare(Map.Entry<Integer, String> o1,
                               Map.Entry<Integer, String> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        HashMap<Integer, String> temp = new LinkedHashMap<Integer, String>();
        for (Map.Entry<Integer, String> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}