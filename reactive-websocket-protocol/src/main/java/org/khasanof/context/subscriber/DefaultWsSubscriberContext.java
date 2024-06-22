package org.khasanof.context.subscriber;

import org.khasanof.model.method.Subscriber;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.context.subscriber
 * @since 6/22/2024 8:44 PM
 */
@Component
public class DefaultWsSubscriberContext implements WsSubscriberContext {

    private final Set<Subscriber> subscribers = new LinkedHashSet<>();

    @Override
    public void addSubscriber(Subscriber subscriber) {
        this.subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(String wsSessionId) {
        this.subscribers.removeIf(subscriber -> subscriber.getWsSessionId().equals(wsSessionId));
    }

    @Override
    public boolean existSubscriber(String wsSessionId) {
        return this.subscribers.stream()
                .anyMatch(subscriber -> subscriber.getWsSessionId().equals(wsSessionId));
    }

    @Override
    public Optional<Subscriber> getSubscriber(String wsSessionId) {
        return this.subscribers.stream()
                .filter(subscriber -> subscriber.getWsSessionId().equals(wsSessionId))
                .findFirst();
    }

    @Override
    public Set<Subscriber> getSubscribers() {
        return this.subscribers;
    }
}
