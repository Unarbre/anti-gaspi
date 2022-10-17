package com.soat.anti_gaspi.infrastructure.mail_sender.dto;

import java.util.List;

public class SendgridDto {
    private List<Personalization> personalizations;
    private From from;
    private String subject;
    private List<Content> content;

    public SendgridDto(List<Personalization> personalizations, From from, String subject, List<Content> content) {
        this.personalizations = personalizations;
        this.from = from;
        this.subject = subject;
        this.content = content;
    }

    public List<Personalization> getPersonalizations() {
        return personalizations;
    }

    public void setPersonalizations(List<Personalization> personalizations) {
        this.personalizations = personalizations;
    }

    public From getFrom() {
        return from;
    }

    public void setFrom(From from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }
}
