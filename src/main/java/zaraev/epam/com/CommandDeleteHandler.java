package zaraev.epam.com;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;

/**
 * Класс для обработки команды delete - удаления текста из файла
 */
@Slf4j
public class CommandDeleteHandler {

    /**
     * Метод для удаления текста из файла
     * Удалит текст из файлу по указанному номеру строки
     * Если номер строки не указан удалит текст из последней строки
     * Если не указан файл или текст для добавления, команда не сработает и будет выведено сообщение о неккоретности ввода команды
     *
     * @param commandAddString - строка "команда" введенная в консоль пользователем
     * @throws Exception
     */
    public void delete(String commandAddString) throws Exception {
        int linePositionString = 0;
        String fileNameToDelete;
        MetodsForCommand myMetodsForCommand = new MetodsForCommand();

        String[] commandAddArray = commandAddString.split(" "); // считываем команду в массив строк по разделителю пробел
        if (myMetodsForCommand.checkCommandWithLinePosition(commandAddArray)) {
            linePositionString = Integer.parseInt(commandAddArray[1]);
            fileNameToDelete = commandAddArray[2];
        } else {
            fileNameToDelete = commandAddArray[1];
        }
        myMetodsForCommand.checkFileName(fileNameToDelete);
        File dir = new File(fileNameToDelete);
        if (!dir.exists()) {
            System.out.println("Файла нет - Нечего удалять" + fileNameToDelete);
        }
        ArrayList<String> list = null;
        try {
            list = myMetodsForCommand.createArrayListfromFile(fileNameToDelete);
        } catch (ArrayIndexOutOfBoundsException e) {
            log.error("Выход за пределы массива", e);
        }
        if (list.size() < linePositionString) {
            System.out.println("Cтроки <> в файле нет - Нечего удалять" + linePositionString);
            return;
        }
        if (myMetodsForCommand.checkCommandWithLinePosition(commandAddArray)) {
            if (list.get(linePositionString) != null) {
                list.add(linePositionString - 1, " ");
                list.remove(linePositionString);
                log.info("Удаляем из ArrayList строку по номеру", linePositionString);
            }
        } else {
            if (list.get(linePositionString) != null) {
                list.remove(list.size() - 1);
                log.info("Удаляем из ArrayList последнюю строку");
            }
        }
        try {
            myMetodsForCommand.writeArrayListToFile(list, fileNameToDelete);
        } catch (ArrayIndexOutOfBoundsException e) {
            log.error("Выход за пределы массива при записи файла", e);
        }
    }
}