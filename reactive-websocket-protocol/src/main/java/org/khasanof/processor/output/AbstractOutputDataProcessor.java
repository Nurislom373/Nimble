package org.khasanof.processor.output;

import org.springframework.web.reactive.socket.WebSocketMessage;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

/**
 * @author Nurislom
 * @see org.khasanof.processor.output
 * @since 6/9/2024 12:07 AM
 */
public abstract class AbstractOutputDataProcessor implements OutputDataProcessor {

    protected final Sinks.Many<WebSocketMessage> sink = Sinks.many().multicast().onBackpressureBuffer();

    /**
     *
     * @return
     */
    @Override
    public Flux<WebSocketMessage> output() {
        return sink.asFlux();
    }

    /**
     *
     * @param message
     */
    public void emitNext(WebSocketMessage message) {
        this.sink.emitNext(message, Sinks.EmitFailureHandler.FAIL_FAST);
    }

    /**
     *
     * @param message
     * @return
     */
    public Sinks.EmitResult tryEmitNext(WebSocketMessage message) {
        return this.sink.tryEmitNext(message);
    }
}
