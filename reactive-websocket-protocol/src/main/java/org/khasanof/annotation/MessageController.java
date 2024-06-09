package org.khasanof.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author Nurislom
 * @see org.khasanof.annotation
 * @since 6/9/2024 7:04 AM
 */
@Documented
@Component
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MessageController {
}
