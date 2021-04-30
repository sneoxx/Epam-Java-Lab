package com.zaraev.epam;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.*;

/**
 * Сервисный класс предоставляющий методы, работающие через forEach
 */
@Slf4j
public class ForEachService {

    /**
     * Создание коллекции List с заданным количеством рандомных элементов UUID
     *
     * @param amountElements - число элементов
     * @return - заполенная коллекция List
     */
    public List<String> createRandomUUIDArrayListViaForEach(int amountElements) {
        String[] myArray = new String[amountElements];
        for (int i = 0; i < amountElements; i++) {
            UUID uuid = UUID.randomUUID();
            myArray[i] = uuid.toString();
        }
        List<String> list = Arrays.asList(myArray);
        log.debug("createRandomUUIDArrayListViaForEach() Коллекция List с числом элементов {} успешно создана", amountElements);
        return list;
    }

    /**
     * Запись коллекции List в указанный файл
     *
     * @param list     - искомая коллекция
     * @param fileName - путь к файлу
     */
    public void writeListToFileViaForEach(List<String> list, String fileName) {
        Optional<List<String>> optionalStringList = Optional.ofNullable(list);
        Optional<String> optionalFileName = Optional.ofNullable(fileName);
        try (Writer writer = new BufferedWriter(
                new FileWriter(optionalFileName.orElse("src/main/resources/1.txt")))) {
            for (Object person : optionalStringList.orElse(createRandomUUIDArrayListViaForEach(10000))) {
                String tempString = (String) person;
                writer.write(tempString + "\n");
            }
        } catch (IOException e) {
            log.error("writeListToFileViaForEach() Ошибка ввода вывода при записи файла", e);
        }
        log.debug("writeListToFileViaForEach() Коллекция List успешно записана в файл: {}", fileName);
    }

    /**
     * Считать из указанного файла в коллекцию List
     *
     * @param fileName - имя файла
     * @return - коллекция list
     */
    public List<String> readListFromFile(String fileName) {
        Optional<String> optionalFileName = Optional.ofNullable(fileName);
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(optionalFileName.orElse("src/main/resources/1.txt"))))) {
            String line;
            ArrayList<String> list = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
            return list;
        } catch (IOException e) {
            log.error("readListFromFile() Ошибка ввода вывода при чтении из файла", e);
        }
        ArrayList<String> list = new ArrayList<>();
        log.info("readListFromFile() Коллекция List успешно считана из файла: {}", fileName);
        return list;
    }

    /**
     * Считать из указанного зашифрованного файла в коллекцию List
     *
     * @param fileNameEncodedFile - имя зайшифрованного файла
     * @return - коллекция list
     */
    public List<String> readListFromFileEncodedFile(String fileNameEncodedFile) {
        Optional<String> optionalFileName = Optional.ofNullable(fileNameEncodedFile);
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(optionalFileName.orElse("src/main/resources/1.txt"))))) {
            String line;
            List<String> list = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                list.add(new String(Base64.getDecoder().decode(line)));
            }
            return list;
        } catch (IOException e) {
            log.error("readListFromFile() Ошибка ввода вывода при чтении из файла", e);
        }
        ArrayList<String> list = new ArrayList<>();
        log.info("readListFromFile() Коллекция List успешно считана из зашифрованного файла: {}", fileNameEncodedFile);
        return list;
    }

    /**
     * Создать из коллекции List экземпляры класса Sausage и поместить их в коллекцию list
     *
     * @param list - коллекция list
     * @return - коллекция list
     */
    public List<Sausage> createSausageFromList(List<String> list) {
        List<Sausage> sausages = new ArrayList<>();
        for (String s : list) {
            String[] tempSting = s.replace("type='", ":")
                    .replace("', weight=", ":")
                    .replace(", cost=", ":")
                    .split(":");
            sausages.add(new Sausage(tempSting[1], Integer.parseInt(tempSting[2]), Long.parseLong(tempSting[3])));
        }
        log.info("createSausageFromList() List<Sausage> создан: {}", sausages);
        return sausages;
    }

    /**
     * Подсчитать количество элементов в поданной коллекции List, где сумма цифр элемента больше 100
     *
     * @param list - искомая коллекция list
     * @return - число типа int
     */
    public int NumberOfElementsWithASumGreaterThan100(List<String> list) {
        int count = 0;
        for (String s : list) {
            String tempSting = s.replaceAll("[^\\d]", "");
            int sum = 0;
            for (int y = 0; y < tempSting.length(); y++) {
                sum += (int) tempSting.charAt(y) - 48;
            }
            if (sum > 100) {
                count = count + 1;
            }
        }
        log.info("NumberOfElementsWithASumGreaterThan100() Число элементов UUID с суммой чисел больше 100: {}", count);
        return count;
    }
}