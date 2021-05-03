package com.zaraev.epam;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс для демонстрации проблемы RaceCondition
 */
@Slf4j
public class RaceCondition implements Runnable {

    public static int value;

    public void run() {
        log.debug("run() Поток {} {}", Thread.currentThread().getName(), Thread.currentThread().getState());
        while (!Thread.currentThread().isInterrupted()) {
            for (int y = 0; y < 1000; y++) {
                int oldValue = value;
                int newValue = ++value;
                if (oldValue + 1 != newValue) {
                    log.error("run() Race condition: {} + 1 = {} в потоке {}", oldValue, newValue, Thread.currentThread().getName());
                    throw new IllegalStateException("Race condition: " + oldValue + " + 1 = " + newValue);
                }
            }
        }
        log.debug("run() Поток {} прерван", Thread.currentThread().getName());
    }
}