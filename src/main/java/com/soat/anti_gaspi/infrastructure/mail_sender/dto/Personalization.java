package com.soat.anti_gaspi.infrastructure.mail_sender.dto;

import java.util.List;

class Personalization {
    private List<To> to;

    public Personalization(List<To> to) {
        this.to = to;
    }

    public List<To> getTo() {
        return to;
    }

    public void setTo(List<To> to) {
        this.to = to;
    }
}
