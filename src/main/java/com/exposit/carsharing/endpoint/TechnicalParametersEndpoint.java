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
@Path("/technical-parameters")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class TechnicalParametersEndpoint {
    private final TechnicalParametersService technicalParametersService;

    public TechnicalParametersEndpoint(TechnicalParametersService technicalParametersService) {
        this.technicalParametersService = technicalParametersService;
    }

    @POST
    @Path("{id}")
    public Response createTechnicalParameters(@PathParam("id") Long carId, TechnicalParametersRequest technicalParameters)
            throws EntityNotFoundException, EntityAlreadyExistException {
        return Response.status(201).entity(technicalParametersService.create(technicalParameters, carId)).build();
    }

    @GET
    public Response getAllTechnicalParameters() {
        return Response.status(200).entity(technicalParametersService.getAll()).build();
    }

    @GET
    @Path("{id}")
    public Response getTechnicalParameters(@PathParam("id") Long id) throws EntityNotFoundException {
        return Response.status(200).entity(technicalParametersService.get(id)).build();
    }

    @DELETE
    @Path("{technical_parameters_id}/{car_id}")
    public Response deleteAd(@PathParam("technical_parameters_id") Long technicalParameterId, @PathParam("car_id") Long carId) throws PrivilegeException, EntityNotFoundException {
        technicalParametersService.delete(technicalParameterId, carId);
        return Response.status(200).build();
    }
}
