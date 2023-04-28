package com.example.cinema.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RequestHandler {
    private final ObjectMapper mapper = new ObjectMapper();
    public String getValue(JsonNode requestBody, String key) {
        JsonNode valueNode = requestBody.get(key);
        if (valueNode == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Missing required field: " + key);
        }
        return valueNode.asText();
    }

    public String writeAsString(Object object) throws JsonProcessingException {
        return mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(object);
    }
}
