package zaraev.epam.com;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * Класс для обработки команды add - добавления текста в файл
 */
@Slf4j
public class CommandAddHandler implements CommandHandler {

    /**
     * Метод для добавления текста в файл
     * Если не указан номер строки, добавит в конец файла новую строку с текстом
     * Если указан номер строки, добавит текст на указанный номер строки в файл, строки который были на ее месте и после нее сдвинутся вниз на одну строку
     * Если в файле строк меньше, чем указанный номер строки. Добавятся новые пустые строки
     * Если не текст для добавления, команда не сработает и будет выведено сообщение о неккоретности ввода команды
     * Если файла для записи еще нет, он будет создан
     *
     * @param commandAddString - строка "команда" введенная в консоль пользователем
     */
    public void handle(String commandAddString) {
        int linePositionString = 0;
        String fileNameToWrite;
        String textToWrite;
        MetodsForCommand myMetodsForCommand = new MetodsForCommand();
        String[] commandAddArray = commandAddString.split(" "); // считываем команду в массив строк по разделителю пробел
        if (myMetodsForCommand.checkCommandWithLinePosition(commandAddArray)) {
            linePositionString = Integer.parseInt(commandAddArray[1]);
            fileNameToWrite = commandAddArray[2];
            textToWrite = commandAddString.substring(commandAddString.lastIndexOf(fileNameToWrite) + fileNameToWrite.length() + 1).replace("\"", ""); //обрезаем с конца, находим текст для записи и сразу удаляем кавычки
        } else {
            fileNameToWrite = commandAddArray[1];
            textToWrite = commandAddString.substring(commandAddString.lastIndexOf(fileNameToWrite) + fileNameToWrite.length() + 1).replace("\"", ""); //обрезаем с конца, находим текст для записи и сразу удаляем кавычки
        }
        try {
            ArrayList<String> list = myMetodsForCommand.createArrayListfromFile(fileNameToWrite);
            list = myMetodsForCommand.addTextInCommandAddArray(commandAddArray, list, linePositionString, textToWrite);
            myMetodsForCommand.writeArrayListToFile(list, fileNameToWrite);
        } catch (ArrayIndexOutOfBoundsException e) {
            log.error("Выход за пределы массива при работе с файлом", e);
        }
    }
}