package org.khasanof.factories.method;

import org.khasanof.annotation.ReactiveWsMethod;
import org.khasanof.model.method.WsMethod;
import org.khasanof.service.WsMethodDefinitionService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Nurislom
 * @see org.khasanof.factories.method
 * @since 6/22/2024 6:49 PM
 */
@Primary
@Component
public class ReactiveWsMethodFactory implements WsMethodFactory {

    private final WsMethodDefinitionService wsMethodDefinitionService;

    public ReactiveWsMethodFactory(WsMethodDefinitionService wsMethodDefinitionService) {
        this.wsMethodDefinitionService = wsMethodDefinitionService;
    }

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

        ReactiveWsMethod annotation = getAnnotation(method);
        wsMethod.setAnnotation(annotation);
        wsMethod.setMethodName(annotation.value());
        setIsDefaultMethod(wsMethod, annotation);

        return wsMethod;
    }

    private ReactiveWsMethod getAnnotation(Method method) {
        if (!method.isAnnotationPresent(ReactiveWsMethod.class)) {
            throw new RuntimeException("ReactiveWsMethod annotation not found!");
        }
        return method.getAnnotation(ReactiveWsMethod.class);
    }

    private void setIsDefaultMethod(WsMethod wsMethod, ReactiveWsMethod annotation) {
        wsMethod.setDefaultMethod(wsMethodDefinitionService.isDefaultMethod(annotation.value()));
    }
}
