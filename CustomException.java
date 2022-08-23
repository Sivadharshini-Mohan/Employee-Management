import java.lang.RuntimeException;

public class CustomException extends Exception {
    CustomException () {
        super();
    }
    CustomException (String message) {
        super(message);
    }

    CustomException (String message, Throwable error) {
        super(message, error);
    }
}