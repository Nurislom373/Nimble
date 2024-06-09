package org.khasanof.collector;

import org.khasanof.annotation.MessageController;
import org.khasanof.annotation.MessageMapping;
import org.khasanof.context.method.ReactiveWebSocketMethodContext;
import org.khasanof.factories.WsProtocolMethodFactory;
import org.khasanof.model.method.WsProtocolMethod;
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
    private final WsProtocolMethodFactory wsProtocolMethodFactory;
    private final ReactiveWebSocketMethodContext reactiveWebSocketMethodContext;

    public ReactiveWebSocketMethodCollector(ApplicationContext applicationContext,
                                            WsProtocolMethodFactory wsProtocolMethodFactory,
                                            ReactiveWebSocketMethodContext reactiveWebSocketMethodContext) {

        this.applicationContext = applicationContext;
        this.wsProtocolMethodFactory = wsProtocolMethodFactory;
        this.reactiveWebSocketMethodContext = reactiveWebSocketMethodContext;
    }

    @Override
    public void collect() {
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(MessageController.class);
        beans.forEach((beanName, bean) -> extractMethods(bean));
    }

    private void extractMethods(Object bean) {
        Class<?> beanClass = bean.getClass();
        Method[] declaredMethods = beanClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            if (declaredMethod.isAnnotationPresent(MessageMapping.class)) {
                WsProtocolMethod wsProtocolMethod = wsProtocolMethodFactory.create(bean, declaredMethod);
                reactiveWebSocketMethodContext.addMethod(declaredMethod.getName(), wsProtocolMethod);
            }
        }
    }
}
