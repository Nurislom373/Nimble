package org.khasanof.service;

import org.springframework.stereotype.Component;

import static org.khasanof.constants.DefaultWsMethodDefinitions.isMatch;

/**
 * @author Nurislom
 * @see org.khasanof.service
 * @since 6/22/2024 7:14 PM
 */
@Component
public class DefaultWsMethodDefinitionService implements WsMethodDefinitionService {

    /**
     *
     * @param methodName
     * @return
     */
    @Override
    public boolean isDefaultMethod(String methodName) {
        return isMatch(methodName);
    }
}
