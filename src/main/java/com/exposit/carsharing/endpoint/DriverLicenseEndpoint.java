package com.exposit.carsharing.endpoint;

import com.exposit.carsharing.dto.DriverLicenseRequest;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.UnauthorizedException;
import com.exposit.carsharing.service.DriverLicenseService;
import com.exposit.carsharing.service.SecurityService;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@Path("/driver-license")
public class DriverLicenseEndpoint {
    private final DriverLicenseService driverLicenseService;
    private final SecurityService securityService;

    public DriverLicenseEndpoint(DriverLicenseService driverLicenseService, SecurityService securityService) {
        this.driverLicenseService = driverLicenseService;
        this.securityService = securityService;
    }

    @GET
    public Response retrieveDriverLicense() throws EntityNotFoundException, UnauthorizedException {
        Long ownerId = securityService.getPrincipalId();
        return Response.status(200).entity(driverLicenseService.getDriverLicenseResponse(ownerId)).build();
    }

    @PUT
    public Response updateDriverLicense(@Valid DriverLicenseRequest driverLicenseRequest)
            throws EntityNotFoundException, UnauthorizedException {
        Long ownerId = securityService.getPrincipalId();
        return Response.status(200).entity(driverLicenseService.updateDriverLicense(ownerId, driverLicenseRequest)).build();
    }

    //    Need to delete this method
    @GET
    @Path("/all")
    public Response retrieveAll() throws EntityNotFoundException, UnauthorizedException {
        return Response.status(200).entity(driverLicenseService.getAll()).build();
    }
}