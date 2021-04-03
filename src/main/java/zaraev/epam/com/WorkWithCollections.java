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
     * Создает и заполняет коллекцию list 10 элементами с 3 дублями, по 1 дублю на каждого
     */
    public ArrayList<Human> createCollectionList() {
        ArrayList<Human> list = new ArrayList<Human>();
        list.add(new Human("Минаев Елисей Платонович", 23, new Address("Москва", "Ленина", 5, 6)));
        list.add(new Human("Николаева Дарина Тимофеевна", 33, new Address("Омск", "Октябрьская", 34, 2)));
        list.add(new Human("Софронова Варвара Константиновна", 37, new Address("Тольятти", "Московская", 12, 63)));
        list.add(new Human("Каев Арсений Георгиевич", 41, new Address("Самара", "Рябиновый", 26, 57)));
        list.add(new Human("Марва Арина Егоровна", 59, new Address("Орел", "Тополиная", 57, 98)));
        list.add(new Human("Захаров Даниил Максимович", 51, new Address("Киев", "Космонавтов", 46, 106)));
        list.add(new Human("Лопатин Артём Маркович", 28, new Address("Оскол", "Дзержинского", 85, 73)));
        list.add(new Human("Минаев Елисей Платонович", 23, new Address("Москва", "Ленина", 5, 6)));
        list.add(new Human("Николаева Дарина Тимофеевна", 33, new Address("Омск", "Октябрьская", 34, 2)));
        list.add(new Human("Софронова Варвара Константиновна", 37, new Address("Тольятти", "Московская", 12, 63)));
        return list;
    }

    /**
     * Находит дубли в коллекции listHuman и выводит их в консоль.
     */
    public void viewDuplicatesListHuman(List<Human> listHuman) {
        System.out.println("Выводим дубли");
        Set<Human> humanDuplicateSet = new HashSet<>();
        for (Human human : listHuman) {
            if (!humanDuplicateSet.add(human)) {
                System.out.println(human);
            }
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
     * Сортирует коллекцию listHuman по ФИО, создан отдельный класс компаратора
     */
    public void sortByFullNameHuman(List<Human> listHuman) {
        System.out.println("Сортировка по ФИО");
        Comparator<Human> comparator = new SortFullNameComparator();
        Collections.sort(listHuman, comparator);
    }

    /**
     * Сортирует коллекцию listHuman по возрасту, компаратор создали и переопеределили прямо в main
     */
    public void sortByAgeHuman(List<Human> listHuman) {
        System.out.println("Сортировка по возрасту");
        Comparator<Human> comparator = new Comparator<Human>() {
            @Override
            public int compare(Human o1, Human o2) {
                return o1.getAge() - o2.getAge();
            }
        };
        listHuman.sort(comparator);
    }

    /**
     * Сортирует коллекцию listHuman по адресу, компаратор создали прямо в main по сокращенной записи
     */
    public void sortByAddressHuman(List<Human> listHuman) {
        System.out.println("Сортировка по адресу");
        Comparator<Human> comparator = (o1, o2) -> o1.getAddress().compareTo(o2.getFullName());
        listHuman.sort(comparator);
    }

    /**
     * Создает и заполняет коллекцию map 10 элементами с 3 дублями, по 1 дублю на каждого
     */
    public HashMap<Integer, String> createCollectionMap() {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(55, "Минаев Елисей Платонович");
        map.put(82, "Николаева Дарина Тимофеевна");
        map.put(16, "Софронова Варвара Константиновна");
        map.put(24, "Каев Арсений Георгиевич");
        map.put(4, "Марва Арина Егоровна");
        map.put(94, "Захаров Даниил Максимович");
        map.put(27, "Лопатин Артём Маркович");
        return map;
    }

    /**
     * Создает и заполняет коллекцию map с правами пользователя
     */
    public HashMap<Role, String> createCollectionMapUser() {
        HashMap<Role, String> map = new HashMap<>();
        map.put(Role.ADMIN, "Полные админские права");
        map.put(Role.USER, "Стандартные права пользователя");
        map.put(Role.MODERATOR, "Права по модерированию контента");
        return map;
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