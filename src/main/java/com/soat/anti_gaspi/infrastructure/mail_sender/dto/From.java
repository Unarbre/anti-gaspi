package com.soat.anti_gaspi.infrastructure.mail_sender.dto;

class From {
    private String email;

    public From(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
