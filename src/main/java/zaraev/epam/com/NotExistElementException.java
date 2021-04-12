package zaraev.epam.com;


/**
 * Класс непроверяемых исключений не равен ли элемент null
 */
public class NotExistElementException extends RuntimeException{
    public NotExistElementException() {
        super();
    }

    public NotExistElementException(String message) {
        super(message);
    }

    public NotExistElementException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotExistElementException(Throwable cause) {
        super(cause);
    }
}
