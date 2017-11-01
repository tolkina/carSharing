package com.exposit.carsharing.endpoint;

import com.exposit.carsharing.dto.DriverLicenseRequest;
import com.exposit.carsharing.dto.DriverLicenseResponse;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.domain.DriverLicense;
import com.exposit.carsharing.service.DriverLicenseService;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
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
    public Response createDriverLicense(@PathParam("id") Long ownerId, @Valid DriverLicenseRequest driverLicenseRequest) throws EntityNotFoundException, EntityAlreadyExistException {
        return Response.status(201).entity(driverLicenseService.createDriverLicense(ownerId, driverLicenseRequest)).build();
    }

    @PUT
    @Path("{id}")
    public Response updateProfile(@PathParam("id") Long ownerId, DriverLicenseResponse driverLicenseResponse) throws EntityNotFoundException {
        return Response.ok().entity(driverLicenseService.updateDriverLicense(ownerId, driverLicenseResponse)).build();
    }

    @GET
    public Response retrieveAllDriverLicenses() {
        Collection<DriverLicense> driverLicenses = driverLicenseService.getAll();
        return Response.status(200).entity(driverLicenses).build();
    }

    @GET
    @Path("{id}")
    public Response retrieveDriverLicense(@PathParam("id") Long id) throws EntityNotFoundException {
        return Response.status(200).entity(driverLicenseService.getDriverLicenseResponse(id)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteDriverLicense(@PathParam("id") Long id) throws PrivilegeException, EntityNotFoundException {
        driverLicenseService.delete(id);
        return Response.status(200).build();
    }
}
