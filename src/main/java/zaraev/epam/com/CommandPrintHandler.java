package zaraev.epam.com;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;

@Slf4j
public class CommandPrintHandler {

    private int linePositionString;
    public String fileNameToPrint;

    /**
     * Метод для вывода на печать текста из файла
     * Вывод на печать текста из файла по указанному номеру строки
     * Если номер строки не указан веведет на печать весь файл
     * Если не указан файл или текст для добавления, команда не сработает и будет выведено сообщение о неккоретности ввода команды
     *
     * @param commandAddString - строка "команда" введенная в консоль пользователем
     */
    public void print(String commandAddString) {
        MetodsForCommand myMetodsForCommand = new MetodsForCommand();
        if (!myMetodsForCommand.checkFileName(commandAddString)) {
            return;
        } else {
            fileNameToPrint = myMetodsForCommand.findFileName(commandAddString);
        }
        File dir = new File(fileNameToPrint);
        if (!dir.exists()) {
            log.debug("Файла <> нет - Нечего печатать", fileNameToPrint);
            System.out.println("Такого файла нет - Нечего печатать");
            return;
        }
        ArrayList<String> list = null;
        String[] commandAddArray = new String[0];
        try {
            list = myMetodsForCommand.createArrayListfromFile(fileNameToPrint);
            commandAddArray = commandAddString.split(" ");
        } catch (ArrayIndexOutOfBoundsException e) {
            log.error("Выход за пределы массива при чтении из файла", e);
        }
        switch (commandAddArray.length) {
            case 3:
                int linePositionString = myMetodsForCommand.findLinePositionString(commandAddString);
                if (list.size() < linePositionString) {
                    log.info("Cтроки <> в файле нет - Нечего печатать", linePositionString);
                    System.out.println("Такой строки в файле нет - Нечего печатать");
                    return;
                }
                if (list.get(linePositionString) != null) {
                    System.out.println("Строка равна: " + list.get(linePositionString - 1));
                    log.debug("Печатаем файл <> по номеру строки <>", fileNameToPrint, linePositionString);
                    break;
                }
            default:
                log.debug("Печатаем весь файл");
                assert list != null;
                System.out.println(list.toString());
                break;
        }
    }
}