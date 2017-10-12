package com.exposit.carsharing.endpoint;

import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.model.DriverLicense;
import com.exposit.carsharing.service.DriverLicenseService;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Component
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@Path("/driver-license")
public class DriverLicenseEndpoint {
    private final DriverLicenseService driverLicenseService;

    public DriverLicenseEndpoint(DriverLicenseService driverLicenseService) {
        this.driverLicenseService = driverLicenseService;
    }

    @POST
    @Path("{id}")
    public Response createDriverLicense(@PathParam("id") Long ownerId, DriverLicense driverLicense) throws EntityNotFoundException {
        driverLicenseService.createDriverLicense(driverLicense, ownerId);
        return Response.status(201).entity(driverLicense).build();
    }

    @GET
    public Response retrieveAllDriverLicenses() {
        Collection<DriverLicense> driverLicenses = driverLicenseService.getAllDriverLicenses();
        return Response.status(200).entity(driverLicenses).build();
    }

    @GET
    @Path("{id}")
    public Response retrieveDriverLicense(@PathParam("id") Long id) {
        DriverLicense driverLicense = driverLicenseService.getDriverLicense(id);
        return Response.status(200).entity(driverLicense).build();
    }
}
