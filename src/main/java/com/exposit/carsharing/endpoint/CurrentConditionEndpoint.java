package com.exposit.carsharing.endpoint;

import com.exposit.carsharing.model.CurrentCondition;
import com.exposit.carsharing.service.CurrentConditionService;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("/current-condition")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class CurrentConditionEndpoint {

    private final CurrentConditionService currentConditionService;

    public CurrentConditionEndpoint(CurrentConditionService currentConditionService) {
        this.currentConditionService = currentConditionService;
    }

    @POST
    @Path("{id}")
    public Response createCurrentCondition(@PathParam("id") Long carId, CurrentCondition currentCondition) {
        currentConditionService.createCurrentCondition(currentCondition, carId);
        return Response.status(201).entity(currentCondition).build();
    }

    @GET
    public Response getAllCurrentConditions() {
        List<CurrentCondition> currentConditions = currentConditionService.getAllCurrentConditions();
        return Response.status(200).entity(currentConditions).build();
    }

    @GET
    @Path("{id}")
    public Response getCurrentCondition(@PathParam("id") Long id) {
        CurrentCondition currentCondition = currentConditionService.getCurrentCondition(id);
        return Response.status(200).entity(currentCondition).build();
    }
}
