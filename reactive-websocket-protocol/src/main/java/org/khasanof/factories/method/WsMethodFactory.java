package org.khasanof.factories.method;

import org.khasanof.model.method.WsMethod;

import java.lang.reflect.Method;

/**
 * @author Nurislom
 * @see org.khasanof.factories
 * @since 6/9/2024 7:06 AM
 */
public interface WsMethodFactory {

    /**
     *
     * @param instance
     * @param method
     * @return
     */
    WsMethod create(Object instance, Method method);
}
