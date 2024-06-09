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
public class WsRequest extends WsMethod {

    private Object data;
}
