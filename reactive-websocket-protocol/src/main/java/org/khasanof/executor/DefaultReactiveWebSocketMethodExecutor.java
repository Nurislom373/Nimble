package org.khasanof.executor;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.khasanof.executor.mediator.InterceptorExecutorMediator;
import org.khasanof.model.method.WsMethod;
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
@Slf4j
@Component
@SuppressWarnings({"unchecked"})
public class DefaultReactiveWebSocketMethodExecutor implements ReactiveWebSocketMethodExecutor {

    private final ObjectMapper objectMapper;
    private final WsMethodService wsMethodService;
    private final InterceptorExecutorMediator interceptorExecutorMediator;

    public DefaultReactiveWebSocketMethodExecutor(ObjectMapper objectMapper,
                                                  WsMethodService wsMethodService,
                                                  InterceptorExecutorMediator interceptorExecutorMediator) {

        this.objectMapper = objectMapper;
        this.wsMethodService = wsMethodService;
        this.interceptorExecutorMediator = interceptorExecutorMediator;
    }

    /**
     *
     * @param wsMethod
     * @param request
     */
    @Override
    public Mono<Void> execute(WsMethod wsMethod, Mono<WsRequestSession> request) {
        if (interceptorExecutorMediator.preIntercept(wsMethod, request)) {
            return executeInternal(wsMethod, request);
        }
        return Mono.empty();
    }

    private Mono<Void> executeInternal(WsMethod wsMethod, Mono<WsRequestSession> request) {
        if (wsMethod.isDefaultMethod()) {
            log.info("Is Default Ws Method");
            return wsMethodService.execute(wsMethod, request);
        }
        log.info("Is Client Ws Method");
        return executeWsRequest(wsMethod, request);
    }

    private Mono<Void> executeWsRequest(WsMethod wsMethod, Mono<WsRequestSession> request) {
        Method method = wsMethod.getMethod();
        if (method.trySetAccessible()) {
            Mono<Void> result = tryExecute(wsMethod, request, method);
            interceptorExecutorMediator.postIntercept(wsMethod, request, result);
            return result;
        }
        throw new RuntimeException("Method not access!");
    }

    private Mono<Void> tryExecute(WsMethod wsMethod, Mono<WsRequestSession> request, Method method) {
        try {
            return (Mono<Void>) method.invoke(wsMethod.getInstance(), convertGenericType(wsMethod, request));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private Mono<Object> convertGenericType(WsMethod wsMethod, Mono<WsRequestSession> request) {
        return request
                .map(wsRequestSession -> objectMapper.convertValue(
                        wsRequestSession.getData(), wsMethod.getPayloadParameter().getGenericType()
                ));
    }
}
