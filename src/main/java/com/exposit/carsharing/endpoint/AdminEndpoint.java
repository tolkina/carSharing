package com.exposit.carsharing.endpoint;

import com.exposit.carsharing.domain.BodyType;
import com.exposit.carsharing.domain.Brand;
import com.exposit.carsharing.dto.CarParameterRequest;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.service.AdminService;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/admin")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class AdminEndpoint {
    private final AdminService adminService;

    public AdminEndpoint(AdminService adminService) {
        this.adminService = adminService;
    }

    // ---------------------- Body type --------------------

    @POST
    @Path("/body-type")
    public Response createBodyType(@Valid CarParameterRequest carParameterRequest) throws EntityAlreadyExistException {
        BodyType bodyType = adminService.createBodyType(carParameterRequest);
        return Response.status(201).entity(bodyType).build();
    }

    @DELETE
    @Path("/body-type/{id}")
    public Response deleteBodyType(@PathParam("id") Long id) throws EntityNotFoundException {
        adminService.deleteBodyType(id);
        return Response.status(200).build();
    }

    @PUT
    @Path("/body-type/{id}")
    public Response updateBodyType(@PathParam("id") Long id, @Valid CarParameterRequest carParameterRequest) throws EntityAlreadyExistException, EntityNotFoundException {
        BodyType bodyType = adminService.updateBodyType(id, carParameterRequest);
        return Response.status(200).entity(bodyType).build();
    }

    @GET
    @Path("/body-type")
    public Response getAllBodyTypes() {
        return Response.status(200).entity(adminService.getAllBodyTypes()).build();
    }

    @GET
    @Path("/body-type/{id}")
    public Response getBodyType(@PathParam("id") Long id) throws EntityNotFoundException {
        return Response.status(200).entity(adminService.getBodyType(id)).build();
    }

    // ---------------------- Brand --------------------

    @POST
    @Path("/brand")
    public Response createBrand(@Valid CarParameterRequest carParameterRequest) throws EntityAlreadyExistException {
        Brand brand = adminService.createBrand(carParameterRequest);
        return Response.status(200).entity(brand).build();
    }

    @DELETE
    @Path("/brand/{id}")
    public Response deleteBrand(@PathParam("id") Long id) throws EntityNotFoundException {
        adminService.deleteBrand(id);
        return Response.status(200).build();
    }

    @PUT
    @Path("/brand/{id}")
    public Response updateBrand(@PathParam("id") Long id, @Valid CarParameterRequest carParameterRequest) throws EntityAlreadyExistException, EntityNotFoundException {
        return Response.status(200).entity(adminService.updateBrand(id, carParameterRequest)).build();
    }

    @GET
    @Path("/brand")
    public Response getAllBrands() {
        return Response.status(200).entity(adminService.getAllBrands()).build();
    }


    @GET
    @Path("/brand/{id}")
    public Response getBrand(@PathParam("id") Long id) throws EntityNotFoundException {
        return Response.status(200).entity(adminService.getBrand(id)).build();
    }

    // ---------------------- Model --------------------

    @POST
    @Path("brand/{brand_id}/model")
    public Response createModel(@PathParam("brand_id") Long brandId, @Valid CarParameterRequest carParameterRequest) throws EntityAlreadyExistException, EntityNotFoundException {
        return Response.status(200).entity(adminService.createModel(brandId, carParameterRequest)).build();
    }

    @DELETE
    @Path("/model/{id}")
    public Response deleteModel(@PathParam("id") Long id) throws EntityNotFoundException {
        adminService.deleteModel(id);
        return Response.status(200).build();
    }

    @PUT
    @Path("/model/{id}")
    public Response updateModel(@PathParam("id") Long id, @Valid CarParameterRequest carParameterRequest) throws EntityAlreadyExistException, EntityNotFoundException {
        return Response.status(200).entity(adminService.updateModel(id, carParameterRequest)).build();
    }

    @GET
    @Path("/model")
    public Response getAllModels() {
        return Response.status(200).entity(adminService.getAllModels()).build();
    }

    @GET
    @Path("brand/{id}/model")
    public Response getAllModelsByBrand(@PathParam("id") Long brand_id) throws EntityNotFoundException {
        return Response.status(200).entity(adminService.getAllModelsByBrand(brand_id)).build();
    }

    @GET
    @Path("/model/{id}")
    public Response getModel(@PathParam("id") Long id) throws EntityNotFoundException {
        return Response.status(200).entity(adminService.getModel(id)).build();
    }

    // ---------------------- Color --------------------

    @POST
    @Path("/color")
    public Response createColor(@Valid CarParameterRequest carParameterRequest) throws EntityAlreadyExistException {
        return Response.status(200).entity(adminService.createColor(carParameterRequest)).build();
    }

    @DELETE
    @Path("/color/{id}")
    public Response deleteColor(@PathParam("id") Long id) throws EntityNotFoundException {
        adminService.deleteColor(id);
        return Response.status(200).build();
    }

    @PUT
    @Path("/color/{id}")
    public Response updateColor(@PathParam("id") Long id, @Valid CarParameterRequest carParameterRequest) throws EntityAlreadyExistException, EntityNotFoundException {
        return Response.status(200).entity(adminService.updateColor(id, carParameterRequest)).build();
    }

    @GET
    @Path("/color")
    public Response getAllColors() {
        return Response.status(200).entity(adminService.getAllColors()).build();
    }

    @GET
    @Path("/color/{id}")
    public Response getColor(@PathParam("id") Long id) throws EntityNotFoundException {
        return Response.status(200).entity(adminService.getColor(id)).build();
    }

    // ---------------------- Drive unit --------------------

    @POST
    @Path("/drive-unit")
    public Response createDriveUnit(@Valid CarParameterRequest carParameterRequest) throws EntityAlreadyExistException {
        return Response.status(200).entity(adminService.createDriveUnit(carParameterRequest)).build();
    }

    @DELETE
    @Path("/drive-unit/{id}")
    public Response deleteDriveUnit(@PathParam("id") Long id) throws EntityNotFoundException {
        adminService.deleteDriveUnit(id);
        return Response.status(200).build();
    }

    @PUT
    @Path("/drive-unit/{id}")
    public Response updateDriveUnit(@PathParam("id") Long id, @Valid CarParameterRequest carParameterRequest) throws EntityAlreadyExistException, EntityNotFoundException {
        return Response.status(200).entity(adminService.updateDriveUnit(id, carParameterRequest)).build();
    }

    @GET
    @Path("/drive-unit")
    public Response getAllDriveUnits() {
        return Response.status(200).entity(adminService.getAllDriveUnits()).build();
    }

    @GET
    @Path("/drive-unit/{id}")
    public Response getDriveUnit(@PathParam("id") Long id) throws EntityNotFoundException {
        return Response.status(200).entity(adminService.getDriveUnit(id)).build();
    }

    // ---------------------- Fuel type --------------------

    @POST
    @Path("/fuel-type")
    public Response createFuelType(@Valid CarParameterRequest carParameterRequest) throws EntityAlreadyExistException {
        return Response.status(200).entity(adminService.createFuelType(carParameterRequest)).build();
    }

    @DELETE
    @Path("/fuel-type/{id}")
    public Response deleteFuelType(@PathParam("id") Long id) throws EntityNotFoundException {
        adminService.deleteFuelType(id);
        return Response.status(200).build();
    }

    @PUT
    @Path("/fuel-type/{id}")
    public Response updateFuelType(@PathParam("id") Long id, @Valid CarParameterRequest carParameterRequest) throws EntityAlreadyExistException, EntityNotFoundException {
        return Response.status(200).entity(adminService.updateFuelType(id, carParameterRequest)).build();
    }

    @GET
    @Path("/fuel-type")
    public Response getAllFuelTypes() {
        return Response.status(200).entity(adminService.getAllFuelTypes()).build();
    }

    @GET
    @Path("/fuel-type/{id}")
    public Response getFuelType(@PathParam("id") Long id) throws EntityNotFoundException {
        return Response.status(200).entity(adminService.getFuelType(id)).build();
    }

    // ---------------------- Gearbox --------------------

    @POST
    @Path("/gearbox")
    public Response createGearbox(@Valid CarParameterRequest carParameterRequest) throws EntityAlreadyExistException {
        return Response.status(200).entity(adminService.createGearbox(carParameterRequest)).build();
    }

    @DELETE
    @Path("/gearbox/{id}")
    public Response deleteGearbox(@PathParam("id") Long id) throws EntityNotFoundException {
        adminService.deleteGearbox(id);
        return Response.status(200).build();
    }

    @PUT
    @Path("/gearbox/{id}")
    public Response updateGearbox(@PathParam("id") Long id, @Valid CarParameterRequest carParameterRequest) throws EntityAlreadyExistException, EntityNotFoundException {
        return Response.status(200).entity(adminService.updateGearbox(id, carParameterRequest)).build();
    }

    @GET
    @Path("/gearbox")
    public Response getAllGearboxes() {
        return Response.status(200).entity(adminService.getAllGearboxes()).build();
    }

    @GET
    @Path("/gearbox/{id}")
    public Response getGearbox(@PathParam("id") Long id) throws EntityNotFoundException {
        return Response.status(200).entity(adminService.getGearbox(id)).build();
    }

    // ---------------------- Interior material --------------------

    @POST
    @Path("/interior-material")
    public Response createInteriorMaterial(@Valid CarParameterRequest carParameterRequest) throws EntityAlreadyExistException {
        return Response.status(200).entity(adminService.createInteriorMaterial(carParameterRequest)).build();
    }

    @DELETE
    @Path("/interior-material/{id}")
    public Response deleteInteriorMaterial(@PathParam("id") Long id) throws EntityNotFoundException {
        adminService.deleteInteriorMaterial(id);
        return Response.status(200).build();
    }

    @PUT
    @Path("/interior-material/{id}")
    public Response updateInteriorMaterial(@PathParam("id") Long id, @Valid CarParameterRequest carParameterRequest) throws EntityAlreadyExistException, EntityNotFoundException {
        return Response.status(200).entity(adminService.updateInteriorMaterial(id, carParameterRequest)).build();
    }

    @GET
    @Path("/interior-material")
    public Response getAllInteriorMaterials() {
        return Response.status(200).entity(adminService.getAllInteriorMaterials()).build();
    }

    @GET
    @Path("/interior-material/{id}")
    public Response getInteriorMaterial(@PathParam("id") Long id) throws EntityNotFoundException {
        return Response.status(200).entity(adminService.getInteriorMaterial(id)).build();
    }

    // ---------------------- Tires season --------------------

    @POST
    @Path("/tires-season")
    public Response createTiresSeason(@Valid CarParameterRequest carParameterRequest) throws EntityAlreadyExistException {
        return Response.status(200).entity(adminService.createTiresSeason(carParameterRequest)).build();
    }

    @DELETE
    @Path("/tires-season/{id}")
    public Response deleteTiresSeason(@PathParam("id") Long id) throws EntityNotFoundException {
        adminService.deleteTiresSeason(id);
        return Response.status(200).build();
    }

    @PUT
    @Path("/tires-season/{id}")
    public Response updateTiresSeason(@PathParam("id") Long id, @Valid CarParameterRequest carParameterRequest) throws EntityAlreadyExistException, EntityNotFoundException {
        return Response.status(200).entity(adminService.updateTiresSeason(id, carParameterRequest)).build();
    }

    @GET
    @Path("/tires-season")
    public Response getAllTiresSeasons() {
        return Response.status(200).entity(adminService.getAllTiresSeasons()).build();
    }

    @GET
    @Path("/tires-season/{id}")
    public Response getTiresSeason(@PathParam("id") Long id) throws EntityNotFoundException {
        return Response.status(200).entity(adminService.getTiresSeason(id)).build();
    }
}