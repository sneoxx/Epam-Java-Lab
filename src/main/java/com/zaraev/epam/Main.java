package com.zaraev.epam;

import com.zaraev.epam.annotation.Value;
import com.zaraev.epam.pojo.BankServiceImpl;
import com.zaraev.epam.pojo.BankServiceImpl2;
import com.zaraev.epam.pojo.BankServiceImpl3;
import com.zaraev.epam.pojo.BankServiceImpl4;
import com.zaraev.epam.pojo.ClientAccount;
import com.zaraev.epam.pojo.ClientAccountVTBBank;
import com.zaraev.epam.pojo.Human;
import com.zaraev.epam.pojo.Phone;
import com.zaraev.epam.pojo.Product;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

  public static int sum(int... numbers) {

    int result = 0;
    for (int i : numbers) {
      result += i;
    }
    return result;
  }

  public static class Util {

    public static long getSum(List clientAccounts) {
      long sum = 0;
      for (Object o : clientAccounts) {
        if (o instanceof ClientAccount) {
          sum += ((ClientAccount) o).getAmount();
        }
      }
      return sum;
    }

    public static <T> T getValue(Object obj) {
      return (T) obj;
    }

//    public static boolean find(List<Product> all, Product p) {
//      //какой-то код
//      return true;
//    }

    public static boolean find(List<? extends Product> all, Product p) {
      //какой-то код
      return true;
    }

//    public static <T extends Product> boolean find(List<T> all, T p) {
//      //какой-то код
//      return true;
//    }

//    public static <T> void copy(List<Product> src, List<Product> dest) {
//      for (Product p : src) {
//        dest.add(p);
//      }
//    }

    public static <T> void copy(List<? extends T> src, List<? super T> dest) {
      for (T p : src) {
        dest.add(p);
      }
    }

    public static void someMethod(List<? extends Phone> list) {
      Product class4 = list.get(0);
      for (Phone phone : list) {
        System.out.println(phone);
      }

    }
  }

  private final static Logger log = LoggerFactory.getLogger(Main.class);

  @Value
  public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
    log.debug("Старт программы");

    // Пример дженериков
    List<String> strings = new ArrayList<>();
    List<Integer> numbers = new ArrayList<>();
    System.out.println(strings.getClass() == numbers.getClass());
    List<String> strings2 = new ArrayList<String>();
    System.out.println(Main.sum(1, 3, 8));

    // Сырые типы
    List<String> list2 = new ArrayList();
    List list3 = new ArrayList();
    List list4 = new ArrayList<>();

    //Пример1 - эра до дженериков
    ClientAccount clientAccount1 = new ClientAccount();
    clientAccount1.setAmount(10);
    ClientAccount clientAccount2 = new ClientAccount();
    clientAccount2.setAmount(20);

    List clientAccountsList = new ArrayList<>();
    clientAccountsList.add(clientAccount1);
    clientAccountsList.add(clientAccount2);

    List<Integer> integerList = Arrays.asList(10, 20);

    BankServiceImpl bankService = new BankServiceImpl();
    System.out.println(bankService.getSum(clientAccountsList));
    System.out.println(bankService.getSum(integerList));

    //Пример2 - Используем дженерики - параметризируем сервис ClientAccount
    List<ClientAccount> clientAccountsList2 = new ArrayList<>();
    ClientAccount clientAccount3 = new ClientAccount();
    clientAccount3.setAmount(11);
    ClientAccount clientAccount4 = new ClientAccount();
    clientAccount4.setAmount(21);
    clientAccountsList2.add(clientAccount3);
    clientAccountsList2.add(clientAccount4);
    //создаем и добавляем ClientAccount в clientAccountsList2
    BankServiceImpl2<ClientAccount> bankService2 = new BankServiceImpl2<>();
    // BankServiceImpl2<Integer> bankService22 = new BankServiceImpl2<>(); // получим ошибку во время компиляции
    System.out.println(bankService2.getSum(clientAccountsList2));
    // System.out.println(bankService2.getSum(integerList)); // получим ошибку во время компиляции

    //Пример3 - в сервис ClientAccount добавляем дополнительную логику из интерфейса
    ClientAccountVTBBank clientAccount5 = new ClientAccountVTBBank();
    clientAccount5.setAmount(20);
    ClientAccountVTBBank clientAccount6 = new ClientAccountVTBBank();
    clientAccount6.setAmount(25);

    List<ClientAccountVTBBank> clientAccountsList3 = new ArrayList<>();
    clientAccountsList3.add(clientAccount5);
    clientAccountsList3.add(clientAccount6);

    List<ClientAccount> clientAccountsList4 = new ArrayList<>();

    BankServiceImpl3<ClientAccountVTBBank> bankService3 = new BankServiceImpl3<>();
    System.out.println(bankService3.getSum(clientAccountsList3));

    //Пример4 - в сервис ClientAccount добавляем метод копирования
    //не путаем - можем добавить в сам сервис параметризированный метод копирования - как и сделано через implements
    //а можем ограничить тип используемого дженерика имплементирующим какой-то интерфейс, тогда в логике методов сервиса
    // сможем использовать метод пришедший из типа дженерика(из самой сущности)
    BankServiceImpl4<ClientAccountVTBBank> bankService4 = new BankServiceImpl4<>();
    System.out.println(bankService3.getSum(clientAccountsList3));
    bankService4.copy(clientAccountsList3, clientAccountsList4);
    System.out.println(clientAccountsList4);

    ArrayList<String> clientAccountsList46 = new ArrayList<>();
    clientAccountsList46.clone();

    HashMap<String, String> testMap = new HashMap<>();
    testMap.clone();

