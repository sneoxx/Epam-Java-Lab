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
     *
     * @param commandAddArray - массив с элементами команды в каждой ячейке
     * @return - false или true
     */
    public boolean checkCommandWithLinePosition(String[] commandAddArray) {
        try {
            Integer linePositionString = Integer.parseInt(commandAddArray[1]);//проверяем можно ли первый элемент массива привести к int, введен ли номер строки в команду?
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Запись текста на нужную позицию в аррейлист
     * @param commandAddArray - строка "команда" введенная в консоль пользователем
     * @param list - аррейлист со строками считанными из файла
     * @param linePositionString - позиция строки для записи
     * @param textToWrite - текст для записи
     * @return - вернет аррейлист с записанной на нужную позицию строкой
     */
    public ArrayList<String> addTextInCommandAddArray(String[] commandAddArray, ArrayList<String> list, int linePositionString, String textToWrite) {
        if (checkCommandWithLinePosition(commandAddArray)) {
            if (list.size() > linePositionString) { //добавление в середину, если длина массива больше строки для записи
                list.add(linePositionString - 1, textToWrite);
                return list;
            } else {
                for (int i = list.size() - 1; i <= linePositionString - 1; i++) { //заполяем его на нужное количество пустыми элементами
                    list.add("\n" );
                }
                list.set(linePositionString - 1, textToWrite); //вставляем нашу строку в конец текущего ArrayList
                log.debug("Добавляем текст {} в ArrayList по номеру строки {}", textToWrite, linePositionString);
                return list;
            }
        } else {
            list.add(textToWrite);  // добавить строку в конец файла
            log.debug("Добавляем текст {} в конец ArrayList", textToWrite);
            return list;
        }
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
     * @param fileNameToWrite - файл из которого будет произведено чтение в ArrayList
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
        ArrayList<String> list = new ArrayList<>();
        return list;
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