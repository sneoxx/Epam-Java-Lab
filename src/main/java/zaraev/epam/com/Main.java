package zaraev.epam.com;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) {
        try {
            log.debug("Старт программы");
            MyClassLoader classLoader = new MyClassLoader();
            Class clazz = classLoader.loadClass
                    ("C:/myClasses/TestClass");
            Object obj = clazz.newInstance();
            System.out.println(obj);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        receiveOutOfMemoryError();
        receiveStackOverflowError();
    }

    public static void receiveOutOfMemoryError() {
        Long[] longs = new Long[6000 * 6000];
    }

    public static void receiveStackOverflowError() {
        receiveStackOverflowError();
    }
}