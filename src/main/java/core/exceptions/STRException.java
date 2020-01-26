package core.exceptions;

public class STRException extends Exception {

    public STRException(String cause) {
        super(cause);
    }

    public STRException(String cause, Exception ex) {
        super(cause, ex);
    }
}
