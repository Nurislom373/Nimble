package org.khasanof.executor.interceptor;

import org.khasanof.model.method.WsMethod;
import org.khasanof.model.ws.WsRequestSession;
import reactor.core.publisher.Mono;

/**
 * @author Nurislom
 * @see org.khasanof.executor.interceptor
 * @since 6/22/2024 6:57 PM
 */
public interface ExecutorInterceptor {

    /**
     *
     * @param wsMethod
     * @param request
     * @return
     */
    default boolean preIntercept(WsMethod wsMethod, Mono<WsRequestSession> request) {
        return true;
    }

    /**
     *
     * @param wsMethod
     * @param request
     */
    default void postIntercept(WsMethod wsMethod, Mono<WsRequestSession> request, Mono<Void> result) {}
}
