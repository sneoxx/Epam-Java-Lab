package zaraev.epam.com;

import java.util.Arrays;

public class WorkWithStrings {
    /**
     * Определение длины строки
     * @param string - исходная строка
     * @return int - длина строки
     */
    public int getLength(String string){
        return string.length();
    }

    /**
     * Сравнение 2 строк без учета регистра
     * @param string - первая строка
     * @param string2  - вторая строка
     * @return boolean - возвращает true или false, равны или нет
     */
    public boolean getEqualsIgnoreCase(String string, String string2){
        return string.equalsIgnoreCase(string2);
    }

    /**
     * Получение из строки массива символов
     * @param string - исходная строка
     * @return char[] - возвращает массив символов
     */
    public char[] getToCharArray(String string){
        return string.toCharArray();
    }

    /**
     * Получение из строки массива байтов
     * @param string - исходная строка
     * @return byte[] - возвращает массив байтов
     */
    public byte[] getBytesString(String string){
        return string.getBytes();
    }

    /**
     * Приведение строки к верхнему регистру
     * @param string - исходная строка
     * @return String - возвращает строку в верхнем регистре
     */
    public String getToUpperCase(String string){
        return string.toUpperCase();
    }

    /**
     * Нахождение первой позицию символа в строке
     * @param string - строка в которой будем искать
     * @param string2 - строка, которую будем искать
     * @return int - первая позиция символа в строке, если его нет, вернет -1
     */
    public int getIndexOf(String string, String string2){
        return string.indexOf(string2);
    }

    /**
     * Нахождение последней позиции символа в строке
     * @param string - строка в которой будем искать
     * @param string2 - строка, которую будем искать
     * @return int  - песледняя позиция символа в строке, если его нет, вернет -1
     */
    public int getLastIndexOf(String string, String string2){
        return string.lastIndexOf(string2);
    }

    /**
     * Проверка оканчивается-ли строка на слово
     * @param string - строка в которой будем искать
     * @param string2 - строка, которую будем искать
     * @return boolean - возвращает true или false, оканчивается строка на слово или нет
     */
    public boolean getEndsWith(String string, String string2){
        return string.endsWith(string2);
    }

    /**
     * Проверка начинается-ли строка на слово
     * @param string - строка в которой будем искать
     * @param string2 - строка, которую будем искать
     * @return boolean - возвращает true или false, начинается строка на слово или нет
     */
    public boolean getStartsWith(String string, String string2){
        return string.startsWith(string2);
    }

    /**
     * Замена всех символов в строке на указанный символ
     * @param string - исходная строка
     * @param char1 - символ который будем менять
     * @param char2 - символ на который будем менять
     * @return String - строка с замененными символами
     */
    public String getReplace(String string, char char1, char char2){
        return string.replace(char1, char2);
    }

    /**
     * Получение из строки, подстроки с index1 символа по index2 символ
     * @param string - исходная строка
     * @param index1 - индекс первого символа подстроки
     * @param index2 - индекс последнего символа подстроки
     * @return String  - итоговая подстрока с index1 символа по index2 символ
     */
    public String getSubstring(String string, int index1, int index2){
        return string.substring(index1, index2);
    }

    /**
     * Разбитие строки на массив строк по символу
     * @param string - исходная строка
     * @param regex - символ по которому разбить строку
     * @return String[] - массив строк разбитый по символу
     */
    public String[] getsSplit(String string, String regex){
        return string.split(regex);
    }

    /**
     * Поменять последовательность символов строки на обратную
     * @param string - исходная строка
     * @return String - строка с обратной последовательностью символов
     */
    public String getReverseString(String string) {
        StringBuilder builder = new StringBuilder(string);
        builder.reverse();
        return builder.toString();
    }
}
