package zaraev.epam.com;

import java.util.Arrays;

public class WorkWithStrings {
    /**
     * Получить длину строки
     * @param string
     * @return int
     */
    public int getLength(String string){
        return string.length();
    }

    /**
     * Сравнить 2 строки без учета регистра
     * @param string
     * @param string2
     * @return boolean
     */
    public boolean getEqualsIgnoreCase(String string, String string2){
        return string.equalsIgnoreCase(string2);
    }

    /**
     * Получить из строки массив символов
     * @param string
     * @return char[]
     */
    public char[] getToCharArray(String string){
        return string.toCharArray();
    }

    /**
     * Получить из строки массив байтов
     * @param string
     * @return byte[]
     */
    public byte[] getBytesString(String string){
        return string.getBytes();
    }

    /**
     * Привести строку к верхнему регистру
     * @param string
     * @return String
     */
    public String getToUpperCase(String string){
        return string.toUpperCase();
    }

    /**
     * Найти первую позицию символа
     * @param string
     * @param string2
     * @return int
     */
    public int getIndexOf(String string, String string2){
        return string.indexOf(string2);
    }

    /**
     * Найти последнюю позицию символа
     * @param string
     * @param string2
     * @return int
     */
    public int getLastIndexOf(String string, String string2){
        return string.lastIndexOf(string2);
    }

    /**
     * Проверить оканчивается-ли строка на слово
     * @param string
     * @param string2
     * @return boolean
     */
    public boolean getEndsWith(String string, String string2){
        return string.endsWith(string2);
    }

    /**
     * Проверить начинается-ли строка на слово
     * @param string
     * @param string2
     * @return boolean
     */
    public boolean getStartsWith(String string, String string2){
        return string.startsWith(string2);
    }

    /**
     * Заменить все символы в строке
     * @param string
     * @param char1
     * @param char2
     * @return String
     */
    public String getReplace(String string, char char1, char char2){
        return string.replace(char1, char2);
    }

    /**
     * Получить подстроку с index1 символа по index2 символ:
     * @param string
     * @param index1
     * @param index2
     * @return String
     */
    public String getSubstring(String string, int index1, int index2){
        return string.substring(index1, index2);
    }

    /**
     * азбить строку по символу
     * @param string
     * @param regex
     * @return String[]
     */
    public String[] getsSplit(String string, String regex){
        return string.split(regex);
    }

    /**
     * Поменять последовательность символов в строке на обратную
     * @param string
     * @return String
     */
    public String getReverseString(String string) {
        StringBuilder builder = new StringBuilder(string);
        builder.reverse();
        return builder.toString();
    }
}
