package com.zaraev.epam.exceptions;

/**
 * Класс непроверяемых исключений на выход за пределы кеша
 */
public class CasheIndexOutOfBoundsException extends RuntimeException {
    public CasheIndexOutOfBoundsException(String message) {
        super(message);
    }
}