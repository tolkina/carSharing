package com.exposit.carsharing.endpoint;

import com.exposit.carsharing.dto.AdRequest;
import com.exposit.carsharing.exception.*;
import com.exposit.carsharing.service.AdService;
import com.exposit.carsharing.service.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Slf4j
@Component
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@Path("/ad/")
public class AdEndpoint {
    private final AdService adService;
    private final SecurityService securityService;

    public AdEndpoint(AdService adService, SecurityService securityService) {
        this.adService = adService;
        this.securityService = securityService;
    }

    @POST
    @Path("{carId}")
    public Response createAd(@PathParam("carId") Long carId, @Valid AdRequest adRequest)
            throws EntityNotFoundException, EntityAlreadyExistException, UnauthorizedException, PrivilegeException {
        Long ownerId = securityService.getPrincipalId();
        log.debug("User with id {} create ad for car with id {}", ownerId, carId);
        return Response.status(201).entity(adService.create(adRequest, ownerId, carId)).build();
    }

    @PUT
    @Path("{adId}")
    public Response updateAd(@PathParam("adId") Long adId, @Valid AdRequest adRequest)
            throws EntityNotFoundException, UnauthorizedException, PrivilegeException, AdException {
        Long ownerId = securityService.getPrincipalId();
        log.debug("User with id {} update ad with id {}", ownerId, adId);
        return Response.status(200).entity(adService.update(ownerId, adId, adRequest)).build();
    }

    @GET
    public Response getAllAds() {
        log.debug("Retrieve all ads");
        return Response.status(200).entity(adService.getAll()).build();
    }

    @GET
    @Path("all-not-my")
    public Response getAllNotMyActualAds(@DefaultValue("1") @QueryParam(value = "page") Integer page,
                                         @DefaultValue("4") @QueryParam(value = "size") Integer size,
                                         @DefaultValue("status") @QueryParam(value = "sort") String sort,
                                         @DefaultValue("asc") @QueryParam(value = "direction") String direction)
            throws UnauthorizedException, EntityNotFoundException {
        Long principalId = securityService.getPrincipalId();
        log.debug("User with id {} get all not his actual ads. Page {}, size {}, sort {}, direction {}",
                principalId, page, size, sort, direction);
        return Response.status(200).entity(adService.getAllNotMyActual(principalId, page, size, sort, direction)).build();
    }

    @GET
    @Path("{adId}")
    public Response getAd(@PathParam("adId") Long id) throws EntityNotFoundException {
        log.debug("Retrieve ad with id {}", id);
        return Response.status(200).entity(adService.getAdResponse(id)).build();
    }

    @DELETE
    @Path("{adId}")
    public Response deleteAd(@PathParam("adId") Long adId)
            throws PrivilegeException, EntityNotFoundException, UnauthorizedException, AdException {
        Long ownerId = securityService.getPrincipalId();
        log.debug("User with id {} delete ad with id {}", ownerId, adId);
        adService.delete(ownerId, adId);
        return Response.status(200).build();
    }

    @PUT
    @Path("{adId}/actual")
    public Response setActual(@PathParam("adId") Long adId)
            throws UnauthorizedException, EntityNotFoundException, PrivilegeException, AdException {
        Long ownerId = securityService.getPrincipalId();
        log.debug("User with id {} set status Actual to ad with id {}", ownerId, adId);
        return Response.status(200).entity(adService.setActual(ownerId, adId)).build();
    }

    @PUT
    @Path("{adId}/not-actual")
    public Response setNotActual(@PathParam("adId") Long adId)
            throws UnauthorizedException, EntityNotFoundException, PrivilegeException, AdException {
        Long ownerId = securityService.getPrincipalId();
        log.debug("User with id {} set status NotActual to ad with id {}", ownerId, adId);
        return Response.status(200).entity(adService.setNotActual(ownerId, adId)).build();
    }
}
