package com.exposit.carsharing.endpoint;

import com.exposit.carsharing.model.CurrentCondition;
import com.exposit.carsharing.service.CurrentConditionService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Sergei on 10/12/2017.
 */

@Controller
@Path("/")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class CurrentConditionController {

    private final CurrentConditionService currentConditionService;

    public CurrentConditionController(CurrentConditionService currentConditionService) {
        this.currentConditionService = currentConditionService;
    }

    @POST
    public Response createCurrentCondition(CurrentCondition currentCondition){
        currentConditionService.populate();
        return Response.status(201).entity(currentCondition).build();
    }

    @GET
    public Response getAllParametrs(){
        List<CurrentCondition> currentConditions = currentConditionService.getAll();
        return Response.status(200).entity(currentConditions).build();
    }
}
