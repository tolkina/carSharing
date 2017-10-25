package com.exposit.carsharing.endpoint;

import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.domain.CurrentCondition;
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
    public Response createCurrentCondition(@PathParam("id") Long carId, CurrentCondition currentCondition) throws EntityNotFoundException, EntityAlreadyExistException {
        currentConditionService.create(currentCondition, carId);
        return Response.status(201).entity(currentCondition).build();
    }

    @GET
    public Response getAllCurrentConditions() {
        List<CurrentCondition> currentConditions = currentConditionService.getAll();
        return Response.status(200).entity(currentConditions).build();
    }

    @GET
    @Path("{id}")
    public Response getCurrentCondition(@PathParam("id") Long id) throws EntityNotFoundException {
        CurrentCondition currentCondition = currentConditionService.get(id);
        return Response.status(200).entity(currentCondition).build();
    }

    @DELETE
    @Path("{current_condition_id}/{car_id}")
    public Response deleteCurrentCondition(@PathParam("current_condition_id") Long currentConditionId, @PathParam("car_id") Long carId) throws PrivilegeException, EntityNotFoundException {
        currentConditionService.delete(currentConditionId, carId);
        return Response.status(200).build();
    }
}
