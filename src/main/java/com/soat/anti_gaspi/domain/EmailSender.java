package com.soat.anti_gaspi.domain;


import com.soat.anti_gaspi.domain.exception.EnableToSendEmailException;

import javax.mail.MessagingException;

// TODO Mettre dans port outgoing
public interface EmailSender {
    void send(EmailInformation mail) throws EnableToSendEmailException;
}
