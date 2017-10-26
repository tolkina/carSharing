package com.exposit.carsharing.endpoint;

import com.exposit.carsharing.dto.CurrentConditionRequest;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.service.CurrentConditionService;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/car")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class CurrentConditionEndpoint {

    private final CurrentConditionService currentConditionService;

    public CurrentConditionEndpoint(CurrentConditionService currentConditionService) {
        this.currentConditionService = currentConditionService;
    }

    @PUT
    @Path("/{car_id}/current-condition")
    public Response createCurrentCondition(@PathParam("car_id") Long carId, CurrentConditionRequest currentCondition)
            throws EntityNotFoundException, EntityAlreadyExistException, PrivilegeException {
        Long ownerId = 1L;
        return Response.status(200).entity(currentConditionService.update(currentCondition, carId, ownerId)).build();
    }

    @GET
    @Path("/current-condition")
    public Response getAllCurrentConditions() {
        return Response.status(200).entity(currentConditionService.getAll()).build();
    }

    @GET
    @Path("/{car_id}/current-condition")
    public Response getCurrentCondition(@PathParam("car_id") Long carId) throws EntityNotFoundException {
        return Response.status(200).entity(currentConditionService.get(carId)).build();
    }
}
