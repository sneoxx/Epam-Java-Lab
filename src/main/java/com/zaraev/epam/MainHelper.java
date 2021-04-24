package com.zaraev.epam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainHelper {
    private static RaceCondition raceCondition = new RaceCondition();
    private static SynchronizedRaceCondition synchronizedRaceCondition = new SynchronizedRaceCondition();

    public void callRaceCondition()  {
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

    public void callsSynchronizedRaceCondition() {
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

    public synchronized void deadlock() {
        try {
            for (int i = 0; i < 10; i++) {
                Thread thread = new Thread(this::deadlock);
                thread.start();
                log.info("deadlock() Состояние потока {} : {}", thread.getName(), thread.getState());
                thread.join();
                thread.interrupt();
                log.info("deadlock() Состояние потока {} : {}", thread.getName(), thread.getState());
            }
        } catch (Exception ex) {

        }
    }




}

