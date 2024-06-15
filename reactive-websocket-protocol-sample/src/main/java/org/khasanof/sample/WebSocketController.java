package org.khasanof.sample;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.ReactiveWebsocketMessageTemplate;
import org.khasanof.annotation.MessageController;
import org.khasanof.annotation.MessageMapping;
import org.khasanof.model.ws.WsRequest;
import reactor.core.publisher.Mono;

/**
 * @author Nurislom
 * @see org.khasanof.sample
 * @since 6/9/2024 8:43 AM
 */
@Slf4j
@MessageController
public class WebSocketController {

    private final ReactiveWebsocketMessageTemplate messageTemplate;

    public WebSocketController(ReactiveWebsocketMessageTemplate messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    @MessageMapping
    public Mono<Void> handle(Mono<WsRequest> request) {
        return request.doOnNext(wsRequest -> log.info("ws request : {}", wsRequest))
                .doOnNext(wsRequest -> messageTemplate.sendMessage("Hello World!"))
                .then();
    }
}
