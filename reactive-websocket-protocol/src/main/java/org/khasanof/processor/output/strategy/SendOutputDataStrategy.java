package org.khasanof.processor.output.strategy;

/**
 * @author Nurislom
 * @see org.khasanof.processor.output.strategy
 * @since 6/9/2024 4:44 AM
 */
public interface SendOutputDataStrategy {

    /**
     *
     * @param message
     */
    void send(Object message);

    /**
     *
     * @param sessionId
     * @param message
     */
    void send(String sessionId, Object message);
}
