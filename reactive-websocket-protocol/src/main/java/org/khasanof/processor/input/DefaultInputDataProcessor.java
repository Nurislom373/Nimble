package org.khasanof.processor.input;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.context.method.ReactiveWebSocketMethodContext;
import org.khasanof.converter.WebSocketMessageConverter;
import org.khasanof.executor.ReactiveWebSocketMethodExecutor;
import org.khasanof.model.method.WsMethod;
import org.khasanof.model.ws.WsRequest;
import org.khasanof.model.ws.WsRequestSession;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * @author Nurislom
 * @see org.khasanof.processor.input
 * @since 6/9/2024 6:35 AM
 */
@Slf4j
@Component
public class DefaultInputDataProcessor implements InputDataProcessor {

    private final WebSocketMessageConverter webSocketMessageConverter;
    private final ReactiveWebSocketMethodContext reactiveWebSocketMethodContext;
    private final ReactiveWebSocketMethodExecutor reactiveWebSocketMethodExecutor;

    public DefaultInputDataProcessor(WebSocketMessageConverter webSocketMessageConverter,
                                     ReactiveWebSocketMethodContext reactiveWebSocketMethodContext,
                                     ReactiveWebSocketMethodExecutor reactiveWebSocketMethodExecutor) {

        this.webSocketMessageConverter = webSocketMessageConverter;
        this.reactiveWebSocketMethodContext = reactiveWebSocketMethodContext;
        this.reactiveWebSocketMethodExecutor = reactiveWebSocketMethodExecutor;
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
                    log.debug("Successfully converted! : {}", request);

                    if (reactiveWebSocketMethodContext.existMethod(request.getMethod())) {
                        Optional<WsMethod> wsProtocolMethod = reactiveWebSocketMethodContext.getMethod(request.getMethod());
                        WsMethod protocolMethod = wsProtocolMethod.orElseThrow();

                        return reactiveWebSocketMethodExecutor.execute(protocolMethod, Mono.just(wsRequestToSession(request, session)))
                                .thenReturn(message);
                    }
                    return Mono.just(message);
                });
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
