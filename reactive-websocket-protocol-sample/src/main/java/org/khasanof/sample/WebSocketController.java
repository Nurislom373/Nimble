package org.khasanof.sample;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.ReactiveWebsocketMessageTemplate;
import org.khasanof.annotation.*;
import org.khasanof.model.ws.WsRequest;
import reactor.core.publisher.Mono;

/**
 * @author Nurislom
 * @see org.khasanof.sample
 * @since 6/9/2024 8:43 AM
 */
@Slf4j
@ReactiveWsController
public class WebSocketController {

    private final ReactiveWebsocketMessageTemplate messageTemplate;

    public WebSocketController(ReactiveWebsocketMessageTemplate messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    @ReactiveWsMethod("handle")
    public Mono<Void> handle(@Payload Mono<Message> request) {
        return request.doOnNext(message -> log.info("message object : {}", message))
                .doOnNext(wsRequest -> messageTemplate.sendMessageOnlyUser("Hello World!"))
                .then();
    }
}
