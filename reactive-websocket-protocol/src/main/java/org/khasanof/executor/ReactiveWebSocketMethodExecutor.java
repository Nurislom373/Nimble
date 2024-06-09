package org.khasanof.executor;

import org.khasanof.model.method.WsProtocolMethod;
import org.khasanof.model.ws.WsRequest;

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
    void execute(WsProtocolMethod method, WsRequest request);
}
