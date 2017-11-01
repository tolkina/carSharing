package com.exposit.carsharing.endpoint;

import com.exposit.carsharing.dto.ProfileRequest;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.service.ProfileService;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@Path("/login")
public class LoginEndpoint {
    private final ProfileService profileService;

    public LoginEndpoint(ProfileService profileService) {
        this.profileService = profileService;
    }

    @POST
    public Response createProfile(@Valid ProfileRequest profileRequest) throws EntityAlreadyExistException {
        return Response.status(201).entity(profileService.createProfile(profileRequest)).build();
    }

    @POST
    @Path("/admin")
    public Response createProfileAdmin(@Valid ProfileRequest profileRequest) throws EntityAlreadyExistException {
        return Response.status(201).entity(profileService.createProfileAdmin(profileRequest)).build();
    }
}
