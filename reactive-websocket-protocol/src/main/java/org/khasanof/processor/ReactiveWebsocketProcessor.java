package org.khasanof.processor;

import org.khasanof.model.WebSocketSessionFacade;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;

/**
 * @author Nurislom
 * @see org.khasanof.processor
 * @since 6/8/2024 10:55 PM
 */
public interface ReactiveWebsocketProcessor {

    /**
     *
     * @param facade
     * @return
     */
    Flux<WebSocketMessage> process(WebSocketSessionFacade facade, WebSocketSession session);
}
