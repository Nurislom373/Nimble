package org.khasanof.model.method;

import lombok.*;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.model.method
 * @since 7/6/2024 12:10 AM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WsMethodParameter {

    private String name;
    private Class<?> type;
    private List<Class<?>> genericTypes;
    private List<Annotation> annotations;

    /**
     *
     * @return
     */
    public boolean hasGenericTypes() {
        return Objects.nonNull(genericTypes);
    }
}
