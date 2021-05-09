package com.zaraev.epam.Task3Chat;

import lombok.extern.slf4j.Slf4j;

/**
 * Поток, считывающий модифицирующий случаным образом сообщение из чата
 */
@Slf4j
public class Updater implements Runnable {

    private final Chat chat;

    public Updater(Chat chat) {
        this.chat = chat;
    }

    /**
     * Запустить поток модификации сообщения из чата
     */
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(chat.getRandomNumber(40000, 70000));
            } catch (InterruptedException | IllegalArgumentException e) {
                log.error("run() Ошибка: " + e);
                Thread.currentThread().interrupt();
            }
            chat.modify();
        }
    }
}