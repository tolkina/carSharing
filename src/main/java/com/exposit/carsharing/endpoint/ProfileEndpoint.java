package com.exposit.carsharing.endpoint;

import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.model.Profile;
import com.exposit.carsharing.service.ProfileService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
        profileService.create(profile);
        return Response.status(201).entity(profile).build();
    }


    @PUT
    @Path("{id}")
    public Response updateProfile(@PathParam("id") Integer id, Profile profile){
        profileService.updateProfile(profile);
        return Response.ok().entity(profile).build();
    }

    @GET
    public Response getAllProfiles() {
        return Response.status(200).entity(profileService.getAll()).build();
    }

    @GET
    @Path("{id}")
    public Response getProfile(@PathParam("id") Long id) throws EntityNotFoundException {
        Profile profile = profileService.get(id);
        return Response.status(200).entity(profile).build();
    }

    @DELETE
    @Path("{profile_id}")
    public Response deleteAd(@PathParam("profile_id") Long profileId) throws PrivilegeException, EntityNotFoundException {
        profileService.delete(profileId);
        return Response.status(200).build();
    }

}
