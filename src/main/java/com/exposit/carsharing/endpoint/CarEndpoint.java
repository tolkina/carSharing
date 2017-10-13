package com.exposit.carsharing.endpoint;

import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.model.Car;
import com.exposit.carsharing.service.CarService;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/car")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class CarEndpoint {
    private final CarService carService;

    public CarEndpoint(CarService carService) {
        this.carService = carService;
    }

    @POST
    @Path("{id}")
    public Response createCar(@PathParam("id") Long ownerId, Car car) throws EntityNotFoundException, EntityAlreadyExistException {
        carService.createCar(car, ownerId);
        return Response.status(201).entity(car).build();
    }

    @GET
    public Response getAllCars() {
        return Response.status(200).entity(carService.getAllCars()).build();
    }

    @GET
    @Path("{id}")
    public Response getCar(@PathParam("id") Long id) throws EntityNotFoundException {
        return Response.status(200).entity(carService.getCar(id)).build();
    }
}
