package com.sam.limitservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitConfigurationController {

    @Autowired
    Configuration configuration;

    @GetMapping("/limits")
    public LimitConfiguration reteriveLimitConfiguration()
    {
        return new LimitConfiguration(configuration.getMinimum(),configuration.getMaximum());
    }
}
