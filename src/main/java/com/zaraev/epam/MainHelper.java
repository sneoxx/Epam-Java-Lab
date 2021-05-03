package com.zaraev.epam;

import com.zaraev.epam.Task3Chat.Chat;
import com.zaraev.epam.Task3Chat.Reader;
import com.zaraev.epam.Task3Chat.Updater;
import com.zaraev.epam.Task3Chat.Writer;
import lombok.extern.slf4j.Slf4j;

/**
 * Класс предоставляющий методы для класса Main
 */
@Slf4j
public class MainHelper {

    private static final RaceCondition RACE_CONDITION = new RaceCondition();

    private static final SynchronizedRaceCondition SYNCHRONIZED_RACE_CONDITION = new SynchronizedRaceCondition();

    private static final int NUMBER_OF_WRITER = 3;

    private static final int NUMBER_OF_READER = 2;

    private static final int NUMBER_OF_UPDATER = 1;


    /*
     * Воспроизведение ошибки RaceCondition
     */
    public void callRaceCondition() {
        log.info("callsSynchronizedRaceCondition() Вызов проблемы RaceCondition запущен");
        try {
            Thread thread = new Thread(RACE_CONDITION);
            Thread thread1 = new Thread(RACE_CONDITION);
            thread.start();
            thread1.start();
            Thread.sleep(1000);
            thread.interrupt();
            thread1.interrupt();
            Thread.sleep(1000);
            log.info("callRaceCondition() Состояние потока {} : {}", thread.getName(), thread.getState());
            log.info("callRaceCondition() Состояние потока {} : {}", thread1.getName(), thread1.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Решение ошибки RaceCondition
     */
    public void callsSynchronizedRaceCondition() {
        log.info("callsSynchronizedRaceCondition() Решение проблемы RaceCondition запущено");
        try {
            Thread thread = new Thread(SYNCHRONIZED_RACE_CONDITION);
            Thread thread1 = new Thread(SYNCHRONIZED_RACE_CONDITION);
            thread.start();
            thread1.start();
            Thread.sleep(1000);
            thread.interrupt();
            thread1.interrupt();
            Thread.sleep(1000);
            log.info("callsSynchronizedRaceCondition() Состояние потока {} : {}", thread.getName(), thread.getState());
            log.info("callsSynchronizedRaceCondition() Состояние потока {} : {}", thread1.getName(), thread1.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Воспроизведение ошибки Deadlock
     */
    public void deadlock() {
        log.info("deadlock() Взаимная блокировка запущена");
        Deadlock.Thread1 thread1 = new Deadlock.Thread1();
        Deadlock.Thread2 thread2 = new Deadlock.Thread2();
        thread1.start();
        thread2.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            log.error("run() Поток {} вызвал ошибку: ", Thread.currentThread().getName(), e);

        }
        log.info("deadlock() Состояние потока {} : {}", thread1.getName(), thread1.getState());
        log.info("deadlock() Состояние потока {} : {}", thread2.getName(), thread2.getState());
    }

    /**
     * Воспроизведение ошибки Deadlock
     */
    public void breakingTheDeadlock() {
        log.info("breakingTheDeadlock() Решение проблемы Deadlock запущено ");
        Deadlock.Thread1 thread1 = new Deadlock.Thread1();
        Deadlock.Thread2 thread2 = new Deadlock.Thread2();
        thread1.start();
        try {
            thread1.join(100);
            thread2.start();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            log.error("run() Поток {} вызвал ошибку: ", Thread.currentThread().getName(), e);

        }
        log.info("breakingTheDeadlock() Состояние потока {} : {}", thread1.getName(), thread1.getState());
        log.info("breakingTheDeadlock() Состояние потока {} : {}", thread2.getName(), thread2.getState());
    }

    /**
     * Запуск чата
     */
    public void generateChat() {
        log.info("Задание3 - Чат запущен:");
        log.info("Число writer потоков: {}", NUMBER_OF_WRITER);
        log.info("Число reader потоков: {}", NUMBER_OF_READER);
        log.info("Число update потоков: {}", NUMBER_OF_UPDATER);
        int numberOfThreads = NUMBER_OF_WRITER + NUMBER_OF_READER + NUMBER_OF_UPDATER;
        Chat chat = new Chat();
        Writer writer = new Writer(chat);
        Reader reader = new Reader(chat);
        Updater updater = new Updater(chat);

        if (numberOfThreads > 10000) {
            log.error("generateChat() Введено слишком большое число потоков: {}", numberOfThreads);
            throw new IllegalArgumentException("Введено слишком большое число потоков, введите другое число");
        }

        for (int i = 0; i < NUMBER_OF_WRITER; i++) {
            Thread writerThread = new Thread(writer);
            writerThread.start();
            log.debug("generateChat() Поток writer: {} создан", writerThread.getName());
        }

        for (int i = 0; i < NUMBER_OF_READER; i++) {
            Thread readerThread = new Thread(reader);
            readerThread.start();
            log.debug("generateChat() Поток reader: {} создан", readerThread.getName());
        }

        for (int i = 0; i < NUMBER_OF_UPDATER; i++) {
            Thread updaterThread = new Thread(updater);
            updaterThread.start();
            log.debug("generateChat() Поток updater: {} создан", updaterThread.getName());
        }
    }
}