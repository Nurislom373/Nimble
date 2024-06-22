package org.khasanof.model.ws;

import lombok.*;
import org.springframework.web.reactive.socket.WebSocketSession;

/**
 * @author Nurislom
 * @see org.khasanof.model.ws
 * @since 6/22/2024 8:06 PM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WsRequestSession extends WsRequest {

    private WebSocketSession session;

    public WsRequestSession(String id, String method, Object data, WebSocketSession session) {
        super(id, method, data);
        this.session = session;
    }
}
