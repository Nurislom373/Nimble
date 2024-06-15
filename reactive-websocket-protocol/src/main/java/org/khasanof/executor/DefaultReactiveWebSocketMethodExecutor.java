package org.khasanof.executor;

import org.khasanof.model.method.WsProtocolMethod;
import org.khasanof.model.ws.WsRequest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Nurislom
 * @see org.khasanof.executor
 * @since 6/9/2024 6:57 AM
 */
@Component
@SuppressWarnings({"unchecked"})
public class DefaultReactiveWebSocketMethodExecutor implements ReactiveWebSocketMethodExecutor {

    /**
     *
     * @param wsProtocolMethod
     * @param request
     */
    @Override
    public Mono<Void> execute(WsProtocolMethod wsProtocolMethod, Mono<WsRequest> request) {
        Method method = wsProtocolMethod.getMethod();
        if (method.trySetAccessible()) {
            return tryExecute(wsProtocolMethod, request, method);
        }
        throw new RuntimeException("Method not access!");
    }

    private Mono<Void> tryExecute(WsProtocolMethod wsProtocolMethod, Mono<WsRequest> request, Method method) {
        try {
            return (Mono<Void>) method.invoke(wsProtocolMethod.getInstance(), request);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
