package com.soat.anti_gaspi.domain.exception;

public class UnableToSendEmailException extends Exception {
    public UnableToSendEmailException(String message) {
        super(message);
    }
}
