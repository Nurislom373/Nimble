package org.khasanof.context.method;

import org.khasanof.model.method.WsMethod;

import java.util.Optional;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.context.method
 * @since 6/9/2024 6:47 AM
 */
public interface ReactiveWebSocketMethodContext {

    /**
     *
     * @param methodName
     * @param method
     */
    void addMethod(String methodName, WsMethod method);

    /**
     *
     * @param methodName
     * @return
     */
    Optional<WsMethod> getMethod(String methodName);

    /**
     *
     * @param methodName
     * @return
     */
    boolean existMethod(String methodName);

    /**
     *
     * @return
     */
    Set<WsMethod> getMethods();
}
