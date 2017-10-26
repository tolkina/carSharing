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
@Path("/car")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class GeneralParametersEndpoint {
    private final GeneralParametersService generalParametersService;

    public GeneralParametersEndpoint(GeneralParametersService generalParametersService) {
        this.generalParametersService = generalParametersService;
    }

    @PUT
    @Path("/{car_id}/general-parameters")
    public Response createGeneralParameters(
            @PathParam("car_id") Long carId, GeneralParametersRequest generalParametersRequest)
            throws EntityNotFoundException, EntityAlreadyExistException, PrivilegeException {
        Long owner = 1L;
        return Response.status(200).entity(generalParametersService.update(generalParametersRequest, carId, owner)).build();
    }

    @GET
    @Path("/general-parameters")
    public Response getAllGeneralParameters() {
        return Response.status(200).entity(generalParametersService.getAll()).build();
    }

    @GET
    @Path("/{car_id}/general-parameters")
    public Response getGeneralParameters(@PathParam("car_id") Long carId) throws EntityNotFoundException {
        return Response.status(200).entity(generalParametersService.get(carId)).build();
    }
}
