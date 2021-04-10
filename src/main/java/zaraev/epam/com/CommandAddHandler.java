package zaraev.epam.com;

import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

@Slf4j
public class CommandAddHandler {

    /**
     * Класс для обработки команды add - добавления текста в файл
     *
     */
    private int linePositionString;
    public String textToWrite;
    public String fileNameToWrite;

    /**
     * Метод для добавления текста в файл
     * Если не указан номер строки, добавит в конец файла новую строку с текстом
     * Если указан номер строки, добавит текст на указанный номер строки в файл, строки который были на ее месте и после нее сдвинутся вниз на одну строку
     * Если в файле строк меньше, чем указанный номер строки. Добавятся новые пустые строки
     * Если не текст для добавления, команда не сработает и будет выведено сообщение о неккоретности ввода команды
     * Если файла для записи еще нет, он будет создан
     * @param commandAddString - строка "команда" введенная в консоль пользователем
     */
    public void add(String commandAddString) {
        MetodsForCommand myMetodsForCommand = new MetodsForCommand();
        if (!myMetodsForCommand.checkFileName(commandAddString)) {
            return;
        } else {
            fileNameToWrite = myMetodsForCommand.findFileName(commandAddString);
        }
        if (!myMetodsForCommand.checkText(commandAddString)) {
            return;
        } else {
            textToWrite = myMetodsForCommand.findTextToWrite(commandAddString);
        }
        linePositionString = myMetodsForCommand.findLinePositionString(commandAddString);
        try {
            myMetodsForCommand.creatFile(fileNameToWrite);
        } catch (FileNotFoundException e) {
            log.error("Файл не найден <>", fileNameToWrite, e);
        } catch (IOException e) {
            log.error("Ошибка ввода вывода", e);
            e.printStackTrace();
        }
        ArrayList<String> list = null;
        String[] commandAddArray = new String[0];
        try {
            list = myMetodsForCommand.createArrayListfromFile(fileNameToWrite);
            commandAddArray = commandAddString.split(" ");
        } catch (ArrayIndexOutOfBoundsException e) {
            log.error("Выход за пределы массива", e);
        }
        switch (commandAddArray.length) {
            case 3:
                list.add(textToWrite);  // добавить строку в конец файла
                try {
                    System.out.println(list.toString());// выводим для проверки в консоль
                } catch (NullPointerException e) {
                    log.error("Вывод на печать null", e);
                }
                log.debug("Добавляем текст <> в конец ArrayList", textToWrite);
                break;
            case 4:
                try {
                    if (list.size() > linePositionString) { //добавление в середину, если длина массива больше строки для записи
                        list.add(linePositionString - 1, textToWrite);
                    } else {
                        ArrayList<String> tempList = new ArrayList<>(); //создаем временный ArrayList
                        for (int i = 0; i <= linePositionString - list.size() - 1; i++) { //заполяем его на нужное количество
                            tempList.add(" ");
                        }
                        list.addAll(tempList); //добавляем временный ArrayList в текущий ArrayList
                        list.set(linePositionString - 1, textToWrite); //вставляем нашу строку в конец текущего ArrayList
                        log.debug("Добавляем текст <> в ArrayList по номеру строки <> ", textToWrite,linePositionString);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    log.error("Выход за пределы массива", e);
                } catch (NullPointerException e ){
                    log.error("Добавление null", e);
                }
                try {
                    System.out.println(list.toString());
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                break;
            default:
                log.debug("В команду введено слишком много аргументов", commandAddString);
                System.out.println("В команду введено слишком много аргументов, введите корректную команду");
                break;
        }
        try {
            myMetodsForCommand.writeArrayListToFile(list, fileNameToWrite);
        } catch (ArrayIndexOutOfBoundsException e) {
            log.error("Выход за пределы массива при записи файла", e);
        }
    }
}