package org.khasanof.service;

import org.khasanof.model.method.WsMethod;
import org.khasanof.model.ws.WsRequestSession;
import org.khasanof.processor.WsMethodProcessor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof.service
 * @since 6/22/2024 7:30 PM
 */
@Service
public class DefaultWsMethodService implements WsMethodService, InitializingBean {

    private final ApplicationContext applicationContext;
    private final Set<WsMethodProcessor> wsMethodProcessors = new LinkedHashSet<>();

    public DefaultWsMethodService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Mono<Void> execute(WsMethod wsMethod, Mono<WsRequestSession> request) {
        return Flux.fromIterable(wsMethodProcessors)
                .flatMap(wsMethodProcessor -> wsMethodProcessor.process(request))
                .then();
    }

    @Override
    public void afterPropertiesSet() {
        this.wsMethodProcessors.addAll(getWsMethodProcessors());
    }

    private Collection<WsMethodProcessor> getWsMethodProcessors() {
        return applicationContext.getBeansOfType(WsMethodProcessor.class)
                .values();
    }
}
