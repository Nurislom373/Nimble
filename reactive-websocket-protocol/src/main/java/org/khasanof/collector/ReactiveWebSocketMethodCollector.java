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
public class ReactiveWebSocketMethodCollector implements Collector {

    private final ApplicationContext applicationContext;
    private final WsMethodFactory wsMethodFactory;
    private final ReactiveWebSocketMethodContext reactiveWebSocketMethodContext;

    public ReactiveWebSocketMethodCollector(ApplicationContext applicationContext,
                                            WsMethodFactory wsMethodFactory,
                                            ReactiveWebSocketMethodContext reactiveWebSocketMethodContext) {

        this.applicationContext = applicationContext;
        this.wsMethodFactory = wsMethodFactory;
        this.reactiveWebSocketMethodContext = reactiveWebSocketMethodContext;
    }

    /**
     * Collect methods annotated with {@link ReactiveWsMethod}
     */
    @Override
    public void collect() {
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(ReactiveWsController.class);
        beans.forEach((beanName, bean) -> extractMethods(bean));
    }

    private void extractMethods(Object bean) {
        Class<?> beanClass = bean.getClass();
        Method[] declaredMethods = beanClass.getDeclaredMethods();
        iterateDeclaredMethods(bean, declaredMethods);
    }

    private void iterateDeclaredMethods(Object bean, Method[] declaredMethods) {
        for (Method declaredMethod : declaredMethods) {
            checkMethodAndAddContext(bean, declaredMethod);
        }
    }

    private void checkMethodAndAddContext(Object bean, Method declaredMethod) {
        if (declaredMethod.isAnnotationPresent(ReactiveWsMethod.class)) {
            WsMethod wsMethod = wsMethodFactory.create(bean, declaredMethod);
            reactiveWebSocketMethodContext.addMethod(wsMethod.getMethodName(), wsMethod);
        }
    }
}
