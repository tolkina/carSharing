package com.exposit.carsharing.endpoint;

import com.exposit.carsharing.dto.CarRequest;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
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
    public Response createCar(CarRequest car) throws EntityNotFoundException, EntityAlreadyExistException {
        Long ownerId = 1L;
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
    public Response deleteCar(@PathParam("car_id") Long carId) throws PrivilegeException, EntityNotFoundException {
        Long ownerId = 1L;
        carService.delete(carId, ownerId);
        return Response.status(200).build();
    }
}
