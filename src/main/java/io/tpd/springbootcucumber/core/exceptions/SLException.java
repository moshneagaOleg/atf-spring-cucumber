package io.tpd.springbootcucumber.core.exceptions;

public class SLException extends RuntimeException {

    public SLException(String message) {
        super(message);
    }

    public SLException(String message, Throwable cause) {
        super(message, cause);
    }

    public SLException(Throwable cause) {
        super(cause);
    }
}
