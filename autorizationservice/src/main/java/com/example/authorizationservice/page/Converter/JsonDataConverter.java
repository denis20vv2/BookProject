package com.example.authorizationservice.page.Converter;

import com.example.authorizationservice.page.domain.Data;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class JsonDataConverter implements AttributeConverter<Data, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Data data) {
        try {
            return objectMapper.writeValueAsString(data);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error serializing JSON", e);
        }
    }

    @Override
    public Data convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, Data.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error deserializing JSON", e);
        }
    }
}

