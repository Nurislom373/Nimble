package org.khasanof.processor.session;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Nurislom
 * @see org.khasanof.processor.session
 * @since 6/9/2024 4:38 AM
 */
@Component
public class DefaultSessionOutputDataProcessor implements SessionOutputDataProcessor {

    @Override
    public Mono<Void> output(WebSocketSession session, Flux<WebSocketMessage> messages) {
        return session.send(messages);
    }
}
