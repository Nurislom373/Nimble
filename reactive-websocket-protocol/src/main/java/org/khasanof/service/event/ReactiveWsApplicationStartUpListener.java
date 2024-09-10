package org.khasanof.service.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author Nurislom
 * @see org.khasanof.service.event
 * @since 9/9/2024 2:55 PM
 */
@Slf4j
@Component
public class ReactiveWsApplicationStartUpListener implements ApplicationListener<ApplicationReadyEvent> {

    /**
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
       log.debug("################ Reactive Websocket Protocol Ready! ################");
    }
}
