package org.khasanof;

import org.khasanof.processor.output.strategy.SendOutputDataStrategy;
import org.springframework.stereotype.Component;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 6/9/2024 7:13 AM
 */
@Component
public class DefaultReactiveWebSocketMessageTemplate implements ReactiveWebsocketMessageTemplate {

    private final SendOutputDataStrategy sendOutputDataStrategy;

    public DefaultReactiveWebSocketMessageTemplate(SendOutputDataStrategy sendOutputDataStrategy) {
        this.sendOutputDataStrategy = sendOutputDataStrategy;
    }

    /**
     *
     * @param message
     */
    @Override
    public void sendMessageOnlyUser(Object message) {
        this.sendOutputDataStrategy.send(message);
    }

    /**
     *
     * @param sessionId
     * @param message
     */
    @Override
    public void sendMessageOnlyUser(String sessionId, Object message) {
        this.sendOutputDataStrategy.send(sessionId, message);
    }
}
