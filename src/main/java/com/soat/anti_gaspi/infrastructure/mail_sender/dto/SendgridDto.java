package com.soat.anti_gaspi.infrastructure.mail_sender.dto;

import java.util.List;

public record SendgridDto(List<Personalization> personalizations, From from, String subject, List<Content> content) {
}
