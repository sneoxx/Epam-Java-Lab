package com.zaraev.epam;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Класс для демонстрации решения проблемы RaceCondition
 */
@Slf4j
public class SynchronizedRaceCondition implements Runnable {

    public static AtomicInteger value = new AtomicInteger(1);

    public void run() {
        log.debug("run() Поток {} {}", Thread.currentThread().getName(), Thread.currentThread().getState());
        while (!Thread.currentThread().isInterrupted()) {
            for (int y = 0; y < 1000; y++) {
                int oldValue = value.get();
                int newValue = value.incrementAndGet();
                log.debug("run() Операция инкремента - старое значение: {} новое значение:{}", oldValue, newValue);
                if (oldValue + 1 != newValue) {
                    log.error("run() Race condition: {} + 1 = {} в потоке {}", oldValue, newValue, Thread.currentThread().getName());
                    throw new IllegalStateException("Race condition: " + oldValue + " + 1 = " + newValue);
                }
            }
        }
        log.debug("run() Поток {} прерван", Thread.currentThread().getName());
    }
}