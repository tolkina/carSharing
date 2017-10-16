package com.exposit.carsharing.configuration;

import com.exposit.carsharing.endpoint.*;
import com.exposit.carsharing.exception.EntityAlreadyExistExceptionMapper;
import com.exposit.carsharing.exception.EntityNotFoundExceptionMapper;
import com.exposit.carsharing.exception.PrivilegeExceptionMapper;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
@Configuration
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        // -------------------- Endpoints -------------
        register(CreditCardEndpoint.class);
        register(DriverLicenseEndpoint.class);
        register(PassportDataEndpoint.class);
        register(ProfileEndpoint.class);
        register(AdEndpoint.class);
        register(DealEndpoint.class);
        register(CurrentConditionEndpoint.class);
        register(CarEndpoint.class);
        register(GeneralParametersEndpoint.class);
        register(TechnicalParametersEndpoint.class);
        register(AdminEndpoint.class);
        // -------------------- Mappers ---------------
        register(EntityNotFoundExceptionMapper.class);
        register(EntityAlreadyExistExceptionMapper.class);
        register(PrivilegeExceptionMapper.class);
    }
}
