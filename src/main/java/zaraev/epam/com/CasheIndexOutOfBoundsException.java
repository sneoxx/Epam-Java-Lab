package zaraev.epam.com;

public class CasheIndexOutOfBoundsException extends RuntimeException{
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