//      List<Camera> cameras = new ArrayList<>();
//    Phone phone = new Phone();
//   // Product product = new Product();
//    //Util.find(cameras, product);
//  //  Util.find(cameras, phone);
//
//    Camera camera1 = new Camera();
//    Camera camera2 = new Camera();
//    camera1.compareTo(camera2);
//    //camera1.compareTo(phone);
//
//    List<Phone> phones = new ArrayList<>();
//    List<Product> products = new ArrayList<>();
//    Util.copy(phones,products);
//
//    List list = new ArrayList();
//    list.add("String");
//    String s = (String) list.get(0);

//    Object[] xx = new String[3];
//    xx[0] = 222;

//    String[] strings66 = new String[] {"a", "b", "c"};
//    Object[] arr = strings66;
//
//    arr[0] = 42;

//    List<String> list = Arrays.asList("Hello", "World");
//    List<Integer> data = new ArrayList(list); //будет ошибка каста
//  //  List<Integer> data = new ArrayList<>(list); // не скомпилируется
//    Integer intNumber = data.get(0);
//    System.out.println(data);
    ReflectionService reflectionService = new ReflectionService();
    reflectionService.myHobbyAnnotationDemonstration();

//    List list6 = Arrays.asList(2, 3);
//    for (Object element : list6) {
//      System.out.println(Util.<Integer>getValue(element) + 1);
//    }
//    reflectionService.checkEntityAnnotation(human);
//        reflectionService.checkValueFieldsAnnotation(human);
//        reflectionService.checkValueMethodsAnnotation(human);
//        log.debug("Класс bird аннотации @Entity есть, аннотаций Value нет");
//        reflectionService.checkAndFillFieldsWithValueAnnotation(bird, 1);
//        log.debug("Класс animal аннотации @Entity нет, аннотаций Value есть");
//        reflectionService.checkAndFillFieldsWithValueAnnotation(animal, 1);
//        log.debug("Проверяем установку значения int");
//        reflectionService.checkAndFillFieldsWithValueAnnotation(human, 777);
//        log.debug("Проверяем установку значения String");
//        reflectionService.checkAndFillFieldsWithValueAnnotation(human, "Kolya");
//        log.debug("Проверяем установку значения Float");
//        reflectionService.checkAndFillFieldsWithValueAnnotation(human, 100.0f);
//        log.debug("Проверяем установку значения Boolean");
//        reflectionService.checkAndFillFieldsWithValueAnnotation(human, true);
//        log.debug("Проверяем установку значения null");
//        reflectionService.checkAndFillFieldsWithValueAnnotation(human, null);


    class TestClass<T> {

      private T value1;
      private T value2;

      public void printValues() {
        System.out.println(value1);
        System.out.println(value2);
      }

      public <T> TestClass<T> createAndAdd2Values(Object o1, Object o2) {
        TestClass<T> result = new TestClass<>();
        result.value1 = (T) o1;
        result.value2 = (T) o2;
        return result;
      }


    }


  }
}