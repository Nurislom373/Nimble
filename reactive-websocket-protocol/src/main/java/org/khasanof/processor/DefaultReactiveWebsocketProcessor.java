package org.khasanof.processor;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.context.session.ReactiveWebsocketSessionContext;
import org.khasanof.model.WebSocketSessionFacade;
import org.khasanof.processor.input.InputDataProcessor;
import org.khasanof.processor.session.ReactiveSessionDataSender;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Nurislom
 * @see org.khasanof.processor
 * @since 6/8/2024 10:57 PM
 */
@Slf4j
@Component
public class DefaultReactiveWebsocketProcessor implements ReactiveWebsocketProcessor {

    private final InputDataProcessor inputDataProcessor;
    private final ReactiveWebsocketSessionContext reactiveWebsocketSessionContext;

    public DefaultReactiveWebsocketProcessor(InputDataProcessor inputDataProcessor,
                                             ReactiveWebsocketSessionContext reactiveWebsocketSessionContext) {

        this.inputDataProcessor = inputDataProcessor;
        this.reactiveWebsocketSessionContext = reactiveWebsocketSessionContext;
    }

    /**
     * @param facade
     * @return
     */
    @Override
    public Flux<WebSocketMessage> process(WebSocketSessionFacade facade) {
        WebSocketSession webSocketSession = facade.getWebSocketSession();
        ReactiveSessionDataSender reactiveSessionDataSender = facade.getReactiveSessionDataSender();

        return inputDataProcessor.input(
                        webSocketSession.receive()
                                .doOnNext(webSocketMessage -> log.info("receive new a message : {}", webSocketMessage.getPayloadAsText()))
                ).switchMap(message -> reactiveSessionDataSender.output())
                .doFinally(signalType -> reactiveWebsocketSessionContext.removeSession(facade.getSessionId()));
    }

    private Mono<Void> simpleMethod() {
        return Mono.empty();
    }
}
