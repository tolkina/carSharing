package com.exposit.carsharing.config;

import com.exposit.carsharing.endpoint.CurrentConditionController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Sergei on 10/12/2017.
 */

@Configuration
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(CurrentConditionController.class);

    }
}
