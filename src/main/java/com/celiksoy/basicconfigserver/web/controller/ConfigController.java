package com.celiksoy.basicconfigserver.web.controller;

import com.celiksoy.basicconfigserver.service.PropertyManager;
import com.celiksoy.basicconfigserver.web.resource.ParameterResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {

    private final PropertyManager propertyManager;

    private final Environment environment;


    @Autowired
    public ConfigController(PropertyManager propertyManager, Environment environment) {
        this.propertyManager = propertyManager;
        this.environment = environment;
    }

    @RequestMapping(value = "/listAll", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ResponseEntity<ParameterResource> listAll() {
        ParameterResource res = new ParameterResource();
        res.setPropertyMap(propertyManager.getAllProperty());

        return ResponseEntity.ok().body(res);
    }

}
