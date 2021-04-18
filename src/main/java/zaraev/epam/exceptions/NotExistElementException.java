package zaraev.epam.exceptions;

/**
 * Класс непроверяемых исключений - не равен ли элемент null
 */
public class NotExistElementException extends RuntimeException{

    public NotExistElementException(String message) {
        super(message);
    }
}