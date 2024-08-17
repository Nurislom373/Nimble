package org.khasanof.processor;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.context.session.ReactiveWebsocketSessionContext;
import org.khasanof.flow.output.OutputDataFlow;
import org.khasanof.model.WebSocketSessionFacade;
import org.khasanof.processor.input.InputDataProcessor;
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
     *
     * @param facade
     * @param session
     * @return
     */
    @Override
    public Flux<WebSocketMessage> process(WebSocketSessionFacade facade, WebSocketSession session) {
        WebSocketSession webSocketSession = facade.getWebSocketSession();
        OutputDataFlow outputDataFlow = facade.getOutputDataFlow();
        return inputDataProcessor.input(
                        webSocketSession.receive()
                                .doOnNext(webSocketMessage -> log.info("receive new a message : {}", webSocketMessage.getPayloadAsText()))
                                .doFinally(signalType -> reactiveWebsocketSessionContext.removeSession(facade.getWsSessionId())), session)
                .switchMap(message -> outputDataFlow.flowAsFlux())
                .onErrorResume(throwable -> Mono.empty());
    }
}
