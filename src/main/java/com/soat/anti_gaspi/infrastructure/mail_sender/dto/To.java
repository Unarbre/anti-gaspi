package com.soat.anti_gaspi.infrastructure.mail_sender.dto;

class To {
    private String email;

    public To(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
