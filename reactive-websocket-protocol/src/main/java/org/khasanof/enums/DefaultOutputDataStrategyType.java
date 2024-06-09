package org.khasanof.enums;

import lombok.RequiredArgsConstructor;
import org.khasanof.model.OutputDataStrategyType;

/**
 * @author Nurislom
 * @see org.khasanof.enums
 * @since 6/9/2024 4:45 AM
 */
@RequiredArgsConstructor
public enum DefaultOutputDataStrategyType implements OutputDataStrategyType {
    ALL {
    },
    SINGLE {
    }
}
