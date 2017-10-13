package com.exposit.carsharing.endpoint;

import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.model.Deal;
import com.exposit.carsharing.service.DealService;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@Path("/deal")
public class DealEndpoint {
    private final DealService dealService;

    public DealEndpoint(DealService dealService) {
        this.dealService = dealService;
    }

    @POST
    @Path("/{owner_id}/{customer_id}")
    public Response createDeal(@PathParam("owner_id") Long ownerId, @PathParam("customer_id") Long customerId, Deal deal) throws EntityNotFoundException {
        dealService.createDeal(deal, ownerId, customerId);
        return Response.status(201).entity(deal).build();
    }

    @GET
    public Response getAllDeals() {
        return Response.status(200).entity(dealService.getAllDeals()).build();
    }

    @GET
    @Path("/my/{id}")
    public Response getAllMyDeals(@PathParam("id") Long customerId) throws EntityNotFoundException {
        return Response.status(200).entity(dealService.getAllMyDeals(customerId)).build();
    }

    @GET
    @Path("/by-me/{id}")
    public Response getAllDealsWithMe(@PathParam("id") Long ownerId) throws EntityNotFoundException {
        return Response.status(200).entity(dealService.getAllDealsWithMe(ownerId)).build();
    }

    @GET
    @Path("{id}")
    public Response getDeal(@PathParam("id") Long id) throws EntityNotFoundException {
        Deal deal = dealService.getDeal(id);
        return Response.status(200).entity(deal).build();
    }
}
