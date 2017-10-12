package com.exposit.carsharing.endpoint;

import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.model.Profile;
import com.exposit.carsharing.service.ProfileService;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;

@Component
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@Path("/profile")
public class ProfileEndpoint {
    private final ProfileService profileService;

    public ProfileEndpoint(ProfileService profileService) {
        this.profileService = profileService;
    }

    @POST
    public Response createProfile(Profile profile) throws EntityAlreadyExistException {
        profileService.createProfile(profile);
        return Response.status(201).entity(profile).build();
    }

    @GET
    public Response getAllProfiles() {
        return Response.status(200).entity(profileService.getAllProfiles()).build();
    }

    @GET
    @Path("{id}")
    public Response getProfile(@PathParam("id") Long id) throws EntityNotFoundException {
        Profile profile = profileService.getProfile(id);
        return Response.status(200).entity(profile).build();
    }

}
