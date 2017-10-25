package com.exposit.carsharing.endpoint;

import com.exposit.carsharing.domain.Ad;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.service.AdService;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    @Path("/{ownerId}/{carId}")
    public Response createAd(@PathParam("ownerId") Long ownerId, @PathParam("carId") Long carId, Ad ad) throws EntityNotFoundException, EntityAlreadyExistException {
        adService.create(ad, ownerId, carId);
        return Response.status(201).entity(ad).build();
    }

    @GET
    public Response getAllAds() {
        return Response.status(200).entity(adService.getAll()).build();
    }

    @GET
    @Path("{id}")
    public Response getAd(@PathParam("id") Long id) throws EntityNotFoundException {
        Ad ad = adService.get(id);
        return Response.status(200).entity(ad).build();
    }

    @DELETE
    @Path("{ad_id}/{owner_id}")
    public Response deleteAd(@PathParam("ad_id") Long adId, @PathParam("owner_id") Long ownerId) throws PrivilegeException, EntityNotFoundException {
        adService.delete(adId, ownerId);
        return Response.status(200).build();
    }
}
