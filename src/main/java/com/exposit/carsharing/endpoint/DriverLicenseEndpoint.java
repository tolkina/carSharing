package com.exposit.carsharing.endpoint;

import com.dropbox.core.DbxException;
import com.exposit.carsharing.dto.DriverLicenseRequest;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.UnauthorizedException;
import com.exposit.carsharing.service.DriverLicenseService;
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
@Path("/driver-license/")
public class DriverLicenseEndpoint {
    private final DriverLicenseService driverLicenseService;
    private final SecurityService securityService;

    public DriverLicenseEndpoint(DriverLicenseService driverLicenseService, SecurityService securityService) {
        this.driverLicenseService = driverLicenseService;
        this.securityService = securityService;
    }

    @GET
    public Response retrieveDriverLicense() throws EntityNotFoundException, UnauthorizedException {
        Long ownerId = securityService.getPrincipalId();
        return Response.status(200).entity(driverLicenseService.getDriverLicenseResponse(ownerId)).build();
    }

    @PUT
    public Response updateDriverLicense(@Valid DriverLicenseRequest driverLicenseRequest)
            throws EntityNotFoundException, UnauthorizedException {
        Long ownerId = securityService.getPrincipalId();
        return Response.status(200).entity(driverLicenseService.updateDriverLicense(ownerId, driverLicenseRequest)).build();
    }

    @PUT
    @Path("photo/front-side")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFrontSidePhoto(@FormDataParam("file") InputStream uploadedInputStream,
                                         @FormDataParam("file") FormDataContentDisposition fileDetail)
            throws UnauthorizedException, EntityNotFoundException, IOException, DbxException {
        return Response.status(200).entity(driverLicenseService
                .uploadFrontSidePhoto(securityService.getPrincipalId(), uploadedInputStream, fileDetail)).build();
    }

    @PUT
    @Path("photo/back-side")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadBackSidePhoto(@FormDataParam("file") InputStream uploadedInputStream,
                                        @FormDataParam("file") FormDataContentDisposition fileDetail)
            throws UnauthorizedException, EntityNotFoundException, IOException, DbxException {
        return Response.status(200).entity(driverLicenseService
                .uploadBackSidePhoto(securityService.getPrincipalId(), uploadedInputStream, fileDetail)).build();
    }

    @DELETE
    @Path("photo/front-side")
    public Response deleteFrontSidePhoto() throws UnauthorizedException, EntityNotFoundException {
        return Response.status(200).entity(driverLicenseService
                .deleteFrontSidePhoto(securityService.getPrincipalId())).build();
    }

    @DELETE
    @Path("photo/back-side")
    public Response deleteBackSidePhoto() throws UnauthorizedException, EntityNotFoundException {
        return Response.status(200).entity(driverLicenseService
                .deleteBackSidePhoto(securityService.getPrincipalId())).build();
    }
}