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
     * @param wsSessionId
     * @return
     */
    boolean existSession(String wsSessionId);

    /**
     *
     * @param wsSessionId
     * @return
     */
    boolean isSubscribed(String wsSessionId);

    /**
     *
     * @param wsSessionId
     * @return
     */
    Optional<WebSocketSessionFacade> getSession(String wsSessionId);

    /**
     *
     * @param wsSessionId
     */
    void removeSession(String wsSessionId);

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
