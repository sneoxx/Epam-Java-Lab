package zaraev.epam.com;


import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        /**
         * 1 Заполняем коллекцию listHuman 10 элементами с 3 дублями, по 1 дублю на каждого
         */
        ArrayList<Human> myListHuman = new ArrayList<Human>();
        System.out.println("Заполняем коллекцию List");
        myListHuman.add(new Human("Минаев Елисей Платонович", 23, new Address("Москва", "Ленина", 5, 6)));
        myListHuman.add(new Human("Николаева Дарина Тимофеевна", 33, new Address("Омск", "Октябрьская", 34, 2)));
        myListHuman.add(new Human("Софронова Варвара Константиновна", 37, new Address("Тольятти", "Московская", 12, 63)));
        myListHuman.add(new Human("Каев Арсений Георгиевич", 41, new Address("Самара", "Рябиновый", 26, 57)));
        myListHuman.add(new Human("Марва Арина Егоровна", 59, new Address("Орел", "Тополиная", 57, 98)));
        myListHuman.add(new Human("Захаров Даниил Максимович", 51, new Address("Киев", "Космонавтов", 46, 106)));
        myListHuman.add(new Human("Лопатин Артём Маркович", 28, new Address("Оскол", "Дзержинского", 85, 73)));
        myListHuman.add(new Human("Минаев Елисей Платонович", 23, new Address("Москва", "Ленина", 5, 6)));
        myListHuman.add(new Human("Николаева Дарина Тимофеевна", 33, new Address("Омск", "Октябрьская", 34, 2)));
        myListHuman.add(new Human("Софронова Варвара Константиновна", 37, new Address("Тольятти", "Московская", 12, 63)));
        WorkWithCollections myWorkWithCollections = new WorkWithCollections();
        myWorkWithCollections.printHumanList(myListHuman);
        myWorkWithCollections.viewDuplicatesListHuman(myListHuman);
        myWorkWithCollections.removeDuplicatesListHuman(myListHuman);
        myWorkWithCollections.printHumanList(myListHuman);
        myWorkWithCollections.sortByFullNameHuman(myListHuman);
        myWorkWithCollections.printHumanList(myListHuman);
        myWorkWithCollections.sortByAgeHuman(myListHuman);
        myWorkWithCollections.printHumanList(myListHuman);
        myWorkWithCollections.sortByAddressHuman(myListHuman);
        myWorkWithCollections.printHumanList(myListHuman);
        HashMap<Role, String> myRoleMap = new HashMap<>();
        myRoleMap.put(Role.ADMIN, "Полные админские права");
        myRoleMap.put(Role.USER, "Стандартные права пользователя");
        myRoleMap.put(Role.MODERATOR, "Права по модерированию контента");
        User myUser = new User("Ivan Petrov", Role.USER, myRoleMap);
        myUser.welcomeMessage(myUser);
        System.out.println(" ");
        HashMap<Integer, String> myMapHuman = new HashMap<>();
        System.out.println("Заполняем коллекцию мапы ");
        myMapHuman.put(55, "Минаев Елисей Платонович");
        myMapHuman.put(82, "Николаева Дарина Тимофеевна");
        myMapHuman.put(16, "Софронова Варвара Константиновна");
        myMapHuman.put(24, "Каев Арсений Георгиевич");
        myMapHuman.put(4, "Марва Арина Егоровна");
        myMapHuman.put(94, "Захаров Даниил Максимович");
        myMapHuman.put(27, "Лопатин Артём Маркович");
        myWorkWithCollections.printHumanMap(myMapHuman);
        myWorkWithCollections.printHumanMap(myWorkWithCollections.sortMapByKey(myMapHuman));
        myWorkWithCollections.printHumanMap(myWorkWithCollections.sortByValue(myMapHuman));
    }
}