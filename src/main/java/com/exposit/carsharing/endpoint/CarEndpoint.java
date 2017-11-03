package com.exposit.carsharing.endpoint;

import com.exposit.carsharing.dto.CarRequest;
import com.exposit.carsharing.dto.CurrentConditionRequest;
import com.exposit.carsharing.dto.GeneralParametersRequest;
import com.exposit.carsharing.dto.TechnicalParametersRequest;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.exception.UnauthorizedException;
import com.exposit.carsharing.service.CarService;
import com.exposit.carsharing.service.SecurityService;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    public Response deleteCar(@PathParam("car_id") Long carId) throws PrivilegeException, EntityNotFoundException, UnauthorizedException {
        Long ownerId = securityService.getPrincipalId();
        carService.delete(carId, ownerId);
        return Response.status(200).build();
    }

    // ------------------------------- Technical Parameters -------------------------------------

    @PUT
    @Path("/{car_id}/technical-parameters")
    public Response updateTechnicalParameters(@PathParam("car_id") Long carId,
                                              @Valid TechnicalParametersRequest technicalParametersRequest)
            throws EntityNotFoundException, EntityAlreadyExistException, PrivilegeException, UnauthorizedException {
        Long ownerId = securityService.getPrincipalId();
        return Response.status(200).entity(carService.updateTechnicalParameters(technicalParametersRequest, carId, ownerId)).build();
    }

    @GET
    @Path("/technical-parameters")
    public Response getAllTechnicalParameters() {
        return Response.status(200).entity(carService.getAllTechnicalParameters()).build();
    }

    @GET
    @Path("/{car_id}/technical-parameters")
    public Response getTechnicalParameters(@PathParam("car_id") Long carId) throws EntityNotFoundException {
        return Response.status(200).entity(carService.getTechnicalParameters(carId)).build();
    }

    // ------------------------------- General Parameters ---------------------------------------

    @PUT
    @Path("/{car_id}/general-parameters")
    public Response createGeneralParameters(
            @PathParam("car_id") Long carId, @Valid GeneralParametersRequest generalParametersRequest)
            throws EntityNotFoundException, EntityAlreadyExistException, PrivilegeException, UnauthorizedException {
        Long ownerId = securityService.getPrincipalId();
        return Response.status(200).entity(carService.updateGeneralParameters(generalParametersRequest, carId, ownerId)).build();
    }

    @GET
    @Path("/general-parameters")
    public Response getAllGeneralParameters() {
        return Response.status(200).entity(carService.getAllGeneralParameters()).build();
    }

    @GET
    @Path("/{car_id}/general-parameters")
    public Response getGeneralParameters(@PathParam("car_id") Long carId) throws EntityNotFoundException {
        return Response.status(200).entity(carService.getGeneralParameters(carId)).build();
    }

    // ------------------------------- Current Condition ----------------------------------------

    @PUT
    @Path("/{car_id}/current-condition")
    public Response createCurrentCondition(@PathParam("car_id") Long carId, CurrentConditionRequest currentCondition)
            throws EntityNotFoundException, EntityAlreadyExistException, PrivilegeException, UnauthorizedException {
        Long ownerId = securityService.getPrincipalId();
        return Response.status(200).entity(carService.updateCurrentCondition(currentCondition, carId, ownerId)).build();
    }

    @GET
    @Path("/current-condition")
    public Response getAllCurrentConditions() {
        return Response.status(200).entity(carService.getAllCurrentCondition()).build();
    }

    @GET
    @Path("/{car_id}/current-condition")
    public Response getCurrentCondition(@PathParam("car_id") Long carId) throws EntityNotFoundException {
        return Response.status(200).entity(carService.getCurrentCondition(carId)).build();
    }
}
