package org.khasanof.config.init;

import org.khasanof.collector.Collector;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author Nurislom
 * @see org.khasanof.config.init
 * @since 6/9/2024 7:10 AM
 */
@Component
public class InitCollectors implements InitializingBean {

    private final ApplicationContext applicationContext;

    public InitCollectors(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     *
     */
    @Override
    public void afterPropertiesSet() {
        applicationContext.getBeansOfType(Collector.class)
                .values()
                .forEach(Collector::collect);
    }
}
