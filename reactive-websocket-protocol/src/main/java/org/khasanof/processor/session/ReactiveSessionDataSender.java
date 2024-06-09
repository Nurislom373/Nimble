package org.khasanof.processor.session;

import org.springframework.web.reactive.socket.WebSocketMessage;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

/**
 * @author Nurislom
 * @see org.khasanof.processor.session
 * @since 6/9/2024 4:55 AM
 */
public interface ReactiveSessionDataSender {

    /**
     *
     * @return
     */
    Flux<WebSocketMessage> output();

    /**
     *
     * @param message
     */
    void emitNext(WebSocketMessage message);

    /**
     *
     * @param message
     * @param emitFailureHandler
     */
    void emitNext(WebSocketMessage message, Sinks.EmitFailureHandler emitFailureHandler);

    /**
     *
     * @param message
     * @return
     */
    Sinks.EmitResult tryEmitNext(WebSocketMessage message);
}
