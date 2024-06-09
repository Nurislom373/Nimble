package org.khasanof.factories;

import org.khasanof.model.method.WsProtocolMethod;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Nurislom
 * @see org.khasanof.factories
 * @since 6/9/2024 7:07 AM
 */
@Component
public class DefaultWsProtocolMethodFactory implements WsProtocolMethodFactory {

    /**
     *
     * @param instance
     * @param method
     * @return
     */
    @Override
    public WsProtocolMethod create(Object instance, Method method) {
        WsProtocolMethod wsProtocolMethod = new WsProtocolMethod();
        wsProtocolMethod.setMethod(method);
        wsProtocolMethod.setInstance(instance);
        wsProtocolMethod.setMethodName(method.getName());
        return wsProtocolMethod;
    }
}
