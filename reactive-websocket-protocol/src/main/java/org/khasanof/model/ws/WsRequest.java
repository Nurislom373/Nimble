package org.khasanof.model.ws;

import lombok.*;

/**
 * @author Nurislom
 * @see org.khasanof.model
 * @since 6/9/2024 6:39 AM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WsRequest extends BaseWs {

    private Object data;

    public WsRequest(String id, String method, Object data) {
        super(id, method);
        this.data = data;
    }
}
