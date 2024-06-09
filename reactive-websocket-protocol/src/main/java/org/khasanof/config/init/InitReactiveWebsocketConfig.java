package org.khasanof.config.init;

import lombok.SneakyThrows;
import org.khasanof.config.DefaultReactiveWebsocketConfigurer;
import org.khasanof.config.ReactiveWebsocketConfigReader;
import org.khasanof.config.ReactiveWebsocketConfigurer;
import org.khasanof.config.ReactiveWebsocketConfigurerAdapter;
import org.khasanof.factories.ReactiveHandlerMappingFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;

import java.util.Map;

import static org.khasanof.constants.GlobalConstants.HANDLER_MAPPING;

/**
 * @author Nurislom
 * @see org.khasanof.config.init
 * @since 6/9/2024 5:02 AM
 */
//@Component
public class InitReactiveWebsocketConfig implements BeanDefinitionRegistryPostProcessor {

    private final ApplicationContext applicationContext;
    private final ReactiveHandlerMappingFactory reactiveHandlerMappingFactory;

    public InitReactiveWebsocketConfig(ApplicationContext applicationContext, ReactiveHandlerMappingFactory reactiveHandlerMappingFactory) {
        this.applicationContext = applicationContext;
        this.reactiveHandlerMappingFactory = reactiveHandlerMappingFactory;
    }

    @SneakyThrows
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        Map<String, ReactiveWebsocketConfigurerAdapter> beans = getReactiveWebsocketConfigurerAdapters();

        validateConfigurerAdapters(beans);
        ReactiveWebsocketConfigurerAdapter configurerAdapter = getWebsocketConfigurerAdapter(beans);

        ReactiveWebsocketConfigurer configurer = new DefaultReactiveWebsocketConfigurer();
        configurerAdapter.configure(configurer);

        ReactiveWebsocketConfigReader websocketConfigReader = (ReactiveWebsocketConfigReader) configurer;
        Map<String, Object> urlMap = createUrlMap(websocketConfigReader);

        createAndRegisterHandlerMapping(urlMap, registry);
    }

    private Map<String, ReactiveWebsocketConfigurerAdapter> getReactiveWebsocketConfigurerAdapters() {
        return applicationContext.getBeansOfType(ReactiveWebsocketConfigurerAdapter.class);
    }

    private void validateConfigurerAdapters(Map<String, ReactiveWebsocketConfigurerAdapter> beans) {
        if (beans.isEmpty()) {
            throw new RuntimeException("ReactiveWebsocketConfigurerAdapter must be one implement");
        }
        if (beans.size() > 1) {
            throw new RuntimeException("ReactiveWebsocketConfigurerAdapter must be one implement");
        }
    }

    private ReactiveWebsocketConfigurerAdapter getWebsocketConfigurerAdapter(Map<String, ReactiveWebsocketConfigurerAdapter> beans) {
        return beans.values().iterator().next();
    }

    private Map<String, Object> createUrlMap(ReactiveWebsocketConfigReader websocketConfigReader) {
        return this.reactiveHandlerMappingFactory.create(websocketConfigReader);
    }

    private void createAndRegisterHandlerMapping(Map<String, Object> urlMap, BeanDefinitionRegistry registry) {
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();

        beanDefinition.setBeanClass(SimpleUrlHandlerMapping.class);
        beanDefinition.getPropertyValues().addPropertyValue("urlMap", urlMap);

        if (registry.containsBeanDefinition(HANDLER_MAPPING)) {
            registry.removeBeanDefinition(HANDLER_MAPPING);
        }
        registry.registerBeanDefinition(HANDLER_MAPPING, beanDefinition);
    }

    private BeanDefinitionRegistry getRegistry() {
        return (BeanDefinitionRegistry) applicationContext.getAutowireCapableBeanFactory();
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    }
}
