package org.khasanof.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;

/**
 * @author Nurislom
 * @see org.khasanof.config
 * @since 6/9/2024 7:20 AM
 */
@Configuration
public class DataBufferFactoryConfiguration {

    /**
     *
     * @return
     */
    @Bean
    public DataBufferFactory dataBufferFactory() {
        return new DefaultDataBufferFactory();
    }
}
