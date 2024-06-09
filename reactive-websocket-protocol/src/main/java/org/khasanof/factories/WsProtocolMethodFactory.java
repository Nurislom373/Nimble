package org.khasanof.factories;

import org.khasanof.model.method.WsProtocolMethod;

import java.lang.reflect.Method;

/**
 * @author Nurislom
 * @see org.khasanof.factories
 * @since 6/9/2024 7:06 AM
 */
public interface WsProtocolMethodFactory {

    /**
     *
     * @param instance
     * @param method
     * @return
     */
    WsProtocolMethod create(Object instance, Method method);
}
