package zaraev.epam.com;

/**
 * Класс проверяемых исключений на выход за пределы кеша
 */
public class CasheIndexOutOfBoundsException extends Exception{
    public CasheIndexOutOfBoundsException() {
        super();
    }

    public CasheIndexOutOfBoundsException(String message) {
        super(message);
    }

    public CasheIndexOutOfBoundsException(String message, Throwable cause) {
        super(message, cause);
    }

    public CasheIndexOutOfBoundsException(Throwable cause) {
        super(cause);
    }
}
