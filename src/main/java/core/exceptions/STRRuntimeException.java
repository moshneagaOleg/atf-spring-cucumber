package core.exceptions;

public class STRRuntimeException extends RuntimeException {

    public STRRuntimeException(String cause) {
        super(cause);
    }

    public STRRuntimeException(String cause, Exception ex) {
        super(cause, ex);
    }
}
