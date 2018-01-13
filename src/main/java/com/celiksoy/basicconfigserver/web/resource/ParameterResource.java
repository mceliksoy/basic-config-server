package com.celiksoy.basicconfigserver.web.resource;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ParameterResource implements Serializable {

    private Map<String, String> propertyMap = new HashMap<>();

    public Map<String, String> getPropertyMap() {
        return propertyMap;
    }

    public void setPropertyMap(Map<String, String> propertyMap) {
        this.propertyMap = propertyMap;
    }
}
