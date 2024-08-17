package org.khasanof.flow;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

/**
 * @author Nurislom
 * @see org.khasanof.flow
 * @since 6/15/2024 7:38 PM
 */
public abstract class AbstractSinksManyFlow<T> {

    protected final Sinks.EmitFailureHandler RETRY_NON_SERIALIZED = (signalType, emitResult) -> true;
    protected final Sinks.Many<T> sink;

    public AbstractSinksManyFlow() {
        this.sink = Sinks.many()
                .multicast()
                .onBackpressureBuffer(256, false);
    }

    public AbstractSinksManyFlow(Sinks.Many<T> sink) {
        this.sink = sink;
    }

    /**
     *
     * @return
     */
    public Flux<T> flowAsFlux() {
        return this.sink.asFlux();
    }

    /**
     *
     * @return
     */
    public Sinks.Many<T> flow() {
       return this.sink;
    }

    /**
     *
     * @param message
     */
    public void emitNext(T message) {
        this.sink.emitNext(message, RETRY_NON_SERIALIZED);
    }

    /**
     *
     * @param message
     * @param emitFailureHandler
     */
    public void emitNext(T message, Sinks.EmitFailureHandler emitFailureHandler) {
        this.sink.tryEmitNext(message);
    }

    /**
     *
     * @param message
     * @return
     */
    public Sinks.EmitResult tryEmitNext(T message) {
        return this.sink.tryEmitNext(message);
    }
}
