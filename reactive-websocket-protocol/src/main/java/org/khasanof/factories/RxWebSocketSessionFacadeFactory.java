package org.khasanof.factories;

import org.khasanof.model.WebSocketSessionFacade;
import org.springframework.web.reactive.socket.WebSocketSession;

/**
 * @author Nurislom
 * @see org.khasanof.factories
 * @since 6/9/2024 5:15 AM
 */
public interface RxWebSocketSessionFacadeFactory {

    /**
     *
     * @param session
     * @return
     */
    WebSocketSessionFacade create(WebSocketSession session);
}
