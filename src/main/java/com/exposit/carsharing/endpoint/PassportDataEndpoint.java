package com.exposit.carsharing.endpoint;

import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.model.PassportData;
import com.exposit.carsharing.service.PassportDataService;
import org.springframework.stereotype.Component;

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
    public Response createPassportData(@PathParam("id") Long ownerId, PassportData passportData) throws EntityNotFoundException {
        passportDataService.createPassportData(passportData, ownerId);
        return Response.status(201).entity(passportData).build();
    }

    @GET
    public Response retrieveAllPassportData() {
        Collection<PassportData> passportData = passportDataService.getAllPassportData();
        return Response.status(200).entity(passportData).build();
    }

    @GET
    @Path("{id}")
    public Response retrievePassportData(@PathParam("id") Long id) {
        PassportData passportData = passportDataService.getPassportData(id);
        return Response.status(200).entity(passportData).build();
    }
}
