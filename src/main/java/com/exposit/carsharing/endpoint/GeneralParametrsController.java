package com.exposit.carsharing.endpoint;

import com.exposit.carsharing.model.GeneralParameters;
import com.exposit.carsharing.service.GeneralParametrsService;
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
public class GeneralParametrsController {
    private final GeneralParametrsService generalParametrsService;

    public GeneralParametrsController(GeneralParametrsService generalParametrsService) {
        this.generalParametrsService = generalParametrsService;
    }

    @POST
    public Response createGeneralParametrs(GeneralParameters generalParameters){
        generalParametrsService.populate();
        return Response.status(201).entity(generalParameters).build();
    }

    @GET
    public Response getAllParametrs() {
        List<GeneralParameters> generalParametrs = generalParametrsService.getAll();
        return Response.status(200).entity(generalParametrs).build();
    }
}
