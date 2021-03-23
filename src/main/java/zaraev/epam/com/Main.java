package zaraev.epam.com;

import java.util.Arrays;

public class Main {
    static String str = "Hello World - это моя первая программа!";
    static String str2 = new String("Я крутой Java программист"); // 3. Создать новую строку с помощью конструктора и занести ее в пул литералов

    public static void main(String[] args) {


        System.out.println("1. Получить длину строки : " + str.length());
        System.out.println("2. Сравнить 2 строки без учета регистра: " + str.equalsIgnoreCase(str2));
        System.out.println("4. Получить из строки массив символов:  " + Arrays.toString(str.toCharArray()));
        System.out.println("5. Получить из строки массив байтов:  " + Arrays.toString(str.getBytes()));
        System.out.println("6. Привести строку к верхнему регистру: " + str.toUpperCase());
        System.out.println("7. Найти первую позицию символа \"а\" в строке: " + str.indexOf('a'));
        System.out.println("8. Найти последнюю позицию символа \"а\" в строке: " + str.lastIndexOf('a'));
        System.out.println("9. Проверить содержит-ли строка слово \"Sun\": " + str.indexOf("Sun"));
        System.out.println("10. Проверить оканчивается-ли строка на \"Oracle\": " + str.endsWith("Oracle"));
        System.out.println("11. Проверить начинается-ли строка на \"Java\": " + str.startsWith("Java"));
        System.out.println("12. Заменить все символы \"а\" в строке на символ \"о\": " + str.replace('a', 'o'));
        System.out.println("13. Получить подстроку с 44 символа по 90 символ: " + str2.concat(str2.concat((str2.concat(str2.concat(str2))))).substring(39, 91));
        System.out.println("14. Разбить строку по символу пробел (т.е. чтобы каждое слово было отдельным элементом массива): " + Arrays.toString(str.split(" ")));
        System.out.println("15. Поменять последовательность символов в строке на обратную: " + reverseString(str));
    }

    public static String reverseString(String string) {
        //напишите тут ваш код
        StringBuilder builder = new StringBuilder(string);
        builder.reverse();
        return builder.toString();
    }
}
