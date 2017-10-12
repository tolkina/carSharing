package com.exposit.carsharing.endpoint;

import com.exposit.carsharing.model.Car;
import com.exposit.carsharing.service.CarService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Sergei on 10/12/2017.
 */
@Controller
@Path("/")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class CarController {
    private final CarService carService;



    public CarController(CarService carService) {
        this.carService = carService;
    }

    @POST
    public Response createCar(Car car){
        carService.populate();
        return Response.status(201).entity(car).build();
    }


    @GET
    public Response getAll(){
        List<Car> cars = carService.getAll();
        return Response.status(200).entity(cars).build();
    }
}
