package zaraev.epam.com;

import java.util.Arrays;

public class Main {


    public static void main(String[] args) {
        String str = "Hello World - это моя первая программа!";
        String str2 = new String("Я крутой Java программист");
        String str3 = str2.concat(str2.concat((str2.concat(str2.concat(str2)))));
        StringLesson1 myStr = new StringLesson1();

        System.out.println("1. Получить длину строки : " + myStr.getLength(str));
        System.out.println("2. Сравнить 2 строки без учета регистра: " + myStr.getEqualsIgnoreCase(str, str2));
        System.out.println("4. Получить из строки массив символов:  " + Arrays.toString(myStr.getToCharArray(str)));
        System.out.println("5. Получить из строки массив байтов:  " + Arrays.toString(myStr.getBytesString(str)));
        System.out.println("6. Привести строку к верхнему регистру: " + myStr.getToUpperCase(str));
        System.out.println("7. Найти первую позицию символа \"а\" в строке: " + myStr.getIndexOf(str,"a"));
        System.out.println("8. Найти последнюю позицию символа \"а\" в строке: " + myStr.getLastIndexOf(str,"a"));
        System.out.println("9. Проверить содержит-ли строка слово \"Sun\": " + myStr.getIndexOf(str,"Sun"));
        System.out.println("10. Проверить оканчивается-ли строка на \"Oracle\": " + myStr.getEndsWith(str,"Oracle"));
        System.out.println("11. Проверить начинается-ли строка на \"Java\": " + myStr.getStartsWith(str,"Java"));
        System.out.println("12. Заменить все символы \"а\" в строке на символ \"о\": " + myStr.getReplace(str,'a', 'o'));
        System.out.println("13. Получить подстроку с 44 символа по 90 символ: " + myStr.getSubstring(str3,39, 91));
        System.out.println("14. Разбить строку по символу пробел (т.е. чтобы каждое слово было отдельным элементом массива): " + Arrays.toString(myStr.getsSplit(str," ")));
        System.out.println("15. Поменять последовательность символов в строке на обратную: " + myStr.getReverseString(str));

    }

}
