package zaraev.epam.com;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;

/**
 * Класс-сервис с методами для работы остальных классов
 */
@Slf4j
public class MetodsForCommand {

    /**
     * Метод определения введен ли номер строки в команду
     * @param commandAddArray - массив с элементами команды в каждой ячейке
     * @return - false или true
     */
    public boolean checkCommandWithLinePosition (String[] commandAddArray) {
       // String[] commandAddArray = commandAddArray = commandAddString.split(" "); // считываем команду в массив строк по разделителю пробел
        boolean commandWithLine = true;
        try {
            Integer linePositionString = Integer.parseInt(commandAddArray[1]);//проверяем можно ли первый элемент массива привести к int, введен ли номер строки в команду?
        } catch (NumberFormatException e) {
            return false;
         }
        return true;
        }

    /**
     * Проверка - корректно ли введено в команде имя файла
     *
     * @param commandAddString - строка "команда" введенная в консоль пользователем
     * @return - false или true
     */
    public boolean checkFileName(String commandAddString) {
        String fileNamePattern = ".txt";
        if (commandAddString.indexOf(fileNamePattern) == -1) {
            log.debug("Имя файла в команду не введено: " + commandAddString);
            System.out.println("Вы не ввели имя файла");
            return false;
        } else if (!(commandAddString.indexOf(fileNamePattern) != -1) && commandAddString.indexOf(fileNamePattern) == commandAddString.lastIndexOf(fileNamePattern) && (commandAddString.charAt(commandAddString.indexOf(fileNamePattern) - 1) != ' ')) {
            log.debug("Имя файла в команду не введено: " + commandAddString);
            System.out.println("Вы не ввели имя файла");
            return false;
        }
        return true;
    }

    /**
     * Проверка - корректно введено в команде текст для добавления
     *
     * @param commandAddString - строка "команда" введенная в консоль пользователем
     * @return - false или true
     */
    public boolean checkText(String commandAddString) {
        if (!(commandAddString.indexOf('"') != -1 && commandAddString.lastIndexOf('"') != -1) && commandAddString.lastIndexOf('"') - commandAddString.indexOf('"') < 1) {
            log.debug("Текст в команду не введен: " + commandAddString);
            System.out.println("Вы не ввели текст");
            return false;
        }
        return true;
    }

    /**
     * Проверка - корректно введен в команде номер строки
     *
     * @param s - строка
     * @return - false или true
     * @throws NumberFormatException
     */
    private boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            log.debug("Номер строки: " + s);
            System.out.println("Номер строки введен неккоректно");
            return false;
        }
    }

    /**
     * Определяем из команды строку для записи, печати или удаления
     *
     * @param commandAddString - строка "команда" введенная в консоль пользователем
     * @return - номер строки типа int
     */
    public int findLinePositionString(String commandAddString) {
        String[] commandAddArray = commandAddString.split(" ");
        if (commandAddArray.length > 2) {
            if (isDigit(commandAddArray[1])) {
                int linePositionString = Integer.parseInt(commandAddArray[1]);
                log.debug("Номер строки: " + linePositionString);
                System.out.println("Номер строки: " + linePositionString);
                return linePositionString;
            } else {
                log.debug("Введен неверный номер строки: " + commandAddString);
                System.out.println("Вы ввели неверное значение строки");
                return 0;
            }
        }
        System.out.println("Вы не ввели строку");
        return 0;
    }

    /**
     * Определяем из команды текст для записи в файл
     *
     * @param commandAddString - строка "команда" введенная в консоль пользователем
     * @return - текст для записи типа String
     */
    public String findTextToWrite(String commandAddString) {
        int firstIndexText = commandAddString.indexOf('"');
        int lasttIndexText = commandAddString.lastIndexOf('"');
        String textToWrite = commandAddString.substring(firstIndexText + 1, lasttIndexText);
        log.debug("Текст: " + textToWrite);
        System.out.println("Текст: " + textToWrite);
        return textToWrite;
    }

    /**
     * Определяем из команды имя файла для записи
     *
     * @param commandAddString - строка "команда" введенная в консоль пользователем
     * @return - имя файла типа String
     */
    public String findFileName(String commandAddString) {
        String fileNamePattern = ".txt";
        int firstIndexFileName = commandAddString.indexOf(fileNamePattern);
        int indexFileName = commandAddString.lastIndexOf(' ', firstIndexFileName);
        String fileName = commandAddString.substring(indexFileName + 1, indexFileName + 6);
        log.debug("Имя файла: " + fileName);
        System.out.println("Имя файла: " + fileName);
        return fileName;
    }

    /**
     * Создает файл для записи, в случае его отсутствия
     *
     * @param fileNameToWrite - имя файла для записи
     */
    public void creatFile(String fileNameToWrite) throws IOException {
        File dir = new File(fileNameToWrite);
        if (!dir.exists()) {
            dir.createNewFile();
        }
    }

    /**
     * Читает из указанного файла в ArrayList
     *
     * @param fileNameToWrite - файл из которого будет произведено чтение в rrayList
     * @return - заполненный из файла ArrayList
     */
    public ArrayList<String> createArrayListfromFile(String fileNameToWrite) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(fileNameToWrite)))) {
            String line;
            ArrayList<String> list = new ArrayList<>();
            while ((line = reader.readLine()) != null) {   // читаем из файлика построчно пока не дойдем до null строки(до конца)
                list.add(line);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Записывает из указанного ArrayList в указанный файл
     *
     * @param list            - ArrayList из которого будем читать
     * @param fileNameToWrite - имя файла на диске в который будем записывать
     */
    public void writeArrayListToFile(ArrayList<String> list, String fileNameToWrite) {
        try (Writer writer = new BufferedWriter(
                new FileWriter(fileNameToWrite)
        )) {
            for (Object person : list) {
                String tempString = (String) person;
                writer.write(tempString + "\n");
            }
        } catch (IOException e) {
            log.error("Ошибка ввода вывода при записи файла", e);
            System.out.println(e.getMessage());
        }
    }
}