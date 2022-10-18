package com.soat.anti_gaspi.domain;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.soat.anti_gaspi.domain.exception.UnableToSendEmailException;

// TODO Mettre dans port outgoing
public interface EmailSender {
    void send(EmailInformation mail) throws UnableToSendEmailException, JsonProcessingException;
}
