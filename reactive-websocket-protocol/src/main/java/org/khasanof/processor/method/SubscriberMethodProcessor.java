package org.khasanof.processor.method;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.khasanof.context.subscriber.WsSubscriberContext;
import org.khasanof.model.method.Subscriber;
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
@Component
public class SubscriberMethodProcessor implements WsMethodProcessor {

    private final ObjectMapper objectMapper;
    private final WsSubscriberContext wsSubscriberContext;

    public SubscriberMethodProcessor(ObjectMapper objectMapper, WsSubscriberContext wsSubscriberContext) {
        this.objectMapper = objectMapper;
        this.wsSubscriberContext = wsSubscriberContext;
    }

    @Override
    public Mono<Void> process(Mono<WsRequestSession> request) {
        return request.doOnNext(wsRequest -> {
                    SubscriberRequest subscriberRequest = dataConvertToSubscriberRequest(wsRequest.getData());
                    if (wsSubscriberContext.existSubscriber(wsRequest.getSession().getId())) {
                        wsSubscriberContext.removeSubscriber(wsRequest.getSession().getId());
                    }
                    wsSubscriberContext.addSubscriber(new Subscriber(subscriberRequest.getSessionId(), wsRequest.getSession().getId()));
                }).then();
    }

    private SubscriberRequest dataConvertToSubscriberRequest(Object data) {
        return objectMapper.convertValue(data, SubscriberRequest.class);
    }

    @Override
    public String getName() {
        return SUBSCRIBE.getName();
    }
}
