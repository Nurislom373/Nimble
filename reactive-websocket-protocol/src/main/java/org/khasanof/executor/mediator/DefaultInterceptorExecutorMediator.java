package org.khasanof.executor.mediator;

import org.khasanof.executor.interceptor.ExecutorInterceptor;
import org.khasanof.model.method.WsMethod;
import org.khasanof.model.ws.WsRequestSession;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.executor.mediator
 * @since 6/22/2024 6:59 PM
 */
@Component
public class DefaultInterceptorExecutorMediator implements InterceptorExecutorMediator, InitializingBean {

    private final ApplicationContext applicationContext;
    private final Set<ExecutorInterceptor> executorInterceptors = new LinkedHashSet<>();

    public DefaultInterceptorExecutorMediator(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     *
     * @param wsMethod
     * @param request
     * @return
     */
    @Override
    public boolean preIntercept(WsMethod wsMethod, Mono<WsRequestSession> request) {
        return executorInterceptors.stream()
                .allMatch(executorInterceptor -> executorInterceptor.preIntercept(wsMethod, request));
    }

    /**
     *
     * @param wsMethod
     * @param request
     */
    @Override
    public void postIntercept(WsMethod wsMethod, Mono<WsRequestSession> request, Mono<Void> result) {
        executorInterceptors.forEach(executorInterceptor -> executorInterceptor.postIntercept(wsMethod, request, result));
    }

    /**
     *
     */
    @Override
    public void afterPropertiesSet() {
        executorInterceptors.addAll(getExecutorInterceptors());
    }

    private Collection<ExecutorInterceptor> getExecutorInterceptors() {
        return applicationContext.getBeansOfType(ExecutorInterceptor.class)
                .values();
    }
}
