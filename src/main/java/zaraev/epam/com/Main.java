package zaraev.epam.com;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;

@Slf4j
public class Main {

    public static void main(String[] args) {
        loadClassThroughMyClassLoaderAndPrintln();
        receiveOutOfMemoryError();
        receiveStackOverflowError();
    }

    /**
     * Создание объекта указанного класса через загрузчик классов MyClassLoader
     */
    public static void loadClassThroughMyClassLoaderAndPrintln() {
        try {
            log.debug("Старт программы");
            MyClassLoader classLoader = new MyClassLoader();
            Class clazz = classLoader.loadClass("TestClass");
            Object obj = clazz.getDeclaredConstructor().newInstance();
            System.out.println(obj);
            log.debug("Объект указанного класса успешно выведен в консоль");
        } catch (ClassNotFoundException e) {
            log.error("Класс не найден", e);
        } catch (InstantiationException e) {
            log.error("Экземпляр класса не может быть создан", e);
        } catch (IllegalAccessException e) {
            log.error("Доступ к классу запрещен", e);
        } catch (NoSuchMethodException e) {
            log.error("Метод не найден", e);
        } catch (InvocationTargetException e) {
            log.error("Исключение вызванного метода или конструктора", e);
        }
    }

    /**
     * Генерация ошибки переполнения Heap - OutOfMemoryError
     */
    public static void receiveOutOfMemoryError() {
        Long[] longs = new Long[6000 * 6000];
    }

    /**
     * Генерация ошибки переполнения стэка - StackOverflowError
     */
    public static void receiveStackOverflowError() {
        receiveStackOverflowError();
    }
}