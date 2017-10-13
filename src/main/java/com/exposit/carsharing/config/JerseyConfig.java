package com.exposit.carsharing.config;

import com.exposit.carsharing.endpoint.CarEndpoint;
import com.exposit.carsharing.endpoint.CurrentConditionEndpoint;
import com.exposit.carsharing.endpoint.GeneralParametersEndpoint;
import com.exposit.carsharing.endpoint.TechnicalParametersEndpoint;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
@Configuration
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(CurrentConditionEndpoint.class);
        register(CarEndpoint.class);
        register(GeneralParametersEndpoint.class);
        register(TechnicalParametersEndpoint.class);
    }
}
