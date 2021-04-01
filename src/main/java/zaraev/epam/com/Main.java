package zaraev.epam.com;


public class Main {
    public static void main(String[] args) {
        System.out.println("Выводим список людей \n");
        WorkWithCollections myWorkWithCollections = new WorkWithCollections();
        System.out.println(myWorkWithCollections);
        myWorkWithCollections.createHumanList();
        myWorkWithCollections.printHumanList();
        myWorkWithCollections.viewDuplicatesListHuman();
        myWorkWithCollections.removeDuplicatesListHuman();
        myWorkWithCollections.printHumanList();
        myWorkWithCollections.sortByFioHuman();
        myWorkWithCollections.printHumanList();
        myWorkWithCollections.sortByAgeHuman();
        myWorkWithCollections.printHumanList();
        myWorkWithCollections.sortByAddressHuman();
        myWorkWithCollections.printHumanList();
        User myUser = new User("Ivan Petrov", Role.USER);
        myUser.welcomeMessage(myUser);
        System.out.println(" ");
        myWorkWithCollections.createHumanMap();
        myWorkWithCollections.printHumanMap();
        myWorkWithCollections.sortMapByKey();
        myWorkWithCollections.printHumanMap();
    }
}