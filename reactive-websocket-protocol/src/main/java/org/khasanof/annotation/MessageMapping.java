package org.khasanof.annotation;

import java.lang.annotation.*;

/**
 * @author Nurislom
 * @see org.khasanof.annotation
 * @since 6/8/2024 10:32 PM
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MessageMapping {

    /**
     *
     * @return
     */
    String value() default "";
}
