package com.exposit.carsharing.endpoint;

import com.exposit.carsharing.model.GeneralParameters;
import com.exposit.carsharing.service.GeneralParametersService;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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
    public Response createGeneralParameters(@PathParam("id") Long carId, GeneralParameters generalParameters) {
        generalParametersService.createGeneralParameters(generalParameters, carId);
        return Response.status(201).entity(generalParameters).build();
    }

    @GET
    public Response getAllGeneralParameters() {
        List<GeneralParameters> generalParameters = generalParametersService.getAllGeneralParameters();
        return Response.status(200).entity(generalParameters).build();
    }

    @GET
    @Path("{id}")
    public Response getGeneralParameters(@PathParam("id") Long id) {
        GeneralParameters generalParameters = generalParametersService.getGeneralParameters(id);
        return Response.status(200).entity(generalParameters).build();
    }
}
