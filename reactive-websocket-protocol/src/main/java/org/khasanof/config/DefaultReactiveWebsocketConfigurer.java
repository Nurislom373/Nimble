package org.khasanof.config;

import lombok.*;
import org.springframework.web.reactive.socket.server.WebSocketService;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

/**
 * @author Nurislom
 * @see org.khasanof.config
 * @since 6/8/2024 10:43 PM
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DefaultReactiveWebsocketConfigurer implements ReactiveWebsocketConfigurer, ReactiveWebsocketConfigReader {

    private String endpoint;
    private WebSocketService webSocketService;
    private WebSocketHandlerAdapter webSocketHandlerAdapter;

    @Override
    public void endpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public void webSocketService(WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }

    @Override
    public void webSocketHandlerAdapter(WebSocketHandlerAdapter webSocketHandlerAdapter) {
        this.webSocketHandlerAdapter = webSocketHandlerAdapter;
    }

    @Override
    public String endpoint() {
        return this.endpoint;
    }

    @Override
    public WebSocketService webSocketService() {
        return this.webSocketService;
    }

    @Override
    public WebSocketHandlerAdapter webSocketHandlerAdapter() {
        return this.webSocketHandlerAdapter;
    }
}
