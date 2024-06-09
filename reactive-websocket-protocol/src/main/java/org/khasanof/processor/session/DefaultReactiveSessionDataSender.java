package org.khasanof.processor.session;

import org.springframework.web.reactive.socket.WebSocketMessage;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

/**
 * @author Nurislom
 * @see org.khasanof.processor.session
 * @since 6/9/2024 4:57 AM
 */
public class DefaultReactiveSessionDataSender implements ReactiveSessionDataSender {

    protected final Sinks.Many<WebSocketMessage> sink = Sinks.many().multicast().onBackpressureBuffer();

    public DefaultReactiveSessionDataSender() {
    }

    /**
     *
     * @return
     */
    @Override
    public Flux<WebSocketMessage> output() {
        return this.sink.asFlux();
    }

    /**
     *
     * @param message
     */
    @Override
    public void emitNext(WebSocketMessage message) {
        this.sink.emitNext(message, Sinks.EmitFailureHandler.FAIL_FAST);
    }

    /**
     *
     * @param message
     * @param emitFailureHandler
     */
    @Override
    public void emitNext(WebSocketMessage message, Sinks.EmitFailureHandler emitFailureHandler) {
        this.sink.emitNext(message, emitFailureHandler);
    }

    /**
     *
     * @param message
     * @return
     */
    @Override
    public Sinks.EmitResult tryEmitNext(WebSocketMessage message) {
        return this.sink.tryEmitNext(message);
    }
}
