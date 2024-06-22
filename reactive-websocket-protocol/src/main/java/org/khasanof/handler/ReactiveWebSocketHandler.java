package org.khasanof.handler;

import org.khasanof.context.session.ReactiveWebsocketSessionContext;
import org.khasanof.factories.RxWebSocketSessionFacadeFactory;
import org.khasanof.model.WebSocketSessionFacade;
import org.khasanof.processor.ReactiveWebsocketProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Nurislom
 * @see org.khasanof.handler
 * @since 6/9/2024 5:13 AM
 */
@Component
public class ReactiveWebSocketHandler implements WebSocketHandler {

    private final ReactiveWebsocketProcessor reactiveWebsocketProcessor;
    private final ReactiveWebsocketSessionContext reactiveWebsocketSessionContext;
    private final RxWebSocketSessionFacadeFactory rxWebSocketSessionFacadeFactory;

    public ReactiveWebSocketHandler(ReactiveWebsocketProcessor reactiveWebsocketProcessor,
                                    ReactiveWebsocketSessionContext reactiveWebsocketSessionContext,
                                    RxWebSocketSessionFacadeFactory rxWebSocketSessionFacadeFactory) {

        this.reactiveWebsocketProcessor = reactiveWebsocketProcessor;
        this.reactiveWebsocketSessionContext = reactiveWebsocketSessionContext;
        this.rxWebSocketSessionFacadeFactory = rxWebSocketSessionFacadeFactory;
    }

    /**
     *
     * @param session
     * @return
     */
    @Override
    public Mono<Void> handle(WebSocketSession session) {
        return session.send(internalHandler(session));
    }

    private Flux<WebSocketMessage> internalHandler(WebSocketSession session) {
        WebSocketSessionFacade facade = getRxWebSocketSessionFacade(session);
        return reactiveWebsocketProcessor.process(facade, session);
    }

    private WebSocketSessionFacade getRxWebSocketSessionFacade(WebSocketSession session) {
        if (!reactiveWebsocketSessionContext.existSession(session.getId())) {
            WebSocketSessionFacade facade = rxWebSocketSessionFacadeFactory.create(session);
            reactiveWebsocketSessionContext.addSession(facade);
            return facade;
        }
        return reactiveWebsocketSessionContext.getSession(session.getId())
                .orElseThrow(() -> new RuntimeException("WebSocketSession not found!"));
    }
}
