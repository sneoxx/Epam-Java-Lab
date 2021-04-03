package zaraev.epam.com;


import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {


        WorkWithCollections myWorkWithCollections = new WorkWithCollections();
        System.out.println("Заполняем коллекцию List");
        ArrayList<Human> myListHuman = myWorkWithCollections.createCollectionList();
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
        User myUser = new User("Ivan Petrov", Role.USER, myWorkWithCollections.createCollectionMapUser());
        myUser.welcomeMessage(myUser);
        System.out.println(" ");
        System.out.println("Заполняем коллекцию мапы ");
        HashMap<Integer, String> myMapHuman = myWorkWithCollections.createCollectionMap();
        myWorkWithCollections.printHumanMap(myMapHuman);
        myWorkWithCollections.printHumanMap(myWorkWithCollections.sortMapByKey(myMapHuman));
        myWorkWithCollections.printHumanMap(myWorkWithCollections.sortByValue(myMapHuman));
    }
}