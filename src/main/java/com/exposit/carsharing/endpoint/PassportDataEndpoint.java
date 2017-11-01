package com.exposit.carsharing.endpoint;

import com.exposit.carsharing.dto.PassportDataRequest;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.domain.PassportData;
import com.exposit.carsharing.service.PassportDataService;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Component
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@Path("/passport-data")
public class PassportDataEndpoint {
    private final PassportDataService passportDataService;

    public PassportDataEndpoint(PassportDataService passportDataService) {
        this.passportDataService = passportDataService;
    }

    @POST
    @Path("{id}")
    public Response createPassportData(@PathParam("id") Long ownerId, @Valid PassportDataRequest passportDataRequest) throws EntityNotFoundException, EntityAlreadyExistException {
        return Response.status(201).entity(passportDataService.createPassport(ownerId, passportDataRequest)).build();
    }

    @PUT
    @Path("{id}")
    public Response updateProfile(@PathParam("id") Long ownerId, @Valid PassportDataRequest passportDataRequest) throws EntityNotFoundException {
        return Response.status(200).entity(passportDataService.updatePassport(ownerId, passportDataRequest)).build();
    }

    @GET
    public Response retrieveAllPassportData() {
        Collection<PassportData> passportData = passportDataService.getAll();
        return Response.status(200).entity(passportData).build();
    }

    @GET
    @Path("{id}")
    public Response retrievePassportData(@PathParam("id") Long id) throws EntityNotFoundException {
        return Response.status(200).entity(passportDataService.getPassportResponse(id)).build();
    }

    @DELETE
    @Path("{passport_data_id}/{owner_id}")
    public Response deletePassportData(@PathParam("passport_data_id") Long passportDataId, @PathParam("owner_id") Long ownerId) throws PrivilegeException, EntityNotFoundException {
        passportDataService.delete(passportDataId, ownerId);
        return Response.status(200).build();
    }
}
