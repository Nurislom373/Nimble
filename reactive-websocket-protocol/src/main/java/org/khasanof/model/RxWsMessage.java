package org.khasanof.model;

import lombok.*;

/**
 * @author Nurislom
 * @see org.khasanof.model
 * @since 8/17/2024 5:56 PM
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RxWsMessage {

    private String message;
}
