package com.zaraev.epam;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс для демонстрации проблемы Deadlock и ее решения
 */
@Slf4j
public class Deadlock {

    private final String name;

    static final Deadlock deadlock1 = new Deadlock("Kolya");

    static final Deadlock deadlock2 = new Deadlock("Masha");

    public Deadlock(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Первый синхронизированный метод вызывающий из себя второй синхронизированный метод
     * @param deadlock - объект класса Deadlock
     */
    public synchronized void getLock(Deadlock deadlock) {
        log.debug("getLock() Поток ожидает разблокировки объекта с именем: " + deadlock.getName());
        deadlock.getUnLock(deadlock);
    }

    /**
     * Второй синхронизированный метод
     * @param deadlock - объект класса Deadlock
     */
    public synchronized void getUnLock(Deadlock deadlock) {
        log.info("getUnLock() Объект с именем {} успешно обработан ", deadlock.getName());
    }

    /**
     * Поток1 вызывающий первый метод от объекта1: deadlock1 с входным параметром объект2: deadlock2,
     * что приводит к необходимости того, чтобы оба объекта были свободны(свободен монитор)
     * для успешного завершения обработки
     */
    public static class Thread1 extends Thread {
        public void run() {
            log.debug("run() {} Удерживает блокировку 1 объекта", Thread.currentThread().getName());
            deadlock1.getLock(deadlock2);
                try {
                    Thread.sleep(1000);
                }
               catch (InterruptedException e) {
                   log.error("run() Поток {} вызвал ошибку: ",Thread.currentThread().getName(), e);

               }
            log.info("run() Поток {} успешно завершен",Thread.currentThread().getName());
        }
    }

    /**
     * Поток2 вызывающий первый метод от объекта2: deadlock2 с входным параметром объект1: deadlock1,
     * что приводит к необходимости того, чтобы оба объекта были свободны(свободен монитор)
     * для успешного завершения обработки
     */
    public static class Thread2 extends  Thread {
        public void run() {
           log.debug("run(){} Удерживает блокировку 2 объекта", Thread.currentThread().getName());
            deadlock2.getLock(deadlock1);
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                log.error("run() Поток {} вызвал ошибку: ",Thread.currentThread().getName(), e);
            }
            log.info("run() Поток {} успешно завершен",Thread.currentThread().getName());
            }
        }
    }