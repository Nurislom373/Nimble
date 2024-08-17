package org.khasanof.collector;

import org.khasanof.annotation.ReactiveWsController;
import org.khasanof.annotation.ReactiveWsMethod;
import org.khasanof.context.method.ReactiveWebSocketMethodContext;
import org.khasanof.factories.method.DefaultWsMethodFactory;
import org.khasanof.factories.method.WsMethodFactory;
import org.khasanof.model.method.WsMethod;
import org.khasanof.processor.WsMethodProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof.collector
 * @since 8/17/2024 5:21 PM
 */
@Component
public class DefaultReactiveWebSocketMethodCollector extends AbstractCollector {

    public static final String METHOD_NAME = "process";

    private final DefaultWsMethodFactory defaultWsMethodFactory;

    public DefaultReactiveWebSocketMethodCollector(WsMethodFactory wsMethodFactory,
                                                   ApplicationContext applicationContext,
                                                   ReactiveWebSocketMethodContext reactiveWebSocketMethodContext,
                                                   DefaultWsMethodFactory defaultWsMethodFactory) {

        super(wsMethodFactory, applicationContext, reactiveWebSocketMethodContext);
        this.defaultWsMethodFactory = defaultWsMethodFactory;
    }

    /**
     * Collect methods implement with {@link WsMethodProcessor}
     */
    @Override
    public void collect() {
        Map<String, WsMethodProcessor> beans = applicationContext.getBeansOfType(WsMethodProcessor.class);
        beans.forEach((beanName, bean) -> extractMethods(bean));
    }

    /**
     *
     * @param bean
     * @param declaredMethod
     */
    @Override
    protected void checkMethodAndAddContext(Object bean, Method declaredMethod) {
       if (Objects.equals(declaredMethod.getName(), METHOD_NAME) && Objects.equals(declaredMethod.getReturnType(), Mono.class)) {
           createWsMethodAndAddContext(bean, declaredMethod, defaultWsMethodFactory);
       }
    }
}
