package com.celiksoy.basicconfigserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PropertyManager {

    private final ConfigLoader configLoader;

    @Autowired
    public PropertyManager(ConfigLoader configLoader) {
        this.configLoader = configLoader;
    }

    public Map<String, String> getAllProperty() {
        return configLoader.getPropertyMap();
    }
}
