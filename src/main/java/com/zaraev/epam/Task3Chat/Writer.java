package com.zaraev.epam.Task3Chat;

import lombok.extern.slf4j.Slf4j;

/**
 * Поток, записывающий сообщения сообщение из чата
 */
@Slf4j
public class Writer implements Runnable {

    private final Chat chat;

    public Writer(Chat chat) {
        this.chat = chat;
    }

    /**
     * Запустить поток записи собщения в чат
     */
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            chat.put();
            try {
                Thread.sleep(chat.getRandomNumber(20000, 60000));
            } catch (InterruptedException | IllegalArgumentException e) {
                log.error("run() Ошибка: " + e);
                Thread.currentThread().interrupt();
            }
        }
    }
}