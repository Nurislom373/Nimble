package org.khasanof.sample;

import org.khasanof.config.ReactiveWebsocketConfigurer;
import org.khasanof.config.ReactiveWebsocketConfigurerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReactiveWebsocketProtocolSampleApplication implements ReactiveWebsocketConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveWebsocketProtocolSampleApplication.class, args);
    }

    @Override
    public void configure(ReactiveWebsocketConfigurer configurer) throws Exception {
        configurer.endpoint("/reactive");
    }
}
