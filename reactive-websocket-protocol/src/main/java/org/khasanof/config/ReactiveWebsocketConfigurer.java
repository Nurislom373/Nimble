package org.khasanof.config;

import org.springframework.web.reactive.socket.server.WebSocketService;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

/**
 * @author Nurislom
 * @see org.khasanof.config
 * @since 6/8/2024 10:41 PM
 */
public interface ReactiveWebsocketConfigurer {

    /**
     *
     * @param endpoint
     */
    void endpoint(String endpoint);

    /**
     *
     * @param webSocketHandlerAdapter
     */
    void webSocketHandlerAdapter(WebSocketHandlerAdapter webSocketHandlerAdapter);

    /**
     *
     * @param webSocketService
     */
    void webSocketService(WebSocketService webSocketService);
}
