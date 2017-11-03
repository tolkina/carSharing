package com.exposit.carsharing.endpoint;

import com.exposit.carsharing.dto.PassportDataRequest;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.UnauthorizedException;
import com.exposit.carsharing.service.PassportDataService;
import com.exposit.carsharing.service.SecurityService;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@Path("/passport-data")
public class PassportDataEndpoint {
    private final PassportDataService passportDataService;
    private final SecurityService securityService;

    public PassportDataEndpoint(PassportDataService passportDataService, SecurityService securityService) {
        this.passportDataService = passportDataService;
        this.securityService = securityService;
    }

    @GET
    public Response retrievePassportData() throws EntityNotFoundException, UnauthorizedException {
        Long ownerId = securityService.getPrincipalId();
        return Response.status(200).entity(passportDataService.getPassportDataResponse(ownerId)).build();
    }

    @PUT
    public Response updatePassportData(@Valid PassportDataRequest passportDataRequest)
            throws EntityNotFoundException, UnauthorizedException {
        Long ownerId = securityService.getPrincipalId();
        return Response.status(200).entity(passportDataService.updatePassportData(ownerId, passportDataRequest)).build();
    }

    //    Need to delete this method
    @GET
    @Path("/all")
    public Response retrieveAll() throws EntityNotFoundException, UnauthorizedException {
        return Response.status(200).entity(passportDataService.getAll()).build();
    }
}
