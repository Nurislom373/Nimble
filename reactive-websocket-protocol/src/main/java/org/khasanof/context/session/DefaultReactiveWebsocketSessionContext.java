package org.khasanof.context.session;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.model.WebSocketSessionFacade;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Nurislom
 * @see org.khasanof.context
 * @since 6/8/2024 10:36 PM
 */
@Slf4j
@Component
public class DefaultReactiveWebsocketSessionContext implements ReactiveWebsocketSessionContext {

    private final Map<String, WebSocketSessionFacade> sessions = new ConcurrentHashMap<>();

    /**
     *
     * @param session
     */
    @Override
    public void addSession(WebSocketSessionFacade session) {
        this.sessions.put(session.getWsSessionId(), session);
    }

    /**
     *
     * @param wsSessionId
     * @return
     */
    @Override
    public boolean existSession(String wsSessionId) {
        return this.sessions.containsKey(wsSessionId);
    }

    /**
     *
     * @param wsSessionId
     * @return
     */
    @Override
    public boolean isSubscribed(String wsSessionId) {
        return Optional.ofNullable(this.sessions.get(wsSessionId))
                .map(WebSocketSessionFacade::isSubscribe)
                .orElse(false);
    }

    /**
     *
     * @param wsSessionId
     * @return
     */
    @Override
    public Optional<WebSocketSessionFacade> getSession(String wsSessionId) {
        return Optional.ofNullable(this.sessions.get(wsSessionId));
    }

    /**
     *
     * @param wsSessionId
     */
    @Override
    public void removeSession(String wsSessionId) {
        this.sessions.remove(wsSessionId);
    }

    /**
     *
     * @return
     */
    @Override
    public Set<WebSocketSessionFacade> getSessions() {
        return new HashSet<>(this.sessions.values());
    }

    /**
     *
     * @return
     */
    @Override
    public Flux<WebSocketSessionFacade> getFluxSessions() {
        return Flux.fromIterable(this.sessions.values());
    }
}
