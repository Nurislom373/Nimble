package org.khasanof.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nurislom
 * @see org.khasanof.config
 * @since 6/9/2024 7:34 AM
 */
@Configuration
public class ObjectMapperConfiguration {

    /**
     *
     * @return
     */
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper() {{
            registerModule(new JavaTimeModule());
            registerModule(new Jdk8Module());
        }};
    }
}
