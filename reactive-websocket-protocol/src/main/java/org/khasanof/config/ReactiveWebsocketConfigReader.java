package org.khasanof.config;

import org.springframework.web.reactive.socket.server.WebSocketService;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

/**
 * @author Nurislom
 * @see org.khasanof.config
 * @since 6/9/2024 5:10 AM
 */
public interface ReactiveWebsocketConfigReader {

    /**
     *
     * @return
     */
    String endpoint();

    /**
     *
     * @return
     */
    WebSocketService webSocketService();

    /**
     *
     * @return
     */
    WebSocketHandlerAdapter webSocketHandlerAdapter();
}
