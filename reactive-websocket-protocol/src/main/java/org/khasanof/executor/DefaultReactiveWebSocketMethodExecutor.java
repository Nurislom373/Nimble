package org.khasanof.executor;

import org.khasanof.model.method.WsProtocolMethod;
import org.khasanof.model.ws.WsRequest;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Nurislom
 * @see org.khasanof.executor
 * @since 6/9/2024 6:57 AM
 */
@Component
public class DefaultReactiveWebSocketMethodExecutor implements ReactiveWebSocketMethodExecutor {

    /**
     *
     * @param wsProtocolMethod
     * @param request
     */
    @Override
    public void execute(WsProtocolMethod wsProtocolMethod, WsRequest request) {
        Method method = wsProtocolMethod.getMethod();
        if (method.trySetAccessible()) {
            tryExecute(wsProtocolMethod, request, method);
        }
    }

    private void tryExecute(WsProtocolMethod wsProtocolMethod, WsRequest request, Method method) {
        try {
            method.invoke(wsProtocolMethod.getInstance(), request);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
