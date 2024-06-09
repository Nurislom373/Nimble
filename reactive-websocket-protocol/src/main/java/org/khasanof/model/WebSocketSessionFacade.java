package org.khasanof.model;

import lombok.*;
import org.khasanof.processor.session.ReactiveSessionDataSender;
import org.springframework.web.reactive.socket.WebSocketSession;

/**
 * @author Nurislom
 * @see org.khasanof.model
 * @since 6/9/2024 4:53 AM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WebSocketSessionFacade {

    private String sessionId;
    private WebSocketSession webSocketSession;
    private ReactiveSessionDataSender reactiveSessionDataSender;
}
