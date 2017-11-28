package com.exposit.carsharing.endpoint;

import com.dropbox.core.DbxException;
import com.exposit.carsharing.dto.PassportDataRequest;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.UnauthorizedException;
import com.exposit.carsharing.service.PassportDataService;
import com.exposit.carsharing.service.SecurityService;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;

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
        return Response.status(200).entity(passportDataService.getPassportDataResponse(ownerId)).build();
    }

    @PUT
    public Response updatePassportData(@Valid PassportDataRequest passportDataRequest)
            throws EntityNotFoundException, UnauthorizedException {
        Long ownerId = securityService.getPrincipalId();
        return Response.status(200).entity(passportDataService.updatePassportData(ownerId, passportDataRequest)).build();
    }

    @PUT
    @Path("registration-photo")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadRegistrationPhoto(@FormDataParam("file") InputStream uploadedInputStream,
                                            @FormDataParam("file") FormDataContentDisposition fileDetail)
            throws UnauthorizedException, EntityNotFoundException, IOException, DbxException {
        return Response.status(200).entity(passportDataService
                .uploadRegistrationPhoto(securityService.getPrincipalId(), uploadedInputStream, fileDetail)).build();
    }

    @PUT
    @Path("photo")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadPhoto(@FormDataParam("file") InputStream uploadedInputStream,
                                @FormDataParam("file") FormDataContentDisposition fileDetail)
            throws UnauthorizedException, EntityNotFoundException, IOException, DbxException {
        return Response.status(200).entity(passportDataService
                .uploadPhoto(securityService.getPrincipalId(), uploadedInputStream, fileDetail)).build();
    }

    @DELETE
    @Path("registration-photo")
    public Response deleteRegistrationPhoto() throws UnauthorizedException, EntityNotFoundException {
        return Response.status(200).entity(passportDataService
                .deleteRegistrationPhoto(securityService.getPrincipalId())).build();
    }

    @DELETE
    @Path("photo")
    public Response deletePhoto() throws UnauthorizedException, EntityNotFoundException {
        return Response.status(200).entity(passportDataService.deletePhoto(securityService.getPrincipalId())).build();
    }
}
