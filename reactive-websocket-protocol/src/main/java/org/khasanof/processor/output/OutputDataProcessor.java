package org.khasanof.processor.output;

import org.springframework.web.reactive.socket.WebSocketMessage;
import reactor.core.publisher.Flux;

/**
 * @author Nurislom
 * @see org.khasanof.processor.output
 * @since 6/8/2024 11:47 PM
 */
public interface OutputDataProcessor {

    /**
     *
     * @return
     */
    Flux<WebSocketMessage> output();
}
