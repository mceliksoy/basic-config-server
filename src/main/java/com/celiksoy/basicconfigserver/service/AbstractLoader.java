package com.celiksoy.basicconfigserver.service;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractLoader implements ConfigLoader {

    protected Map<String, String> propertyMap = new HashMap<>();

    @Scheduled(fixedDelayString = "${server.property.reload.interval:60000}")
    public void scheduleFixedDelayTask() {
        load();
    }

    public Map<String, String> getPropertyMap() {
        return propertyMap;
    }
}
