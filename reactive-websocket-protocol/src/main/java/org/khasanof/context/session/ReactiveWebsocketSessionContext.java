package org.khasanof.context.session;

import org.khasanof.model.WebSocketSessionFacade;
import reactor.core.publisher.Flux;

import java.util.Optional;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.context
 * @since 6/8/2024 10:35 PM
 */
public interface ReactiveWebsocketSessionContext {

    /**
     *
     * @param session
     */
    void addSession(WebSocketSessionFacade session);

    /**
     *
     * @param sessionId
     * @return
     */
    boolean existSession(String sessionId);

    /**
     *
     * @param sessionId
     * @return
     */
    Optional<WebSocketSessionFacade> getSession(String sessionId);

    /**
     *
     * @param sessionId
     */
    void removeSession(String sessionId);

    /**
     *
     * @return
     */
    Set<WebSocketSessionFacade> getSessions();

    /**
     *
     * @return
     */
    Flux<WebSocketSessionFacade> getFluxSessions();
}
