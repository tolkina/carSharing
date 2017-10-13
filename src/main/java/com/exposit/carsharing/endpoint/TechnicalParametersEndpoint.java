package com.exposit.carsharing.endpoint;

import com.exposit.carsharing.model.TechnicalParameters;
import com.exposit.carsharing.service.TechnicalParametersService;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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
    public Response createTechnicalParameters(@PathParam("id") Long carId, TechnicalParameters technicalParameters) {
        technicalParametersService.createTechnicalParameters(technicalParameters, carId);
        return Response.status(201).entity(technicalParameters).build();
    }

    @GET
    public Response getAllTechnicalParameters() {
        List<TechnicalParameters> technicalParameters = technicalParametersService.getAllTechnicalParameters();
        return Response.status(200).entity(technicalParameters).build();
    }

    @GET
    @Path("{id}")
    public Response getTechnicalParameters(@PathParam("id") Long id) {
        TechnicalParameters technicalParameters = technicalParametersService.getTechnicalParameters(id);
        return Response.status(200).entity(technicalParameters).build();
    }
}
