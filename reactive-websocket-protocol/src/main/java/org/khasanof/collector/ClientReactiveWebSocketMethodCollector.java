package org.khasanof.collector;

import org.khasanof.annotation.ReactiveWsController;
import org.khasanof.annotation.ReactiveWsMethod;
import org.khasanof.context.method.ReactiveWebSocketMethodContext;
import org.khasanof.factories.method.WsMethodFactory;
import org.khasanof.model.method.WsMethod;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.collector
 * @since 6/9/2024 7:03 AM
 */
@Component
public class ClientReactiveWebSocketMethodCollector extends AbstractCollector {

    public ClientReactiveWebSocketMethodCollector(WsMethodFactory wsMethodFactory,
                                                  ApplicationContext applicationContext,
                                                  ReactiveWebSocketMethodContext reactiveWebSocketMethodContext) {

        super(wsMethodFactory, applicationContext, reactiveWebSocketMethodContext);
    }

    /**
     * Collect methods annotated with {@link ReactiveWsMethod}
     */
    @Override
    public void collect() {
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(ReactiveWsController.class);
        beans.forEach((beanName, bean) -> extractMethods(bean));
    }

    /**
     *
     * @param bean
     * @param declaredMethod
     */
    @Override
    public void checkMethodAndAddContext(Object bean, Method declaredMethod) {
        if (declaredMethod.isAnnotationPresent(ReactiveWsMethod.class)) {
            createWsMethodAndAddContext(bean, declaredMethod);
        }
    }
}
