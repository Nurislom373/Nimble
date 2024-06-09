package org.khasanof.factories;

import org.khasanof.config.ReactiveWebsocketConfigReader;
import org.khasanof.handler.ReactiveWebSocketHandler;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.factories
 * @since 6/9/2024 5:22 AM
 */
@Component
public class DefaultReactiveHandlerMappingFactory implements ReactiveHandlerMappingFactory {

    private final ReactiveWebSocketHandler reactiveWebSocketHandler;

    public DefaultReactiveHandlerMappingFactory(ReactiveWebSocketHandler reactiveWebSocketHandler) {
        this.reactiveWebSocketHandler = reactiveWebSocketHandler;
    }

    /**
     * @param websocketConfigReader
     * @return
     */
    @Override
    public Map<String, Object> create(ReactiveWebsocketConfigReader websocketConfigReader) {
        Map<String, Object> webSocketHandlerMap = new HashMap<>();
        webSocketHandlerMap.put("/reactive", reactiveWebSocketHandler);
        return webSocketHandlerMap;
    }
}
