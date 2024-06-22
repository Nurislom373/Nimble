package org.khasanof.executor.interceptor;

import org.khasanof.model.method.WsMethod;
import org.khasanof.model.ws.WsRequestSession;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author Nurislom
 * @see org.khasanof.executor.interceptor
 * @since 6/22/2024 7:07 PM
 */
@Component
public class DefaultWsMethodsExecutorInterceptor implements ExecutorInterceptor {

    @Override
    public boolean preIntercept(WsMethod wsMethod, Mono<WsRequestSession> request) {
        return false;
    }
}
