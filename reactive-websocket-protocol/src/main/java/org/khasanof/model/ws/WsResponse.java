package org.khasanof.model.ws;

import lombok.*;

/**
 * @author Nurislom
 * @see org.khasanof.model
 * @since 6/9/2024 6:40 AM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WsResponse extends BaseWs {

    private Object data;
    private String message;
    private Boolean success;
}
