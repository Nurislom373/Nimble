package org.khasanof.context.subscriber;

import org.khasanof.model.method.Subscriber;

import java.util.Optional;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.context.subscriber
 * @since 6/22/2024 8:41 PM
 */
public interface WsSubscriberContext {

    /**
     *
     * @param subscriber
     */
    void addSubscriber(Subscriber subscriber);

    /**
     *
     * @param wsSessionId
     */
    void removeSubscriber(String wsSessionId);

    /**
     *
     * @param wsSessionId
     * @return
     */
    boolean existSubscriber(String wsSessionId);

    /**
     *
     * @param wsSessionId
     * @return
     */
    Optional<Subscriber> getSubscriber(String wsSessionId);

    /**
     *
     * @return
     */
    Set<Subscriber> getSubscribers();
}
