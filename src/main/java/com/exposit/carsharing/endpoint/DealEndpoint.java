package com.exposit.carsharing.endpoint;

import com.exposit.carsharing.dto.DealRequest;
import com.exposit.carsharing.exception.DealException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.exception.UnauthorizedException;
import com.exposit.carsharing.service.DealService;
import com.exposit.carsharing.service.SecurityService;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@Path("/deal")
public class DealEndpoint {
    private final DealService dealService;
    private final SecurityService securityService;

    public DealEndpoint(DealService dealService, SecurityService securityService) {
        this.dealService = dealService;
        this.securityService = securityService;
    }

    @POST
    public Response createDeal(@Valid DealRequest dealRequest)
            throws EntityNotFoundException, UnauthorizedException, DealException {
        Long customerId = securityService.getPrincipalId();
        return Response.status(201).entity(dealService.create(dealRequest, customerId)).build();
    }

    @GET
    @Path("/my")
    public Response getAllMyDeals(@DefaultValue("1") @QueryParam(value = "page") Integer page,
                                  @DefaultValue("4") @QueryParam(value = "size") Integer size,
                                  @DefaultValue("status") @QueryParam(value = "sort") String sort,
                                  @DefaultValue("asc") @QueryParam(value = "direction") String direction)
            throws EntityNotFoundException, UnauthorizedException {
        Long customerId = securityService.getPrincipalId();
        return Response.status(200).entity(dealService.getAllByCustomer(customerId, page, size, sort, direction))
                .build();
    }

    @GET
    @Path("/by-me")
    public Response getAllDealsWithMe(@DefaultValue("1") @QueryParam(value = "page") Integer page,
                                      @DefaultValue("4") @QueryParam(value = "size") Integer size,
                                      @DefaultValue("status") @QueryParam(value = "sort") String sort,
                                      @DefaultValue("asc") @QueryParam(value = "direction") String direction)
            throws EntityNotFoundException, UnauthorizedException {
        Long ownerId = securityService.getPrincipalId();
        return Response.status(200).entity(dealService.getAllByOwner(ownerId, page, size, sort, direction)).build();
    }

    @GET
    @Path("{id}")
    public Response getDeal(@PathParam("id") Long dealId)
            throws EntityNotFoundException, UnauthorizedException, PrivilegeException {
        Long principalId = securityService.getPrincipalId();
        return Response.status(200).entity(dealService.get(dealId, principalId)).build();
    }

    @PUT
    @Path("{id}/start-rental")
    public Response startRental(@PathParam("id") Long dealId)
            throws UnauthorizedException, EntityNotFoundException, PrivilegeException, DealException {
        Long principalId = securityService.getPrincipalId();
        return Response.status(200).entity(dealService.startRental(dealId, principalId)).build();
    }

    @PUT
    @Path("/{id}/stop-rental")
    public Response stopRental(@PathParam("id") Long dealId)
            throws UnauthorizedException, EntityNotFoundException, PrivilegeException, DealException {
        Long principalId = securityService.getPrincipalId();
        return Response.status(200).entity(dealService.stopRental(dealId, principalId)).build();
    }

    @PUT
    @Path("{id}/cancel-booking")
    public Response cancelBooking(@PathParam("id") Long dealId)
            throws UnauthorizedException, EntityNotFoundException, PrivilegeException, DealException {
        Long principalId = securityService.getPrincipalId();
        return Response.status(200).entity(dealService.cancelBooking(dealId, principalId)).build();
    }
}
