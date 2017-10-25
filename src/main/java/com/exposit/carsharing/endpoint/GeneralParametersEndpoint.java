package com.exposit.carsharing.endpoint;

import com.exposit.carsharing.dto.GeneralParametersRequest;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.service.GeneralParametersService;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/general-parameters")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class GeneralParametersEndpoint {
    private final GeneralParametersService generalParametersService;

    public GeneralParametersEndpoint(GeneralParametersService generalParametersService) {
        this.generalParametersService = generalParametersService;
    }

    @POST
    @Path("{id}")
    public Response createGeneralParameters(@PathParam("id") Long carId, GeneralParametersRequest generalParameters)
            throws EntityNotFoundException, EntityAlreadyExistException, PrivilegeException {
        return Response.status(201).entity(generalParametersService.create(generalParameters, carId)).build();
    }

    @GET
    public Response getAllGeneralParameters() {
        return Response.status(200).entity(generalParametersService.getAll()).build();
    }

    @GET
    @Path("{id}")
    public Response getGeneralParameters(@PathParam("id") Long id) throws EntityNotFoundException {
        return Response.status(200).entity(generalParametersService.get(id)).build();
    }

    @DELETE
    @Path("{general_parameters_id}/{car_id}")
    public Response deleteGeneralParameters(@PathParam("general_parameters_id") Long generalParametersId, @PathParam("car_id") Long carId) throws PrivilegeException, EntityNotFoundException {
        generalParametersService.delete(generalParametersId, carId);
        return Response.status(200).build();
    }
}
