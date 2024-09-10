package org.khasanof.processor.output.strategy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.khasanof.context.session.ReactiveWebsocketSessionContext;
import org.khasanof.model.WebSocketSessionFacade;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketMessage;

import java.nio.charset.StandardCharsets;

/**
 * @author Nurislom
 * @see org.khasanof.processor.output.strategy
 * @since 6/9/2024 4:49 AM
 */
@Component
public class DefaultSendOutputDataStrategy implements SendOutputDataStrategy {

    private final ObjectMapper objectMapper;
    private final DataBufferFactory dataBufferFactory;
    private final ReactiveWebsocketSessionContext reactiveWebsocketSessionContext;

    public DefaultSendOutputDataStrategy(ObjectMapper objectMapper,
                                         DataBufferFactory dataBufferFactory,
                                         ReactiveWebsocketSessionContext reactiveWebsocketSessionContext) {

        this.objectMapper = objectMapper;
        this.dataBufferFactory = dataBufferFactory;
        this.reactiveWebsocketSessionContext = reactiveWebsocketSessionContext;
    }

    /**
     *
     * @param message
     */
    @Override
    public void send(Object message) {
        WebSocketMessage webSocketMessage = toWebSocketMessage(message);
        reactiveWebsocketSessionContext.getSessions()
                .stream()
                .peek(webSocketSessionFacade -> System.out.println("webSocketSessionFacade = " + webSocketSessionFacade))
                .filter(WebSocketSessionFacade::isSubscribe)
                .map(WebSocketSessionFacade::getOutputDataFlow)
                .forEach(outputDataFlow -> outputDataFlow.emitNext(webSocketMessage));
    }

    /**
     *
     * @param sessionId
     * @param message
     */
    @Override
    public void send(String sessionId, Object message) {
        reactiveWebsocketSessionContext.getSession(sessionId)
                .filter(WebSocketSessionFacade::isSubscribe)
                .map(WebSocketSessionFacade::getOutputDataFlow)
                .ifPresent(outputDataFlow -> {
                    WebSocketMessage webSocketMessage = toWebSocketMessage(message);
                    outputDataFlow.emitNext(webSocketMessage);
                });
    }

    private WebSocketMessage toWebSocketMessage(Object message) {
        String textMessage = tryWriteValueAsString(message);
        byte[] bytes = textMessage.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = this.dataBufferFactory.wrap(bytes);
        return new WebSocketMessage(WebSocketMessage.Type.TEXT, buffer);
    }

    private String tryWriteValueAsString(Object message) {
        try {
            return objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
