package com.exposit.carsharing.endpoint;

import com.exposit.carsharing.domain.Ad;
import com.exposit.carsharing.dto.AdRequest;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.service.AdService;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
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
    public Response createAd(@PathParam("ownerId") Long ownerId, @PathParam("carId") Long carId, @Valid AdRequest adRequest) throws EntityNotFoundException, EntityAlreadyExistException {
        return Response.status(201).entity(adService.createAd(adRequest, ownerId, carId)).build();
    }

    @GET
    public Response getAllAds() {
        return Response.status(200).entity(adService.getAll()).build();
    }

    @GET
    @Path("{ownerId}")
    public Response getAllAdByOwner(@PathParam("ownerId") Long ownerId) throws EntityNotFoundException {
        return Response.status(200).entity(adService.getAllAdByOwner(ownerId)).build();
    }


    @GET
    @Path("ad-{id}")
    public Response getAd(@PathParam("id") Long id) throws EntityNotFoundException {
        return Response.status(200).entity(adService.getAdResponse(id)).build();
    }

    @DELETE
    @Path("{ad_id}")
    public Response deleteAd(@PathParam("ad_id") Long adId) throws PrivilegeException, EntityNotFoundException {
        adService.delete(adId);
        return Response.status(200).build();
    }
}
