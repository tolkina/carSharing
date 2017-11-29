package com.exposit.carsharing.endpoint;

import com.dropbox.core.DbxException;
import com.exposit.carsharing.dto.PasswordRequest;
import com.exposit.carsharing.dto.ProfileRequest;
import com.exposit.carsharing.exception.*;
import com.exposit.carsharing.service.AdService;
import com.exposit.carsharing.service.CarService;
import com.exposit.carsharing.service.ProfileService;
import com.exposit.carsharing.service.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
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
        log.debug("Retrieve all profiles");
        return Response.status(200).entity(profileService.getAll()).build();
    }

    @GET
    @Path("principal")
    public Response getPrincipal() throws UnauthorizedException {
        log.debug("Retrieve principal");
        return Response.status(200).entity(securityService.getPrincipal()).build();
    }

    @GET
    @Path("{id}")
    public Response getProfileById(@PathParam("id") Long id) throws EntityNotFoundException {
        log.debug("Retrieve profile with id {}", id);
        return Response.status(200).entity(profileService.getProfileResponse(id)).build();
    }

    @GET
    public Response getProfile() throws EntityNotFoundException, UnauthorizedException {
        Long ownerId = securityService.getPrincipalId();
        log.debug("Retrieve principal profile with id {}", ownerId);
        return Response.status(200).entity(profileService.getProfileResponse(ownerId)).build();
    }

    @PUT
    public Response updateProfile(@Valid ProfileRequest profileRequest)
            throws EntityNotFoundException, UnauthorizedException {
        Long ownerId = securityService.getPrincipalId();
        log.debug("Update profile with id {}", ownerId);
        return Response.status(200).entity(profileService.updateProfile(ownerId, profileRequest)).build();
    }

    @PUT
    @Path("check-to-confirm")
    public Response checkToConfirmProfile() throws EntityNotFoundException, UnauthorizedException, ConfirmProfileException {
        Long profileId = securityService.getPrincipalId();
        log.debug("Set confirm status CHECK for profile with id {}", profileId);
        profileService.setConfirmProfileCheck(profileId);
        return Response.status(200).build();
    }

    @GET
    @Path("car")
    public Response getAllCarsForPrincipal(@DefaultValue("1") @QueryParam(value = "page") Integer page,
                                           @DefaultValue("4") @QueryParam(value = "size") Integer size,
                                           @DefaultValue("id") @QueryParam(value = "sort") String sort,
                                           @DefaultValue("asc") @QueryParam(value = "direction") String direction)
            throws UnauthorizedException, EntityNotFoundException {
        Long ownerId = securityService.getPrincipalId();
        log.debug("Get all cars for user with id {}", ownerId);
        return Response.status(200).entity(carService.getAllByOwner(ownerId, page, size, sort, direction)).build();
    }

    @GET
    @Path("car-without-ad")
    public Response getAllCarsWithoutAdForPrincipal() throws UnauthorizedException, EntityNotFoundException {
        Long ownerId = securityService.getPrincipalId();
        log.debug("Get all cars without ad for user with id {}", ownerId);
        return Response.status(200).entity(carService.getAllWithoutAdByOwner(ownerId)).build();
    }

    @GET
    @Path("ad")
    public Response getAllAdsForPrincipal(@DefaultValue("1") @QueryParam(value = "page") Integer page,
                                          @DefaultValue("4") @QueryParam(value = "size") Integer size,
                                          @DefaultValue("status") @QueryParam(value = "sort") String sort,
                                          @DefaultValue("asc") @QueryParam(value = "direction") String direction)
            throws EntityNotFoundException, UnauthorizedException {
        Long ownerId = securityService.getPrincipalId();
        log.debug("Get all ads for user with id {}", ownerId);
        return Response.status(200).entity(adService.getAllByOwner(ownerId, page, size, sort, direction)).build();
    }

    @PUT
    @Path("avatar")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadProfileAvatar(@FormDataParam("file") InputStream uploadedInputStream,
                                        @FormDataParam("file") FormDataContentDisposition fileDetail)
            throws DbxException, IOException, UnauthorizedException, EntityNotFoundException {
        Long ownerId = securityService.getPrincipalId();
        log.debug("User with id {} upload avatar", ownerId);
        return Response.status(200)
                .entity(profileService.uploadProfileAvatar(ownerId, uploadedInputStream, fileDetail)).build();
    }

    @DELETE
    @Path("avatar")
    public Response deleteProfileAvatar() throws UnauthorizedException, EntityNotFoundException {
        Long ownerId = securityService.getPrincipalId();
        log.debug("User with id {} delete avatar", ownerId);
        return Response.status(200).entity(profileService.deleteProfileAvatar(ownerId)).build();
    }

    @PUT
    @Path("/disable")
    public Response disableUser() throws UnauthorizedException, EntityNotFoundException, PrivilegeException {
        Long principalId = securityService.getPrincipalId();
        log.debug("Disabling user with id {}", principalId);
        profileService.disableUser(principalId);
        return Response.status(200).build();
    }

    @PUT
    @Path("/password")
    public Response changePassword(@Valid PasswordRequest passwordRequest)
            throws UnauthorizedException, EntityNotFoundException, PasswordException {
        Long principalId = securityService.getPrincipalId();
        log.debug("Changing password for user with id {}", principalId);
        profileService.changePassword(principalId, passwordRequest);
        return Response.status(200).build();
    }
}