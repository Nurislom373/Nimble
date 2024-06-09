package org.khasanof.converter;

import org.khasanof.model.ws.WsRequest;
import org.springframework.web.reactive.socket.WebSocketMessage;

/**
 * @author Nurislom
 * @see org.khasanof.converter
 * @since 6/9/2024 6:41 AM
 */
public interface WebSocketMessageConverter {

    /**
     *
     * @param message
     * @return
     */
    WsRequest convert(WebSocketMessage message);
}
