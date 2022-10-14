package com.soat.anti_gaspi.infrastructure.exception;

public class SendgridException extends RuntimeException {
    public SendgridException(String message) {
        super(message);
    }
}
