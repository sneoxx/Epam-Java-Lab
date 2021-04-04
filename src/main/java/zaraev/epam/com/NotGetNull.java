package zaraev.epam.com;

/**
 * Класс проверяемых исключений. Не получай null
 */
public class NotGetNull extends  Exception{
    public NotGetNull() {
        super();
    }

    public NotGetNull(String message) {
        super(message);
    }

    public NotGetNull(String message, Throwable cause) {
        super(message, cause);
    }

    public NotGetNull(Throwable cause) {
        super(cause);
    }
}
