package org.khasanof;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 6/8/2024 10:33 PM
 */
public interface ReactiveWebsocketMessageTemplate {

    /**
     *
     * @param message
     */
    void sendMessage(Object message);

    /**
     *
     * @param sessionId
     * @param message
     */
    void sendMessage(String sessionId, Object message);
}
