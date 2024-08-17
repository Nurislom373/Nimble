package org.khasanof.processor.input;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.khasanof.ReactiveWebsocketMessageTemplate;
import org.khasanof.context.method.ReactiveWebSocketMethodContext;
import org.khasanof.context.session.ReactiveWebsocketSessionContext;
import org.khasanof.converter.WebSocketMessageConverter;
import org.khasanof.executor.ReactiveWebSocketMethodExecutor;
import org.khasanof.model.RxWsMessage;
import org.khasanof.model.method.WsMethod;
import org.khasanof.model.ws.WsRequest;
import org.khasanof.model.ws.WsRequestSession;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static org.khasanof.constants.GlobalConstants.NOT_SUB_MESSAGE;

/**
 * @author Nurislom
 * @see org.khasanof.processor.input
 * @since 6/9/2024 6:35 AM
 */
@Slf4j
@Component
public class DefaultInputDataProcessor implements InputDataProcessor {

    private final ObjectMapper objectMapper;
    private final WebSocketMessageConverter webSocketMessageConverter;
    private final ReactiveWebSocketMethodContext reactiveWebSocketMethodContext;
    private final ReactiveWebsocketSessionContext reactiveWebsocketSessionContext;
    private final ReactiveWebSocketMethodExecutor reactiveWebSocketMethodExecutor;
    private final ReactiveWebsocketMessageTemplate reactiveWebsocketMessageTemplate;

    public DefaultInputDataProcessor(ObjectMapper objectMapper,
                                     WebSocketMessageConverter webSocketMessageConverter,
                                     ReactiveWebSocketMethodContext reactiveWebSocketMethodContext,
                                     ReactiveWebsocketSessionContext reactiveWebsocketSessionContext,
                                     ReactiveWebSocketMethodExecutor reactiveWebSocketMethodExecutor,
                                     ReactiveWebsocketMessageTemplate reactiveWebsocketMessageTemplate) {

        this.objectMapper = objectMapper;
        this.webSocketMessageConverter = webSocketMessageConverter;
        this.reactiveWebSocketMethodContext = reactiveWebSocketMethodContext;
        this.reactiveWebsocketSessionContext = reactiveWebsocketSessionContext;
        this.reactiveWebSocketMethodExecutor = reactiveWebSocketMethodExecutor;
        this.reactiveWebsocketMessageTemplate = reactiveWebsocketMessageTemplate;
    }

    /**
     * @param messages
     * @return
     */
    @Override
    public Flux<WebSocketMessage> input(Flux<WebSocketMessage> messages, WebSocketSession session) {
        return messages
                .flatMap(message -> {
                    WsRequest request = webSocketMessageConverter.convert(message);
                    log.info("Successfully converted! : {}", request);

                    if (reactiveWebSocketMethodContext.existMethod(request.getMethod())) {
                        log.info("Is Exist Method : {}", request.getMethod());

                        Optional<WsMethod> wsProtocolMethod = reactiveWebSocketMethodContext.getMethod(request.getMethod());
                        WsMethod protocolMethod = wsProtocolMethod.orElseThrow();

                        if (!protocolMethod.isDefaultMethod()) {
                            if (!reactiveWebsocketSessionContext.isSubscribed(session.getId())) {
                                reactiveWebsocketMessageTemplate.sendMessage(session.getId(), createNotSubMessage());
                                return Mono.just(message);
                            }
                        }

                        return reactiveWebSocketMethodExecutor.execute(protocolMethod, Mono.just(wsRequestToSession(request, session)))
                                .thenReturn(message);
                    }
                    log.info("Isn't Exist Method : {}", request.getMethod());
                    return Mono.just(message);
                });
    }

    /**
     *
     * @return
     */
    private String createNotSubMessage() {
        try {
            return objectMapper.writeValueAsString(new RxWsMessage(NOT_SUB_MESSAGE));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param wsRequest
     * @param session
     * @return
     */
    private WsRequestSession wsRequestToSession(WsRequest wsRequest, WebSocketSession session) {
        return new WsRequestSession(wsRequest.getId(), wsRequest.getMethod(), wsRequest.getData(), session);
    }
}
