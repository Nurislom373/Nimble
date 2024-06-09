package org.khasanof.processor.input;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.context.method.ReactiveWebSocketMethodContext;
import org.khasanof.converter.WebSocketMessageConverter;
import org.khasanof.executor.ReactiveWebSocketMethodExecutor;
import org.khasanof.model.ws.WsRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketMessage;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Flux<WebSocketMessage> input(Flux<WebSocketMessage> messages) {
        return messages
                .doOnNext(message -> {
                    WsRequest request = webSocketMessageConverter.convert(message);
                    log.info("Successfully converted! : {}", request);
                    if (reactiveWebSocketMethodContext.existMethod(request.getMethod())) {
                        reactiveWebSocketMethodContext.getMethod(request.getMethod())
                                .ifPresent(wsProtocolMethod -> {
                                    log.info("WsProtocolMethod found : {}", wsProtocolMethod);
                                    reactiveWebSocketMethodExecutor.execute(wsProtocolMethod, request);
                                });
                    }
                });
    }

    private Mono<Void> simpleMethod() {
        return Mono.empty();
    }
}
