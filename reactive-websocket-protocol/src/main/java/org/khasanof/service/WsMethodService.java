package org.khasanof.service;

import org.khasanof.model.method.WsMethod;
import org.khasanof.model.ws.WsRequest;
import org.khasanof.model.ws.WsRequestSession;
import reactor.core.publisher.Mono;

/**
 * @author Nurislom
 * @see org.khasanof.service
 * @since 6/22/2024 7:30 PM
 */
public interface WsMethodService {

    /**
     *
     * @param wsMethod
     * @param request
     */
    Mono<Void> execute(WsMethod wsMethod, Mono<WsRequestSession> request);
}
