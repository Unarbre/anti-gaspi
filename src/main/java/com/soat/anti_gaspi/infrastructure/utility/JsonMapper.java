package com.soat.anti_gaspi.infrastructure.utility;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface JsonMapper {
    <T> String  toJson(T data) throws JsonProcessingException;
}
