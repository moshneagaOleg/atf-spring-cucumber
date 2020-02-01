package core.exceptions;

public class VTFException extends RuntimeException {

    public VTFException(String message) {
        super(message);
    }

    public VTFException(String message, Throwable cause) {
        super(message, cause);
    }

    public VTFException(Throwable cause) {
        super(cause);
    }
}
