package zaraev.epam.com;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String string = "Hello World - это моя первая программа!";
        String string2 = new String("Я крутой Java программист");
        String string3 = string2.concat(string2.concat((string2.concat(string2.concat(string2)))));
        WorkWithStrings myString = new WorkWithStrings();
        System.out.println("1. Получить длину строки : " + myString.getLength(string));
        System.out.println("2. Сравнить 2 строки без учета регистра: " + myString.getEqualsIgnoreCase(string, string2));
        System.out.println("4. Получить из строки массив символов:  " + Arrays.toString(myString.getToCharArray(string)));
        System.out.println("5. Получить из строки массив байтов:  " + Arrays.toString(myString.getBytesString(string)));
        System.out.println("6. Привести строку к верхнему регистру: " + myString.getToUpperCase(string));
        System.out.println("7. Найти первую позицию символа \"а\" в строке: " + myString.getIndexOf(string,"a"));
        System.out.println("8. Найти последнюю позицию символа \"а\" в строке: " + myString.getLastIndexOf(string,"a"));
        System.out.println("9. Проверить содержит-ли строка слово \"Sun\": " + myString.getIndexOf(string,"Sun"));
        System.out.println("10. Проверить оканчивается-ли строка на \"Oracle\": " + myString.getEndsWith(string,"Oracle"));
        System.out.println("11. Проверить начинается-ли строка на \"Java\": " + myString.getStartsWith(string,"Java"));
        System.out.println("12. Заменить все символы \"а\" в строке на символ \"о\": " + myString.getReplace(string,'a', 'o'));
        System.out.println("13. Получить подстроку с 44 символа по 90 символ: " + myString.getSubstring(string3,39, 91));
        System.out.println("14. Разбить строку по символу пробел (т.е. чтобы каждое слово было отдельным элементом массива): " + Arrays.toString(myString.getsSplit(string," ")));
        System.out.println("15. Поменять последовательность символов в строке на обратную: " + myString.getReverseString(string));
    }
}
