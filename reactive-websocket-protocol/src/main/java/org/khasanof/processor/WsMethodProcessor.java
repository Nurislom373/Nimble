package org.khasanof.processor;

import org.khasanof.model.method.WsMethodDefinition;
import org.khasanof.model.ws.WsRequestSession;
import reactor.core.publisher.Mono;

/**
 * @author Nurislom
 * @see org.khasanof.processor
 * @since 6/22/2024 7:32 PM
 */
public interface WsMethodProcessor extends WsMethodDefinition {

    /**
     *
     * @param request
     */
    Mono<Void> process(Mono<WsRequestSession> request);
}
