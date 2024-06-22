package org.khasanof.executor.mediator;

import org.khasanof.model.method.WsMethod;
import org.khasanof.model.ws.WsRequestSession;
import reactor.core.publisher.Mono;

/**
 * @author Nurislom
 * @see org.khasanof.executor.mediator
 * @since 6/22/2024 6:58 PM
 */
public interface ExecutorInterceptorMediator {

    /**
     *
     * @param wsMethod
     * @param request
     * @return
     */
    boolean preIntercept(WsMethod wsMethod, Mono<WsRequestSession> request);

    /**
     *
     * @param wsMethod
     * @param request
     */
    void postIntercept(WsMethod wsMethod, Mono<WsRequestSession> request, Mono<Void> result);
}
