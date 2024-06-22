package org.khasanof.model.method;

import lombok.*;

/**
 * @author Nurislom
 * @see org.khasanof.model.method
 * @since 6/22/2024 8:42 PM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Subscriber {

    private String sessionId;
    private String wsSessionId;
}
