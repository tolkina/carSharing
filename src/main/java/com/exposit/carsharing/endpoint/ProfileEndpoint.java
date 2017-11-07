package com.exposit.carsharing.endpoint;

import com.exposit.carsharing.dto.ProfileRequest;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.service.CarService;
import com.exposit.carsharing.service.ProfileService;
import com.exposit.carsharing.service.SecurityService;
import com.exposit.carsharing.service.SecurityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@Path("/profile")
public class ProfileEndpoint {
    private final ProfileService profileService;
    private final CarService carService;
    private final SecurityService securityService;

    public ProfileEndpoint(ProfileService profileService, CarService carService, SecurityService securityService) {
        this.profileService = profileService;
        this.carService = carService;
        this.securityService = securityService;
    }

    @POST
    public Response createProfile(@Valid ProfileRequest profileRequest) throws EntityAlreadyExistException {
        return Response.status(201).entity(profileService.createProfile(profileRequest)).build();
    }

    @PUT
    @Path("{id}")
    public Response updateProfile(@PathParam("id") Long id, @Valid ProfileRequest profileRequest) throws EntityNotFoundException {
        return Response.status(200).entity(profileService.updateProfile(id, profileRequest)).build();
    }

    @GET
    public Response getAllProfiles() {
        return Response.status(200).entity(profileService.getAll()).build();
    }

    @GET
    @Path("{id}")
    public Response getProfile(@PathParam("id") Long id) throws EntityNotFoundException {
        return Response.status(200).entity(profileService.getProfileResponse(id)).build();
    }

    @DELETE
    @Path("{profile_id}")
    public Response deleteAd(@PathParam("profile_id") Long profileId) throws PrivilegeException, EntityNotFoundException {
        profileService.delete(profileId);
        return Response.status(200).build();
    }

    @GET
    @Path("/{owner_id}/car")
    public Response getAllCarsByOwner(@PathParam("owner_id") Long ownerId) throws EntityNotFoundException {
        return Response.status(200).entity(carService.getAllByOwner(ownerId)).build();
    }
}
