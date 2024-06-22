package org.khasanof.factories.method;

import org.khasanof.model.method.WsMethod;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Nurislom
 * @see org.khasanof.factories
 * @since 6/9/2024 7:07 AM
 */
@Component
public class DefaultWsMethodFactory implements WsMethodFactory {

    /**
     *
     * @param instance
     * @param method
     * @return
     */
    @Override
    public WsMethod create(Object instance, Method method) {
        WsMethod wsMethod = new WsMethod();
        wsMethod.setMethod(method);
        wsMethod.setInstance(instance);
        wsMethod.setMethodName(method.getName());
        return wsMethod;
    }
}
