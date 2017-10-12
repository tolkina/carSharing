package com.exposit.carsharing.endpoint;

import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.model.Ad;
import com.exposit.carsharing.model.CreditCard;
import com.exposit.carsharing.service.AdService;
import com.exposit.carsharing.service.CreditCardService;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;

@Component
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@Path("/ad")
public class AdEndpoint {
    private final AdService adService;

    public AdEndpoint(AdService adService) {
        this.adService = adService;
    }

    @POST
    @Path("/{id}")
    public Response createAd(@PathParam("id") Long ownerId, Ad ad) throws EntityNotFoundException {
        adService.createAd(ad, ownerId);
        return Response.status(201).entity(ad).build();
    }

    @GET
    public Response getAllAds() {
        return Response.status(200).entity(adService.getAllAds()).build();
    }

    @GET
    @Path("{id}")
    public Response getAd(@PathParam("id") Long id) throws EntityNotFoundException {
        Ad ad = adService.getAd(id);
        return Response.status(200).entity(ad).build();
    }
}
