package org.khasanof.executor;

import org.khasanof.executor.mediator.ExecutorInterceptorMediator;
import org.khasanof.model.method.WsMethod;
import org.khasanof.model.ws.WsRequest;
import org.khasanof.model.ws.WsRequestSession;
import org.khasanof.service.WsMethodService;
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

    private final WsMethodService wsMethodService;
    private final ExecutorInterceptorMediator executorInterceptorMediator;

    public DefaultReactiveWebSocketMethodExecutor(WsMethodService wsMethodService, ExecutorInterceptorMediator executorInterceptorMediator) {
        this.wsMethodService = wsMethodService;
        this.executorInterceptorMediator = executorInterceptorMediator;
    }

    /**
     *
     * @param wsMethod
     * @param request
     */
    @Override
    public Mono<Void> execute(WsMethod wsMethod, Mono<WsRequestSession> request) {
        if (executorInterceptorMediator.preIntercept(wsMethod, request)) {
            return executeInternal(wsMethod, request);
        }
        return Mono.empty();
    }

    private Mono<Void> executeInternal(WsMethod wsMethod, Mono<WsRequestSession> request) {
        if (wsMethod.isDefaultMethod()) {
            return wsMethodService.execute(wsMethod, request);
        }
        return executeWsRequest(wsMethod, request);
    }

    private Mono<Void> executeWsRequest(WsMethod wsMethod, Mono<WsRequestSession> request) {
        Method method = wsMethod.getMethod();
        if (method.trySetAccessible()) {
            Mono<Void> result = tryExecute(wsMethod, request, method);
            executorInterceptorMediator.postIntercept(wsMethod, request, result);
            return result;
        }
        throw new RuntimeException("Method not access!");
    }

    private Mono<Void> tryExecute(WsMethod wsMethod, Mono<WsRequestSession> request, Method method) {
        try {
            return (Mono<Void>) method.invoke(wsMethod.getInstance(), request);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
