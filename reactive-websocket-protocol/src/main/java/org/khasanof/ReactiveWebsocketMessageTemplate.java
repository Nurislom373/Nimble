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
    void sendMessageOnlyUser(Object message);

    /**
     *
     * @param sessionId
     * @param message
     */
    void sendMessageOnlyUser(String sessionId, Object message);
}
