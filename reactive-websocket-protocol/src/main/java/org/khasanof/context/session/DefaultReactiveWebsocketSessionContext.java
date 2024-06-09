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
        this.sessions.put(session.getSessionId(), session);
    }

    /**
     *
     * @param sessionId
     * @return
     */
    @Override
    public boolean existSession(String sessionId) {
        return this.sessions.containsKey(sessionId);
    }

    /**
     *
     * @param sessionId
     * @return
     */
    @Override
    public Optional<WebSocketSessionFacade> getSession(String sessionId) {
        return Optional.ofNullable(this.sessions.get(sessionId));
    }

    /**
     *
     * @param sessionId
     */
    @Override
    public void removeSession(String sessionId) {
        this.sessions.remove(sessionId);
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
