package zaraev.epam.com;

/**
 * Класс непроверяемых исключений. Не добавляй null
 */
public class NotAddNull extends RuntimeException{
    public NotAddNull() {
        super();
    }

    public NotAddNull(String message) {
        super(message);
    }

    public NotAddNull(String message, Throwable cause) {
        super(message, cause);
    }

    public NotAddNull(Throwable cause) {
        super(cause);
    }
}
