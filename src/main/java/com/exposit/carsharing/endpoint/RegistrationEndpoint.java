package com.exposit.carsharing.endpoint;

import com.exposit.carsharing.dto.UserRequest;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.service.UserService;
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
@Path("/registration")
public class RegistrationEndpoint {
    private final UserService userService;

    public RegistrationEndpoint(UserService userService) {
        this.userService = userService;
    }

    @POST
    public Response createProfile(@Valid UserRequest userRequest) throws EntityAlreadyExistException {
        return Response.status(201).entity(userService.createUser(userRequest)).build();
    }

    @POST
    @Path("/admin")
    public Response createProfileAdmin(@Valid UserRequest userRequest) throws EntityAlreadyExistException {
        return Response.status(201).entity(userService.createAdmin(userRequest)).build();
    }
}
