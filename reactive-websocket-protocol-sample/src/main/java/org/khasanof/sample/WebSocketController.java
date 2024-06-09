package org.khasanof.sample;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.ReactiveWebsocketMessageTemplate;
import org.khasanof.annotation.MessageController;
import org.khasanof.annotation.MessageMapping;
import org.khasanof.model.ws.WsRequest;

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
    public void handle(WsRequest request) {
        log.info("request : {}", request);
        messageTemplate.sendMessage("Hello World!");
    }
}
