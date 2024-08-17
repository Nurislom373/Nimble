package org.khasanof.processor.method;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.khasanof.context.session.ReactiveWebsocketSessionContext;
import org.khasanof.model.method.SubscriberRequest;
import org.khasanof.model.ws.WsRequestSession;
import org.khasanof.processor.WsMethodProcessor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static org.khasanof.constants.DefaultWsMethodDefinitions.SUBSCRIBE;

/**
 * @author Nurislom
 * @see org.khasanof.processor.method
 * @since 6/22/2024 7:32 PM
 */
@Slf4j
@Component
public class SubscriberMethodProcessor implements WsMethodProcessor {

    private final ObjectMapper objectMapper;
    private final ReactiveWebsocketSessionContext reactiveWebsocketSessionContext;

    public SubscriberMethodProcessor(ObjectMapper objectMapper,
                                     ReactiveWebsocketSessionContext reactiveWebsocketSessionContext) {

        this.objectMapper = objectMapper;
        this.reactiveWebsocketSessionContext = reactiveWebsocketSessionContext;
    }

    /**
     *
     * @param request
     * @return
     */
    @Override
    public Mono<Void> process(Mono<WsRequestSession> request) {
        return request.doOnNext(wsRequest -> {
            SubscriberRequest subscriberRequest = dataConvertToSubscriberRequest(wsRequest.getData());
            if (reactiveWebsocketSessionContext.existSession(wsRequest.getSession().getId())) {
                reactiveWebsocketSessionContext.getSession(wsRequest.getSession().getId())
                                .ifPresent(webSocketSessionFacade -> {
                                    webSocketSessionFacade.setSubscribe(true);
                                    webSocketSessionFacade.setSessionId(subscriberRequest.getSessionId());
                                    // TODO send message
                                });
            }
        }).then();
    }

    private SubscriberRequest dataConvertToSubscriberRequest(Object data) {
        return objectMapper.convertValue(data, SubscriberRequest.class);
    }

    /**
     *
     * @return
     */
    @Override
    public String getName() {
        return SUBSCRIBE.getName();
    }
}
