package com.exposit.carsharing.endpoint;

import com.dropbox.core.DbxException;
import com.exposit.carsharing.dto.*;
import com.exposit.carsharing.exception.*;
import com.exposit.carsharing.service.CarService;
import com.exposit.carsharing.service.SecurityService;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Component
@Path("/car")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class CarEndpoint {
    private final CarService carService;
    private final SecurityService securityService;

    public CarEndpoint(CarService carService, SecurityService securityService) {
        this.carService = carService;
        this.securityService = securityService;
    }

    // ------------------------------- Car ----------------------------------------------------

    @POST
    public Response createCar(CarRequest car) throws EntityNotFoundException, EntityAlreadyExistException,
            PrivilegeException, UnauthorizedException {
        Long ownerId = securityService.getPrincipalId();
        return Response.status(201).entity(carService.create(car, ownerId)).build();
    }

    @GET
    public Response getAllCars() {
        return Response.status(200).entity(carService.getAll()).build();
    }

    @GET
    @Path("/{car_id}")
    public Response getCar(@PathParam("car_id") Long carId) throws EntityNotFoundException {
        return Response.status(200).entity(carService.getCarResponse(carId)).build();
    }

    @DELETE
    @Path("/{car_id}")
    public Response deleteCar(@PathParam("car_id") Long carId)
            throws PrivilegeException, EntityNotFoundException, UnauthorizedException, AdException {
        Long ownerId = securityService.getPrincipalId();
        carService.delete(carId, ownerId);
        return Response.status(200).build();
    }

    // ------------------------------- Technical Parameters -------------------------------------

    @PUT
    @Path("/{car_id}/technical-parameters")
    public Response updateTechnicalParameters(@PathParam("car_id") Long carId,
                                              @Valid TechnicalParametersRequest technicalParametersRequest)
            throws EntityNotFoundException, EntityAlreadyExistException, PrivilegeException, UnauthorizedException,
            AdException {
        Long ownerId = securityService.getPrincipalId();
        return Response.status(200).entity(
                carService.updateTechnicalParameters(technicalParametersRequest, carId, ownerId)).build();
    }

    @GET
    @Path("/{car_id}/technical-parameters")
    public Response getTechnicalParameters(@PathParam("car_id") Long carId) throws EntityNotFoundException {
        return Response.status(200).entity(carService.getTechnicalParameters(carId)).build();
    }

    // ------------------------------- General Parameters ---------------------------------------

    @PUT
    @Path("/{car_id}/general-parameters")
    public Response updateGeneralParameters(@PathParam("car_id") Long carId,
                                            @Valid GeneralParametersRequest generalParametersRequest)
            throws EntityNotFoundException, EntityAlreadyExistException, PrivilegeException, UnauthorizedException,
            AdException {
        Long ownerId = securityService.getPrincipalId();
        return Response.status(200).entity(carService.updateGeneralParameters(generalParametersRequest, carId, ownerId)).build();
    }

    @GET
    @Path("/{car_id}/general-parameters")
    public Response getGeneralParameters(@PathParam("car_id") Long carId) throws EntityNotFoundException {
        return Response.status(200).entity(carService.getGeneralParameters(carId)).build();
    }

    @PUT
    @Path("/{car_id}/general-parameters/photos")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadPhotos(@PathParam("car_id") Long carId, FormDataMultiPart multiPart)
            throws UnauthorizedException, EntityNotFoundException, IOException, AdException, DbxException,
            PrivilegeException {
        Long ownerId = securityService.getPrincipalId();
        return Response.status(200).entity(carService.uploadPhotos(multiPart, ownerId, carId)).build();
    }

    @DELETE
    @Path("/{car_id}/general-parameters/photos")
    public Response deletePhotos(@PathParam("car_id") Long carId, @Valid CarPhotosRequest carPhotosRequest)
            throws UnauthorizedException, EntityNotFoundException, IOException, AdException, DbxException,
            PrivilegeException {
        Long ownerId = securityService.getPrincipalId();
        return Response.status(200).entity(carService.deletePhotos(carPhotosRequest, ownerId, carId)).build();
    }

    // ------------------------------- Current Condition ----------------------------------------

    @PUT
    @Path("/{car_id}/current-condition")
    public Response updateCurrentCondition(@PathParam("car_id") Long carId, CurrentConditionRequest currentCondition)
            throws EntityNotFoundException, EntityAlreadyExistException, PrivilegeException, UnauthorizedException,
            AdException {
        Long ownerId = securityService.getPrincipalId();
        return Response.status(200).entity(carService.updateCurrentCondition(currentCondition, carId, ownerId)).build();
    }

    @GET
    @Path("/{car_id}/current-condition")
    public Response getCurrentCondition(@PathParam("car_id") Long carId) throws EntityNotFoundException {
        return Response.status(200).entity(carService.getCurrentCondition(carId)).build();
    }
}
