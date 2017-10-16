package com.exposit.carsharing.endpoint;

import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
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
    public Response createCreditCard(@PathParam("id") Long ownerId, CreditCard creditCard) throws EntityNotFoundException, EntityAlreadyExistException {
        creditCardService.create(creditCard, ownerId);
        return Response.status(201).entity(creditCard).build();
    }

    @GET
    public Response retrieveAllCreditCards() {
        Collection<CreditCard> creditCards = creditCardService.getAll();
        return Response.status(200).entity(creditCards).build();
    }

    @GET
    @Path("{id}")
    public Response retrieveCreditCard(@PathParam("id") Long id) throws EntityNotFoundException {
        CreditCard creditCard = creditCardService.get(id);
        return Response.status(200).entity(creditCard).build();
    }

    @DELETE
    @Path("{credit_card_id}/{owner_id}")
    public Response deleteCreditCard(@PathParam("credit_card_id") Long creditCarId, @PathParam("owner_id") Long ownerId) throws PrivilegeException, EntityNotFoundException {
        creditCardService.delete(creditCarId, ownerId);
        return Response.status(200).build();
    }
}
