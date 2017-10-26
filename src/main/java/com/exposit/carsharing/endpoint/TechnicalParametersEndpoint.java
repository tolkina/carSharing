package com.exposit.carsharing.endpoint;

import com.exposit.carsharing.dto.TechnicalParametersRequest;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.service.TechnicalParametersService;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/car")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class TechnicalParametersEndpoint {
    private final TechnicalParametersService technicalParametersService;

    public TechnicalParametersEndpoint(TechnicalParametersService technicalParametersService) {
        this.technicalParametersService = technicalParametersService;
    }

    @PUT
    @Path("/{car_id}/technical-parameters")
    public Response updateTechnicalParameters(@PathParam("car_id") Long carId,
                                              TechnicalParametersRequest technicalParametersRequest)
            throws EntityNotFoundException, EntityAlreadyExistException, PrivilegeException {
        Long ownerId = 1L;
        return Response.status(200).entity(technicalParametersService.update(technicalParametersRequest, carId, ownerId)).build();
    }

    @GET
    @Path("/technical-parameters")
    public Response getAllTechnicalParameters() {
        return Response.status(200).entity(technicalParametersService.getAll()).build();
    }

    @GET
    @Path("/{car_id}/technical-parameters")
    public Response getTechnicalParameters(@PathParam("car_id") Long carId) throws EntityNotFoundException {
        return Response.status(200).entity(technicalParametersService.get(carId)).build();
    }
}
