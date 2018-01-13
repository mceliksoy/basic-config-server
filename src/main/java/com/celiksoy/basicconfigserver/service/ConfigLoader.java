package com.celiksoy.basicconfigserver.service;

import java.util.Map;

public interface ConfigLoader {
    void load();

    Map<String, String> getPropertyMap();
}
