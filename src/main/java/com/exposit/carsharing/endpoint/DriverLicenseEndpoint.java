package com.exposit.carsharing.endpoint;

import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
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
    public Response createDriverLicense(@PathParam("id") Long ownerId, DriverLicense driverLicense) throws EntityNotFoundException, EntityAlreadyExistException {
        driverLicenseService.create(driverLicense, ownerId);
        return Response.status(201).entity(driverLicense).build();
    }

    @PUT
    @Path("{id}")
    public Response updateProfile(@PathParam("id") Long ownerId, DriverLicense driverLicense) throws EntityNotFoundException {
        driverLicenseService.updateDriverLicense(driverLicense, ownerId);
        return Response.ok().entity(driverLicense).build();
    }

    @GET
    public Response retrieveAllDriverLicenses() {
        Collection<DriverLicense> driverLicenses = driverLicenseService.getAll();
        return Response.status(200).entity(driverLicenses).build();
    }

    @GET
    @Path("{id}")
    public Response retrieveDriverLicense(@PathParam("id") Long id) throws EntityNotFoundException {
        DriverLicense driverLicense = driverLicenseService.get(id);
        return Response.status(200).entity(driverLicense).build();
    }

    @DELETE
    @Path("{driver_license_id}/{owner_id}")
    public Response deleteDriverLicense(@PathParam("driver_license_id") Long driverLicenseId, @PathParam("owner_id") Long ownerId) throws PrivilegeException, EntityNotFoundException {
        driverLicenseService.delete(driverLicenseId, ownerId);
        return Response.status(200).build();
    }
}
