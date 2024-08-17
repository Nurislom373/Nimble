package org.khasanof.collector;

import org.khasanof.context.method.ReactiveWebSocketMethodContext;
import org.khasanof.factories.method.WsMethodFactory;
import org.khasanof.model.method.WsMethod;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;

/**
 * @author Nurislom
 * @see org.khasanof.collector
 * @since 8/17/2024 5:28 PM
 */
public abstract class AbstractCollector implements Collector {

    protected final WsMethodFactory wsMethodFactory;
    protected final ApplicationContext applicationContext;
    protected final ReactiveWebSocketMethodContext reactiveWebSocketMethodContext;

    public AbstractCollector(WsMethodFactory wsMethodFactory,
                                ApplicationContext applicationContext,
                                ReactiveWebSocketMethodContext reactiveWebSocketMethodContext) {

        this.wsMethodFactory = wsMethodFactory;
        this.applicationContext = applicationContext;
        this.reactiveWebSocketMethodContext = reactiveWebSocketMethodContext;
    }

    /**
     *
     * @param bean
     */
    protected void extractMethods(Object bean) {
        Class<?> beanClass = bean.getClass();
        Method[] declaredMethods = beanClass.getDeclaredMethods();
        iterateDeclaredMethods(bean, declaredMethods);
    }

    /**
     *
     * @param bean
     * @param declaredMethods
     */
    protected void iterateDeclaredMethods(Object bean, Method[] declaredMethods) {
        for (Method declaredMethod : declaredMethods) {
            checkMethodAndAddContext(bean, declaredMethod);
        }
    }

    /**
     *
     * @param bean
     * @param declaredMethod
     */
    protected void createWsMethodAndAddContext(Object bean, Method declaredMethod) {
        createWsMethodAndAddContext(bean, declaredMethod, wsMethodFactory);
    }

    /**
     *
     * @param bean
     * @param declaredMethod
     * @param wsMethodFactory
     */
    protected void createWsMethodAndAddContext(Object bean, Method declaredMethod, WsMethodFactory wsMethodFactory) {
        WsMethod wsMethod = wsMethodFactory.create(bean, declaredMethod);
        reactiveWebSocketMethodContext.addMethod(wsMethod.getMethodName(), wsMethod);
    }

    /**
     *
     * @param bean
     * @param declaredMethod
     */
    protected abstract void checkMethodAndAddContext(Object bean, Method declaredMethod);
}
