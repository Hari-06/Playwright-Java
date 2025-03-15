package com.framework.exceptions;

public class FrameworkException extends RuntimeException {

    public FrameworkException(String message) {
        super(message);
    }

    public FrameworkException(Throwable cause) {
        super(cause);
    }

    public FrameworkException(String message, Throwable cause) {
        super(message, cause);
    }

}

