package org.khasanof.config;

/**
 * @author Nurislom
 * @see org.khasanof.config
 * @since 6/8/2024 10:42 PM
 */
public interface ReactiveWebsocketConfigurerAdapter {

    /**
     *
     * @param configurer
     * @throws Exception
     */
    void configure(ReactiveWebsocketConfigurer configurer) throws Exception;
}
