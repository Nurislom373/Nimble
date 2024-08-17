package org.khasanof.model;

import lombok.*;
import org.khasanof.flow.output.OutputDataFlow;
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
    private String wsSessionId;
    private boolean isSubscribe;
    private OutputDataFlow outputDataFlow;
    private WebSocketSession webSocketSession;
}
