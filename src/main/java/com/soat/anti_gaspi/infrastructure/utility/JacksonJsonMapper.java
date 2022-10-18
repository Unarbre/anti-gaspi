package com.soat.anti_gaspi.infrastructure.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonJsonMapper implements JsonMapper {

    private final ObjectMapper mapper;

    public JacksonJsonMapper() {
        mapper = new ObjectMapper();
    }

    @Override
    public <T> String toJson(T data) throws JsonProcessingException {
        return mapper.writeValueAsString(data);
    }
}
