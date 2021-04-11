package zaraev.epam.com;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;

/**
 * Класс для обработки команды print - печати текста из файла
 */
@Slf4j
public class CommandPrintHandler implements CommandHandler{

    /**
     * Метод для вывода на печать текста из файла
     * Вывод на печать текста из файла по указанному номеру строки
     * Если номер строки не указан веведет на печать весь файл
     * Если не указан файл или текст для добавления, команда не сработает и будет выведено сообщение о неккоретности ввода команды
     *
     * @param commandAddString - строка "команда" введенная в консоль пользователем
     */
    public void handle(String commandAddString) {
        String fileNameToPrint;
        int linePositionString = 0;
        MetodsForCommand myMetodsForCommand = new MetodsForCommand();
        String[] commandAddArray = commandAddString.split(" "); // считываем команду в массив строк по разделителю пробел
        if (myMetodsForCommand.checkCommandWithLinePosition(commandAddArray)) {
            linePositionString = Integer.parseInt(commandAddArray[1]);
            fileNameToPrint = commandAddArray[2];
        } else {
            fileNameToPrint = commandAddArray[1];
        }
        File dir = new File(fileNameToPrint);
        if (!dir.exists()) {
            log.debug("Файла {} нет - Нечего печатать", fileNameToPrint);
            System.out.println("Такого файла нет - Нечего печатать");
            return;
        }
        try {
            ArrayList<String> list = null;
            list = myMetodsForCommand.createArrayListfromFile(fileNameToPrint);
            if (myMetodsForCommand.checkCommandWithLinePosition(commandAddArray)) {
                if (list.size() < linePositionString) {
                    log.info("Cтроки {} в файле нет - Нечего печатать", linePositionString);
                    System.out.println("Такой строки в файле нет - Нечего печатать");
                }
                if (list.get(linePositionString) != null) {
                    System.out.println("Строка равна: " + list.get(linePositionString - 1));
                    log.debug("Печатаем файл {} по номеру строки {}", fileNameToPrint, linePositionString);
                    //            break;
                }
            } else {
                log.debug("Печатаем весь файл");
                assert list != null;
                System.out.println(list.toString());
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            log.error("Выход за пределы массива при чтении из файла", e);
        } catch (NullPointerException e) {
            log.error("Поптыка добавить null", e);
        }
    }
}