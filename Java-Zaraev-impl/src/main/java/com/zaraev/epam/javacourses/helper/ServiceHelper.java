package com.zaraev.epam.javacourses.helper;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Класс с общими методами для классов Service
 */
@Component
public class ServiceHelper {

    /**
     * Генерация случайного слова
     *
     * @return - случайное слово
     */
    public String generateRandomWord() {
        Random random = new Random();
        char[] word = new char[random.nextInt(2) + 3];
        for (int j = 0; j < word.length; j++) {
            word[j] = (char) ('a' + random.nextInt(26));
        }
        return new String(word);
    }

    /**
     * Генерация случайного числа в заданном диапазоне
     *
     * @return - случайное число
     */
    public String getRandomNumber() {
        return Integer.toString(1 + (int) (Math.random() * 10000));
    }
}