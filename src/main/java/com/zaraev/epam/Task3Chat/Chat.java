package com.zaraev.epam.Task3Chat;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Класс, реализующий поведение чата, емкостью 25 смс
 */
@Slf4j
public class Chat {
    private final List<Sms> listSms;
    private int smsId = 0;

    private final ReentrantLock locker;
    private final Condition condition;

    public Chat() {
        locker = new ReentrantLock();
        condition = locker.newCondition();
        listSms = new ArrayList<>();
    }

    /**
     * Считывание и последующее удаление сообщения из чата
     */
    public void get() {
        locker.lock();
        try {
            while (listSms.size() < 1) {
                log.info("get() нет элементов для считывания");
                condition.await();
            }
            log.info("get() Сообщение № {} с текстом \"{}\" считано и удалено из чата", listSms.get(0).getId(), listSms.get(0).getSmsText());
            listSms.remove(0);
            log.debug("get() Емкость чата {}", listSms.size());
            condition.signalAll();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            locker.unlock();
        }
    }

    /**
     * Запись сообщения в чат
     */
    public void put() {
        locker.lock();
        try {
            while (listSms.size() >= 25) {
                log.info("put() Емкость чата полностью заполнена");
                condition.await();
            }
            smsId++;
            listSms.add(new Sms(smsId, "Текст" + smsId));
            int chatCapacity = listSms.size();
            log.info("put() Сообщение № {} с текстом \"{}\" добавлено в чат", listSms.get(chatCapacity - 1).getId(), listSms.get(chatCapacity - 1).getSmsText());
            log.debug("put() Емкость чата {}", chatCapacity);
            condition.signalAll();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            locker.unlock();
        }
    }

    /**
     * Модификация сообщения в чата
     */
    public void modify() {
        locker.lock();
        try {
            while (listSms.size() < 1) {
                log.info("modify() Нет элементов для модификации");
                condition.await();
            }
            int chatCapacity = listSms.size();
            Sms modify = listSms.get(chatCapacity - 1);
            modify.setSmsText(generateRandomWord());
            listSms.set(chatCapacity - 1, modify);
            log.info("modify() Сообщение № {} модифицировано на текст \"{}\" ", listSms.get(chatCapacity - 1).getId(), listSms.get(chatCapacity - 1).getSmsText());
            log.debug("modify() Емкость чата {}", chatCapacity);
            condition.signalAll();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            locker.unlock();
        }
    }

    /**
     * Генерация случайного слова
     *
     * @return - случайное слово
     */
    public static String generateRandomWord() {
        Random random = new Random();
        char[] word = new char[random.nextInt(8) + 3];
        for (int j = 0; j < word.length; j++) {
            word[j] = (char) ('a' + random.nextInt(26));
        }
        return new String(word);
    }

    /**
     * Генерация случайного числа в заданном диапазоне
     *
     * @param min - минимальное число диапазона
     * @param max - максимальное число диапазона
     * @return - случайное число
     */
    public int getRandomNumber(int min, int max) {
        return min + (int) (Math.random() * max);
    }
}