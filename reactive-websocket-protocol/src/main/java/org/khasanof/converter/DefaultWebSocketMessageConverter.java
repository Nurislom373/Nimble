package org.khasanof.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.khasanof.model.ws.WsRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketMessage;

/**
 * @author Nurislom
 * @see org.khasanof.converter
 * @since 6/9/2024 6:42 AM
 */
@Component
public class DefaultWebSocketMessageConverter implements WebSocketMessageConverter {

    private final ObjectMapper objectMapper;

    public DefaultWebSocketMessageConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     *
     * @param message
     * @return
     */
    @Override
    public WsRequest convert(WebSocketMessage message) {
        String payload = message.getPayloadAsText();
        return tryConvert(payload);
    }

    private WsRequest tryConvert(String payload) {
        try {
            return objectMapper.readValue(payload, WsRequest.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
