package zaraev.epam.com;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;

@Slf4j
public class CommandDeleteHandler {
    private int linePositionString;
    public String fileNameToDelete;

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
        MetodsForCommand myMetodsForCommand = new MetodsForCommand();
        if (!myMetodsForCommand.checkFileName(commandAddString)) {
            return;
        } else {
            fileNameToDelete = myMetodsForCommand.findFileName(commandAddString);
        }
        linePositionString = myMetodsForCommand.findLinePositionString(commandAddString);
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
        String[] commandAddArray = commandAddString.split(" ");
        switch (commandAddArray.length) {
            case 2:
                if (list.get(linePositionString) != null) {
                    list.remove(list.size() - 1);
                    log.info("Удаляем из ArrayList последнюю строку");
                }
                break;
            case 3:
                if (list.get(linePositionString) != null) {
                    list.add(linePositionString - 1, " ");
                    list.remove(linePositionString);
                    log.info("Удаляем из ArrayList строку по номеру", linePositionString);
                }
                for (Object person : list) {   // выводим для проверки в консоль
                    System.out.println(list);
                }
                break;
            default:
                log.debug("В команду введено слишком много аргументов", commandAddString);
                System.out.println("В команду введено слишком много аргументов, введите корректную команду");
                break;
        }
        try {
            myMetodsForCommand.writeArrayListToFile(list, fileNameToDelete);
        } catch (ArrayIndexOutOfBoundsException e) {
            log.error("Выход за пределы массива при записи файла", e);
        }
    }
}