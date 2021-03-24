package zaraev.epam.com;

import java.util.Arrays;

public class WorkWithStrings {
    /**
     * Получить длину строки
     * @param string - принимает строку на вход
     * @return int возвращает число int
     */
    public int getLength(String string){
        return string.length();
    }

    /**
     * Сравнить 2 строки без учета регистра
     * @param string принимает строку на вход
     * @param string2 принимает строку на вход
     * @return boolean возвращает true или false
     */
    public boolean getEqualsIgnoreCase(String string, String string2){
        return string.equalsIgnoreCase(string2);
    }

    /**
     * Получить из строки массив символов
     * @param string принимает строку на вход
     * @return char[] мозвращает массив символов
     */
    public char[] getToCharArray(String string){
        return string.toCharArray();
    }

    /**
     * Получить из строки массив байтов
     * @param string принимает строку на вход
     * @return byte[] возвращает массив байтов
     */
    public byte[] getBytesString(String string){
        return string.getBytes();
    }

    /**
     * Привести строку к верхнему регистру
     * @param string принимает строку на вход
     * @return String возвращает строку
     */
    public String getToUpperCase(String string){
        return string.toUpperCase();
    }

    /**
     * Найти первую позицию символа
     * @param string принимает строку в которой будем искать
     * @param string2 принимает строку, которую будем искать
     * @return int возвращает позицию символа
     */
    public int getIndexOf(String string, String string2){
        return string.indexOf(string2);
    }

    /**
     * Найти последнюю позицию символа
     * @param string принимает строку в которой будем искать
     * @param string2 принимает строку, которую будем искать
     * @return int возвращает позицию символа
     */
    public int getLastIndexOf(String string, String string2){
        return string.lastIndexOf(string2);
    }

    /**
     * Проверить оканчивается-ли строка на слово
     * @param string принимает строку в которой будем искать
     * @param string2 принимает строку, которую будем искать
     * @return boolean возвращает true или false
     */
    public boolean getEndsWith(String string, String string2){
        return string.endsWith(string2);
    }

    /**
     * Проверить начинается-ли строка на слово
     * @param string принимает строку в которой будем искать
     * @param string2 принимает строку, которую будем искать
     * @return boolean возвращает true или false
     */
    public boolean getStartsWith(String string, String string2){
        return string.startsWith(string2);
    }

    /**
     * Заменить все символы в строке
     * @param string принимает строку на вход
     * @param char1 принимает символ который будем менять
     * @param char2 принимает символ на который будем менять
     * @return String возвращает строку
     */
    public String getReplace(String string, char char1, char char2){
        return string.replace(char1, char2);
    }

    /**
     * Получить подстроку с index1 символа по index2 символ:
     * @param string принимает строку на вход
     * @param index1 принимает индекс первого символа
     * @param index2 принимает индекс второго символа
     * @return String возвращает строку
     */
    public String getSubstring(String string, int index1, int index2){
        return string.substring(index1, index2);
    }

    /**
     * разбить строку по символу
     * @param string принимает строку на вход
     * @param regex по какому символу разбить
     * @return String[] возвращает массив строк
     */
    public String[] getsSplit(String string, String regex){
        return string.split(regex);
    }

    /**
     * Поменять последовательность символов в строке на обратную
     * @param string принимает строку на вход
     * @return String возвращает строку
     */
    public String getReverseString(String string) {
        StringBuilder builder = new StringBuilder(string);
        builder.reverse();
        return builder.toString();
    }
}
