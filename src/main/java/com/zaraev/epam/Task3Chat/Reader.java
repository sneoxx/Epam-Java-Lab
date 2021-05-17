package com.zaraev.epam.Task3Chat;

import lombok.extern.slf4j.Slf4j;

/**
 * Поток, считывающий сообщения и удаляющий сообщение из чата
 */
@Slf4j
public class Reader implements Runnable {

    private final Chat chat;

    public Reader(Chat chat) {
        this.chat = chat;
    }

    /**
     * Запустить поток считывания сообщения из чата
     */
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(chat.getRandomNumber(30000, 50000));
            } catch (InterruptedException | IllegalArgumentException e) {
                log.error("run() Ошибка: " + e);
                Thread.currentThread().interrupt();
            }
            chat.get();
        }
    }
}