package org.khasanof.executor;

import org.khasanof.model.method.WsMethod;
import org.khasanof.model.ws.WsRequest;
import org.khasanof.model.ws.WsRequestSession;
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
    Mono<Void> execute(WsMethod method, Mono<WsRequestSession> request);
}
