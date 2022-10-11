package com.soat.anti_gaspi.domain;



// TODO Mettre dans port outgoing
public interface EmailSender {
    void send(EmailInformation mail);
}
