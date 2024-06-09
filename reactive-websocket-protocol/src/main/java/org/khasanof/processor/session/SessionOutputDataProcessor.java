package org.khasanof.processor.session;

import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Nurislom
 * @see org.khasanof.processor.session
 * @since 6/9/2024 4:37 AM
 */
public interface SessionOutputDataProcessor {

    /**
     *
     * @param session
     * @param messages
     */
    Mono<Void> output(WebSocketSession session, Flux<WebSocketMessage> messages);
}
