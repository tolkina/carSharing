package com.exposit.carsharing.endpoint;

import com.exposit.carsharing.dto.CreditCardRequest;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.UnauthorizedException;
import com.exposit.carsharing.service.CreditCardService;
import com.exposit.carsharing.service.SecurityService;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@Path("/credit-card")
public class CreditCardEndpoint {
    private final CreditCardService creditCardService;
    private final SecurityService securityService;

    public CreditCardEndpoint(CreditCardService creditCardService, SecurityService securityService) {
        this.creditCardService = creditCardService;
        this.securityService = securityService;
    }

    @POST
    public Response createCreditCard(@Valid CreditCardRequest creditCardRequest)
            throws UnauthorizedException, EntityNotFoundException {
        Long ownerId = securityService.getPrincipalId();
        return Response.status(201).entity(creditCardService.create(creditCardRequest, ownerId)).build();
    }

    @GET
    public Response retrieveAllCreditCardsByOwner()
            throws UnauthorizedException, EntityNotFoundException {
        Long ownerId = securityService.getPrincipalId();
        return Response.status(200).entity(creditCardService.getAllByOwner(ownerId)).build();
    }

    @GET
    @Path("{credit_card_id}")
    public Response retrieveCreditCard(@PathParam("credit_card_id") Long creditCardId)
            throws EntityNotFoundException, UnauthorizedException {
        Long ownerId = securityService.getPrincipalId();
        return Response.status(200).entity(creditCardService.get(creditCardId, ownerId)).build();
    }

    @DELETE
    @Path("{credit_card_id}")
    public Response deleteCreditCard(@PathParam("credit_card_id") Long creditCarId)
            throws UnauthorizedException, EntityNotFoundException {
        Long ownerId = securityService.getPrincipalId();
        creditCardService.delete(creditCarId, ownerId);
        return Response.status(200).build();
    }
}
