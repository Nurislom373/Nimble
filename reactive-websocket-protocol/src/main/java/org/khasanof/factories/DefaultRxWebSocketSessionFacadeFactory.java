package org.khasanof.factories;

import org.khasanof.model.WebSocketSessionFacade;
import org.khasanof.processor.session.DefaultReactiveSessionDataSender;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketSession;

/**
 * @author Nurislom
 * @see org.khasanof.factories
 * @since 6/9/2024 5:16 AM
 */
@Component
public class DefaultRxWebSocketSessionFacadeFactory implements RxWebSocketSessionFacadeFactory {

    /**
     *
     * @param session
     * @return
     */
    @Override
    public WebSocketSessionFacade create(WebSocketSession session) {
        WebSocketSessionFacade facade = new WebSocketSessionFacade();
        facade.setWebSocketSession(session);

        facade.setSessionId(session.getId());
        facade.setReactiveSessionDataSender(new DefaultReactiveSessionDataSender());
        return facade;
    }
}
