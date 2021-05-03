package com.zaraev.epam;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс предоставляющий методы для класса Main
 */
@Slf4j
public class MainHelper {

    private static final RaceCondition raceCondition = new RaceCondition();

    private static final SynchronizedRaceCondition synchronizedRaceCondition = new SynchronizedRaceCondition();

    /**
     * Воспроизведение ошибки RaceCondition
     */
    public void callRaceCondition() {
        log.info("callsSynchronizedRaceCondition() Вызов проблемы RaceCondition запущен");
        try {
            Thread thread = new Thread(raceCondition);
            Thread thread1 = new Thread(raceCondition);
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
            Thread thread = new Thread(synchronizedRaceCondition);
            Thread thread1 = new Thread(synchronizedRaceCondition);
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
}