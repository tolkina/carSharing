package com.exposit.carsharing.configuration;

import com.exposit.carsharing.endpoint.CreditCardEndpoint;
import com.exposit.carsharing.endpoint.DriverLicenseEndpoint;
import com.exposit.carsharing.endpoint.PassportDataEndpoint;
import com.exposit.carsharing.endpoint.ProfileEndpoint;
import com.exposit.carsharing.exception.EntityAlreadyExistExceptionMapper;
import com.exposit.carsharing.exception.EntityNotFoundExceptionMapper;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
@Configuration
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(CreditCardEndpoint.class);
        register(DriverLicenseEndpoint.class);
        register(PassportDataEndpoint.class);
        register(ProfileEndpoint.class);
        register(EntityNotFoundExceptionMapper.class);
        register(EntityAlreadyExistExceptionMapper.class);
    }
}
