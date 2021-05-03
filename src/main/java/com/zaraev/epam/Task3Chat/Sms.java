package com.zaraev.epam.Task3Chat;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Класс sms, описывающий объект сообщения чата
 */
@Data
@AllArgsConstructor
public class Sms {

    private final int id;

    private String smsText;

}