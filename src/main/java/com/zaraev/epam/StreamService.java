package com.zaraev.epam;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Сервисный класс предоставляющий методы работающие через стримы
 */
@Slf4j
public class StreamService {

    DataApiService dataApiService = new DataApiService();

    /**
     * Создание коллекции List с заданным количеством рандомных элементов UUID
     *
     * @param amountElements - число элементов
     * @return - заполенная коллекция List
     */
    public List<String> createRandomUUIDArrayList(int amountElements) {
        List<String> list = Stream.generate((UUID::randomUUID))
                .limit(amountElements)
                .map(UUID::toString)
                .collect(Collectors.toList());
        log.debug("createRandomUUIDArrayList() Коллекция List с числом элементов {} успешно создана", amountElements);
        return list;
    }

    /**
     * Запись коллекции List в указанный файл
     *
     * @param list     - искомая коллекция
     * @param fileName - путь к файлу
     */
    public void writeArrayListToFile(List<String> list, String fileName) {
        Optional<List<String>> optionalStringList = Optional.ofNullable(list);
        Optional<String> optionalFileName = Optional.ofNullable(fileName);
        try {
            Path path = Paths.get(optionalFileName.orElse("src/main/resources/1.txt"));
            Files.write(path, optionalStringList.orElse(createRandomUUIDArrayList(10000))).toFile();
            log.debug("writeArrayListToFile() Коллекция List успешно записана в файл: {}", fileName);
        } catch (IOException e) {
            log.error("writeArrayListToFile() Ошибка ввода вывода при записи файла", e);
        }
    }

    /**
     * Считать из указанного файла в коллекцию List,
     * и подсчитать количество элементов, где сумма цифр элемента больше 100
     *
     * @param fileName - имя файла
     * @return - число типа long
     */
    public long readListFromFileAndFilterSum(String fileName) {
        Optional<String> optionalFileName = Optional.ofNullable(fileName);
        File file = new File(optionalFileName.orElse("src/main/resources/1.txt"));
        long count = 0;
        try {
            count = Files.lines(Paths.get(file.getAbsolutePath()))
                    .map(str -> str.replaceAll("[^\\d]", ""))
                    .filter(dataApiService::sumOfString)
                    .count();
        } catch (IOException e) {
            log.error("readListFromFileAndFilterSum() Ошибка ввода вывода при чтении из файла", e);
        }
        log.info("readListFromFileAndFilterSum() Число элементов UUID с суммой чисел больше 100: {}", count);
        return count;
    }
}