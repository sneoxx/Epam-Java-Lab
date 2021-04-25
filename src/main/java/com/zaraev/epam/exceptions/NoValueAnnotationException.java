package com.zaraev.epam.exceptions;

/**
 * Unchecked exception выбрасываюся, когда есть анннотация Entity, но нет аннотации Value хотя бы на одном поле
 */
public class NoValueAnnotationException extends RuntimeException{

    public NoValueAnnotationException(String message) {
        super(message);
    }
}