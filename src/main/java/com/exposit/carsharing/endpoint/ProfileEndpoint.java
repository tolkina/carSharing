package com.exposit.carsharing.endpoint;

import com.exposit.carsharing.dto.ProfileRequest;
import com.exposit.carsharing.exception.ConfirmProfileException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.UnauthorizedException;
import com.exposit.carsharing.service.AdService;
import com.exposit.carsharing.service.CarService;
import com.exposit.carsharing.service.ProfileService;
import com.exposit.carsharing.service.SecurityService;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@Path("/profile/")
public class ProfileEndpoint {
    private final ProfileService profileService;
    private final CarService carService;
    private final SecurityService securityService;
    private final AdService adService;

    public ProfileEndpoint(ProfileService profileService, CarService carService, SecurityService securityService,
                           AdService adService) {
        this.profileService = profileService;
        this.carService = carService;
        this.securityService = securityService;
        this.adService = adService;
    }

    @GET
    @Path("all")
    public Response getAllProfiles() {
        return Response.status(200).entity(profileService.getAll()).build();
    }

    @GET
    @Path("principal")
    public Response getPrincipal() throws UnauthorizedException {
        return Response.status(200).entity(securityService.getPrincipal()).build();
    }

    @GET
    @Path("{id}")
    public Response getProfileById(@PathParam("id") Long id) throws EntityNotFoundException {
        return Response.status(200).entity(profileService.getProfileResponse(id)).build();
    }

    @GET
    public Response getProfile() throws EntityNotFoundException, UnauthorizedException {
        Long ownerId = securityService.getPrincipalId();
        return Response.status(200).entity(profileService.getProfileResponse(ownerId)).build();
    }

    @PUT
    public Response updateProfile(@Valid ProfileRequest profileRequest)
            throws EntityNotFoundException, UnauthorizedException {
        Long ownerId = securityService.getPrincipalId();
        return Response.status(200).entity(profileService.updateProfile(ownerId, profileRequest)).build();
    }

    @PUT
    @Path("check-to-confirm")
    public Response checkToConfirmProfile() throws EntityNotFoundException, UnauthorizedException, ConfirmProfileException {
        Long profileId = securityService.getPrincipalId();
        profileService.setConfirmProfileCheck(profileId);
        return Response.status(200).build();
    }

    @DELETE
    public Response deleteProfile() throws EntityNotFoundException, UnauthorizedException {
        Long ownerId = securityService.getPrincipalId();
        profileService.delete(ownerId);
        return Response.status(200).build();
    }

    @GET
    @Path("car")
    public Response getAllCarsForPrincipal() throws UnauthorizedException, EntityNotFoundException {
        Long ownerId = securityService.getPrincipalId();
        return Response.status(200).entity(carService.getAllByOwner(ownerId)).build();
    }
    @GET
    @Path("car-without-ad")
    public Response getAllCarsWithoutAdForPrincipal() throws UnauthorizedException, EntityNotFoundException {
        Long ownerId = securityService.getPrincipalId();
        return Response.status(200).entity(carService.getAllWithoutAdByOwner(ownerId)).build();
    }

    @GET
    @Path("ad")
    public Response getAllAdsForPrincipal() throws EntityNotFoundException, UnauthorizedException {
        Long ownerId = securityService.getPrincipalId();
        return Response.status(200).entity(adService.getAllByOwner(ownerId)).build();
    }
}