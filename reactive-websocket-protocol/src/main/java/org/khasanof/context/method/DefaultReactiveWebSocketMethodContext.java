package org.khasanof.context.method;

import org.khasanof.model.method.WsProtocolMethod;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Nurislom
 * @see org.khasanof.context.method
 * @since 6/9/2024 6:48 AM
 */
@Component
public class DefaultReactiveWebSocketMethodContext implements ReactiveWebSocketMethodContext {

    private final Map<String, WsProtocolMethod> methodMap = new ConcurrentHashMap<>();

    /**
     *
     * @param methodName
     * @param method
     */
    @Override
    public void addMethod(String methodName, WsProtocolMethod method) {
        this.methodMap.put(methodName, method);
    }

    /**
     *
     * @param methodName
     * @return
     */
    @Override
    public Optional<WsProtocolMethod> getMethod(String methodName) {
        return Optional.ofNullable(this.methodMap.get(methodName));
    }

    /**
     *
     * @param methodName
     * @return
     */
    @Override
    public boolean existMethod(String methodName) {
        return this.methodMap.containsKey(methodName);
    }

    /**
     *
     * @return
     */
    @Override
    public Set<WsProtocolMethod> getMethods() {
        return new HashSet<>(this.methodMap.values());
    }
}
