package zaraev.epam.com;


import java.util.*;

public class WorkWithCollections {
    ArrayList<Human> listHuman = new ArrayList<Human>();
    HashMap<Integer, String> mapHuman = new HashMap<>();
    Set<Integer> setHuman = new HashSet<>();

    @Override
    public String toString() {
        return "WorkWithCollections{" +
                "listHuman=" + listHuman +
                '}';
    }

    /**
     * Вывод на консоль коллекций listHuman
     */
    public void printHumanList() {
        for (Human human : listHuman) {
            System.out.println(human);
        }
        System.out.println();
    }

    /**
     * Вывод на консоль коллекции mapHuman
     */
    public void printHumanMap() {
        for (Map.Entry<Integer, String> pair : mapHuman.entrySet())
        {
            String key = pair.getKey().toString();
            String value = pair.getValue();
            System.out.println(key + ":" + value);
        }
        System.out.println();
    }

    /**
     * Заполняет коллекцию listHuman 10 элементами с 3 дублями, по 1 дублю на каждого
     */
    public void createHumanList() {
        System.out.println("Заполняем коллекцию");
        listHuman.add(new Human("Минаев Елисей Платонович", 23, new Address("Москва", "Ленина", 5, 6)));
        listHuman.add(new Human("Николаева Дарина Тимофеевна", 33, new Address("Омск", "Октябрьская", 34, 2)));
        listHuman.add(new Human("Софронова Варвара Константиновна", 37, new Address("Тольятти", "Московская", 12, 63)));
        listHuman.add(new Human("Каев Арсений Георгиевич", 41, new Address("Самара", "Рябиновый", 26, 57)));
        listHuman.add(new Human("Марва Арина Егоровна", 59, new Address("Орел", "Тополиная", 57, 98)));
        listHuman.add(new Human("Захаров Даниил Максимович", 51, new Address("Киев", "Космонавтов", 46, 106)));
        listHuman.add(new Human("Лопатин Артём Маркович", 28, new Address("Оскол", "Дзержинского", 85, 73)));
        listHuman.add(new Human("Минаев Елисей Платонович", 23, new Address("Москва", "Ленина", 5, 6)));
        listHuman.add(new Human("Николаева Дарина Тимофеевна", 33, new Address("Омск", "Октябрьская", 34, 2)));
        listHuman.add(new Human("Софронова Варвара Константиновна", 37, new Address("Тольятти", "Московская", 12, 63)));
    }

    /**
     * Заполняет коллекцию mapHuman
     */
    public void createHumanMap() {
        System.out.println("Заполняем коллекцию мапы ");
        mapHuman.put(55, "Минаев Елисей Платонович");
        mapHuman.put(82, "Николаева Дарина Тимофеевна");
        mapHuman.put(16, "Софронова Варвара Константиновна");
        mapHuman.put(24, "Каев Арсений Георгиевич");
        mapHuman.put(4, "Марва Арина Егоровна");
        mapHuman.put(94, "Захаров Даниил Максимович");
        mapHuman.put(27, "Лопатин Артём Маркович");
    }

    /**
     * Находит дубли в коллекции listHuman и выводит их в консоль.
     */
    public void viewDuplicatesListHuman() {
        System.out.println("Выводим дубли");
        Set<Human> humanDuplicateSet = new HashSet<>();
        for (int i = 0; i < listHuman.size(); i++) {
            Human e1 = listHuman.get(i);
            for (int j = 0; j < listHuman.size(); j++) {
                if (i == j) continue;
                Human e2 = listHuman.get(j);
                if (e1.equals(e2)) {
                    humanDuplicateSet.add(e2);
                }
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
    public void removeDuplicatesListHuman() {
        System.out.println("Удаляем дубли");
        Set<Human> tempSet = new LinkedHashSet<>(listHuman);
        listHuman.clear();
        listHuman.addAll(tempSet);
    }

    /**
     * Сортирует коллекцию listHuman по ФИО
     */
    public void sortByFioHuman() {
        System.out.println("Сортировка по ФИО");
        listHuman.sort(Comparator.comparing(Human::getFio));
    }

    /**
     * Сортирует коллекцию listHuman по возрасту
     */
    public void sortByAgeHuman() {
        System.out.println("Сортировка по возрасту");
        listHuman.sort(Comparator.comparing(Human::getAge));
    }

    /**
     * Сортирует коллекцию listHuman по адресу
     */
    public void sortByAddressHuman() {
        System.out.println("Сортировка по адресу");
        //listHuman.sort(Comparator.comparing(Human::getAddress));
        Comparator comparator = Comparator.comparing(Human::getAddress);
        Collections.sort(listHuman, comparator);
    }

    /**
     * Сортирует коллекцию mapHuman по полю ключ
     */
    public void sortMapByKey() {
        System.out.println("Сортировка Мапы по ключу");
        TreeMap<Integer, String> sortedTreeMap = new TreeMap<>(mapHuman);
        mapHuman.clear();
        mapHuman = new LinkedHashMap<>(sortedTreeMap);
    }

    /**
     * Сортирует коллекцию mapHuman по полю значение
     */
    public void sortMapByValue() {
        System.out.println("Сортировка Мапы по значению");
//        List<Integer> mapKeys = new ArrayList<>(mapHuman.keySet());
//        List<String> mapValues = new ArrayList<>(mapHuman.values());
//
//        System.out.println(mapValues);
//        System.out.println(mapKeys);
        SortedSet mapValues = new TreeSet<>(mapHuman.values());
        System.out.println(mapValues);
        //List<String> mapValues = new ArrayList<>(mapHuman.values());
        //Collections.sort(mapValues);
        //System.out.println(mapValues);
    }
}