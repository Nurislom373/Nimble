package org.khasanof.annotation;

import java.lang.annotation.*;

/**
 * @author Nurislom
 * @see org.khasanof.annotation
 * @since 6/22/2024 8:51 PM
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Payload {
}
