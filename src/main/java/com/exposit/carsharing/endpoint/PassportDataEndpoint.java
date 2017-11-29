package com.exposit.carsharing.endpoint;

import com.dropbox.core.DbxException;
import com.exposit.carsharing.dto.PassportDataRequest;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.UnauthorizedException;
import com.exposit.carsharing.service.PassportDataService;
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
@Path("/passport-data/")
public class PassportDataEndpoint {
    private final PassportDataService passportDataService;
    private final SecurityService securityService;

    public PassportDataEndpoint(PassportDataService passportDataService, SecurityService securityService) {
        this.passportDataService = passportDataService;
        this.securityService = securityService;
    }

    @GET
    public Response retrievePassportData() throws EntityNotFoundException, UnauthorizedException {
        Long ownerId = securityService.getPrincipalId();
        log.debug("User with id {} retrieve passportData", ownerId);
        return Response.status(200).entity(passportDataService.getPassportDataResponse(ownerId)).build();
    }

    @PUT
    public Response updatePassportData(@Valid PassportDataRequest passportDataRequest)
            throws EntityNotFoundException, UnauthorizedException {
        Long ownerId = securityService.getPrincipalId();
        log.debug("User with id {} update passportData", ownerId);
        return Response.status(200)
                .entity(passportDataService.updatePassportData(ownerId, passportDataRequest)).build();
    }

    @PUT
    @Path("registration-photo")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadRegistrationPhoto(@FormDataParam("file") InputStream uploadedInputStream,
                                            @FormDataParam("file") FormDataContentDisposition fileDetail)
            throws UnauthorizedException, EntityNotFoundException, IOException, DbxException {
        Long ownerId = securityService.getPrincipalId();
        log.debug("User with id {} upload registrationPhoto for passportData", ownerId);
        return Response.status(200)
                .entity(passportDataService.uploadRegistrationPhoto(ownerId, uploadedInputStream, fileDetail)).build();
    }

    @PUT
    @Path("photo")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadPhoto(@FormDataParam("file") InputStream uploadedInputStream,
                                @FormDataParam("file") FormDataContentDisposition fileDetail)
            throws UnauthorizedException, EntityNotFoundException, IOException, DbxException {
        Long ownerId = securityService.getPrincipalId();
        log.debug("User with id {} upload photo for passportData", ownerId);
        return Response.status(200)
                .entity(passportDataService.uploadPhoto(ownerId, uploadedInputStream, fileDetail)).build();
    }

    @DELETE
    @Path("registration-photo")
    public Response deleteRegistrationPhoto() throws UnauthorizedException, EntityNotFoundException {
        Long ownerId = securityService.getPrincipalId();
        log.debug("User with id {} delete registrationPhoto for passportData", ownerId);
        return Response.status(200).entity(passportDataService.deleteRegistrationPhoto(ownerId)).build();
    }

    @DELETE
    @Path("photo")
    public Response deletePhoto() throws UnauthorizedException, EntityNotFoundException {
        Long ownerId = securityService.getPrincipalId();
        log.debug("User with id {} delete photo for passportData", ownerId);
        return Response.status(200).entity(passportDataService.deletePhoto(ownerId)).build();
    }
}
