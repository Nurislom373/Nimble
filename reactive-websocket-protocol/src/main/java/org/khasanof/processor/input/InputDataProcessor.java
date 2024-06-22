package org.khasanof.processor.input;

import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;

/**
 * @author Nurislom
 * @see org.khasanof.processor.input
 * @since 6/8/2024 11:09 PM
 */
public interface InputDataProcessor {

    /**
     *
     * @param messages
     * @return
     */
    Flux<WebSocketMessage> input(Flux<WebSocketMessage> messages, WebSocketSession session);
}
