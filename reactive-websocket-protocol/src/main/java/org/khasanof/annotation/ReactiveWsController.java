package org.khasanof.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author Nurislom
 * @see org.khasanof.annotation
 * @since 6/22/2024 6:43 PM
 */
@Documented
@Component
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ReactiveWsController {
}
