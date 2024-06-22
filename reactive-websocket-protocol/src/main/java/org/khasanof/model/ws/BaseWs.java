package org.khasanof.model.ws;

import lombok.*;

/**
 * @author Nurislom
 * @see org.khasanof.model
 * @since 6/9/2024 6:38 AM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseWs {

    private String id;
    private String method;
}
