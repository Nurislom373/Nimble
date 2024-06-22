package org.khasanof.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.khasanof.model.method.WsMethodDefinition;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.constants
 * @since 6/22/2024 7:10 PM
 */
@Getter
@RequiredArgsConstructor
public enum DefaultWsMethodDefinitions implements WsMethodDefinition {

    SUBSCRIBE("SUBSCRIBE");

    private final String name;

    /**
     *
     * @param methodName
     * @return
     */
    public static boolean isMatch(String methodName) {
        return Arrays.stream(values())
                .anyMatch(value -> Objects.equals(value.getName(), methodName));
    }
}
