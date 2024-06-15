package org.khasanof.executor;

import org.khasanof.model.method.WsProtocolMethod;
import org.khasanof.model.ws.WsRequest;
import reactor.core.publisher.Mono;

/**
 * @author Nurislom
 * @see org.khasanof.executor
 * @since 6/9/2024 6:56 AM
 */
public interface ReactiveWebSocketMethodExecutor {

    /**
     *
     * @param method
     * @param request
     */
    Mono<Void> execute(WsProtocolMethod method, Mono<WsRequest> request);
}
