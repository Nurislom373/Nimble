package org.khasanof.factories;

import org.khasanof.config.ReactiveWebsocketConfigReader;
import org.springframework.web.reactive.HandlerMapping;

import java.util.Map;

/**
 * @author Nurislom
 * @see org.khasanof.factories
 * @since 6/9/2024 5:22 AM
 */
public interface ReactiveHandlerMappingFactory {

    /**
     *
      * @param websocketConfigReader
     * @return
     */
    Map<String, Object> create(ReactiveWebsocketConfigReader websocketConfigReader);
}
