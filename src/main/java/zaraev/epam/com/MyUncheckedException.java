package zaraev.epam.com;

public class MyUncheckedException extends RuntimeException {
    public MyUncheckedException() {
        super();
    }

    public MyUncheckedException(String message) {
        super(message);
    }

    public MyUncheckedException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyUncheckedException(Throwable cause) {
        super(cause);
    }
}
