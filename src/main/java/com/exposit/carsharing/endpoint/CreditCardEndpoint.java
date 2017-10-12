package com.exposit.carsharing.endpoint;

import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.model.CreditCard;
import com.exposit.carsharing.service.CreditCardService;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Component
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@Path("/credit-card")
public class CreditCardEndpoint {
    private final CreditCardService creditCardService;

    public CreditCardEndpoint(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @POST
    @Path("/{id}")
    public Response createCreditCard(@PathParam("id") Long ownerId, CreditCard creditCard) throws EntityNotFoundException {
        creditCardService.createCreditCard(creditCard, ownerId);
        return Response.status(201).entity(creditCard).build();
    }

    @GET
    public Response retrieveAllCreditCards() {
        Collection<CreditCard> creditCards = creditCardService.getAllCreditCards();
        return Response.status(200).entity(creditCards).build();
    }

    @GET
    @Path("{id}")
    public Response retrieveCreditCard(@PathParam("id") Long id) {
        CreditCard creditCard = creditCardService.getCreditCard(id);
        return Response.status(200).entity(creditCard).build();
    }
}
