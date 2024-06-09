package org.khasanof;

import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import static org.khasanof.constants.GlobalConstants.BASE_PACKAGE;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 6/9/2024 7:32 AM
 */
@ComponentScan(excludeFilters = { @ComponentScan.Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) }, basePackages = {BASE_PACKAGE})
public class ReactiveWsProtocolAutoConfiguration {
}
