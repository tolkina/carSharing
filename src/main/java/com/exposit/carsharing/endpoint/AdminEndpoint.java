package com.exposit.carsharing.endpoint;

import com.exposit.carsharing.modelAdmin.BodyType;
import com.exposit.carsharing.modelAdmin.Brand;
import com.exposit.carsharing.service.AdminService;
import org.springframework.stereotype.Component;

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
    public Response createBodyType(String name) {
        BodyType bodyType = adminService.createBodyType(name);
        return Response.status(201).entity(bodyType).build();
    }

    @DELETE
    @Path("/body-type")
    public Response deleteBodyType(Long id) {
        adminService.deleteBodyType(id);
        return Response.status(200).build();
    }

    @PUT
    @Path("/body-type")
    public Response updateBodyType(Long id, String name) {
        BodyType bodyType = adminService.updateBodyType(id, name);
        return Response.status(200).entity(bodyType).build();
    }

    @GET
    @Path("/body-type")
    public Response getAllBodyTypes() {
        return Response.status(200).entity(adminService.getAllBodyTypes()).build();
    }

    @GET
    @Path("/body-type/{id}")
    public Response getBodyType(@PathParam("id") Long id) {
        return Response.status(200).entity(adminService.getBodyType(id)).build();
    }

    // ---------------------- Brand --------------------

    @POST
    @Path("/brand")
    public Response createBrand(String name) {
        Brand brand = adminService.createBrand(name);
        return Response.status(200).entity(brand).build();
    }

    @DELETE
    @Path("/brand")
    public Response deleteBrand(Long id) {
        adminService.deleteBrand(id);
        return Response.status(200).build();
    }

    @PUT
    @Path("/brand")
    public Response updateBrand(Long id, String name) {
        return Response.status(200).entity(adminService.updateBrand(id, name)).build();
    }

    @GET
    @Path("/brand")
    public Response getAllBrands() {
        return Response.status(200).entity(adminService.getAllBrands()).build();
    }

    @GET
    @Path("brand{id}/model")
    public Response getAllModelsByBrand(@PathParam("id") Long brand_id) {
        return Response.status(200).entity(adminService.getAllModelsByBrand(brand_id)).build();
    }

    @GET
    @Path("/brand/{id}")
    public Response getBrand(@PathParam("id") Long id) {
        return Response.status(200).entity(adminService.getBrand(id)).build();
    }

    // ---------------------- Color --------------------

    @POST
    @Path("/color")
    public Response createColor(String name) {
        return Response.status(200).entity(adminService.createColor(name)).build();
    }

    @DELETE
    @Path("/color")
    public Response deleteColor(Long id) {
        adminService.deleteColor(id);
        return Response.status(200).build();
    }

    @PUT
    @Path("/color")
    public Response updateColor(Long id, String name) {
        return Response.status(200).entity(adminService.updateColor(id, name)).build();
    }

    @GET
    @Path("/color")
    public Response getAllColors() {
        return Response.status(200).entity(adminService.getAllColors()).build();
    }

    @GET
    @Path("/color/{id}")
    public Response getColor(@PathParam("id") Long id) {
        return Response.status(200).entity(adminService.getColor(id)).build();
    }

    // ---------------------- Drive unit --------------------

    @POST
    @Path("/drive-unit")
    public Response createDriveUnit(String name) {
        return Response.status(200).entity(adminService.createDriveUnit(name)).build();
    }

    @DELETE
    @Path("/drive-unit")
    public Response deleteDriveUnit(Long id) {
        adminService.deleteDriveUnit(id);
        return Response.status(200).build();
    }

    @PUT
    @Path("/drive-unit")
    public Response updateDriveUnit(Long id, String name) {
        return Response.status(200).entity(adminService.updateDriveUnit(id, name)).build();
    }

    @GET
    @Path("/drive-unit")
    public Response getAllDriveUnits() {
        return Response.status(200).entity(adminService.getAllDriveUnits()).build();
    }

    @GET
    @Path("/drive-unit/{id}")
    public Response getDriveUnit(@PathParam("id") Long id) {
        return Response.status(200).entity(adminService.getDriveUnit(id)).build();
    }

    // ---------------------- Fuel type --------------------

    @POST
    @Path("/fuel-type")
    public Response createFuelType(String name) {
        return Response.status(200).entity(adminService.createFuelType(name)).build();
    }

    @DELETE
    @Path("/fuel-type")
    public Response deleteFuelType(Long id) {
        adminService.deleteFuelType(id);
        return Response.status(200).build();
    }

    @PUT
    @Path("/fuel-type")
    public Response updateFuelType(Long id, String name) {
        return Response.status(200).entity(adminService.updateFuelType(id, name)).build();
    }

    @GET
    @Path("/fuel-type")
    public Response getAllFuelTypes() {
        return Response.status(200).entity(adminService.getAllFuelTypes()).build();
    }

    @GET
    @Path("/fuel-type/{id}")
    public Response getFuelType(@PathParam("id") Long id) {
        return Response.status(200).entity(adminService.getFuelType(id)).build();
    }

    // ---------------------- Gearbox --------------------

    @POST
    @Path("/gearbox")
    public Response createGearbox(String name) {
        return Response.status(200).entity(adminService.createGearbox(name)).build();
    }

    @DELETE
    @Path("/gearbox")
    public Response deleteGearbox(Long id) {
        adminService.deleteGearbox(id);
        return Response.status(200).build();
    }

    @PUT
    @Path("/gearbox")
    public Response updateGearbox(Long id, String name) {
        return Response.status(200).entity(adminService.updateGearbox(id, name)).build();
    }

    @GET
    @Path("/gearbox")
    public Response getAllGearboxes() {
        return Response.status(200).entity(adminService.getAllGearboxes()).build();
    }

    @GET
    @Path("/gearbox/{id}")
    public Response getGearbox(@PathParam("id") Long id) {
        return Response.status(200).entity(adminService.getGearbox(id)).build();
    }

    // ---------------------- Interior material --------------------

    @POST
    @Path("/interior-material")
    public Response createInteriorMaterial(String name) {
        return Response.status(200).entity(adminService.createInteriorMaterial(name)).build();
    }

    @DELETE
    @Path("/interior-material")
    public Response deleteInteriorMaterial(Long id) {
        adminService.deleteInteriorMaterial(id);
        return Response.status(200).build();
    }

    @PUT
    @Path("/interior-material")
    public Response updateInteriorMaterial(Long id, String name) {
        return Response.status(200).entity(adminService.updateInteriorMaterial(id, name)).build();
    }

    @GET
    @Path("/interior-material")
    public Response getAllInteriorMaterials() {
        return Response.status(200).entity(adminService.getAllInteriorMaterials()).build();
    }

    @GET
    @Path("/interior-material/{id}")
    public Response getInteriorMaterial(@PathParam("id") Long id) {
        return Response.status(200).entity(adminService.getInteriorMaterial(id)).build();
    }

    // ---------------------- Model --------------------

    @POST
    @Path("/model")
    public Response createModel(String name) {
        return Response.status(200).entity(adminService.createModel(name)).build();
    }

    @DELETE
    @Path("/model")
    public Response deleteModel(Long id) {
        adminService.deleteModel(id);
        return Response.status(200).build();
    }

    @PUT
    @Path("/model")
    public Response updateModel(Long id, String name) {
        return Response.status(200).entity(adminService.updateModel(id, name)).build();
    }

    @GET
    @Path("/model")
    public Response getAllModels() {
        return Response.status(200).entity(adminService.getAllModels()).build();
    }

    @GET
    @Path("/model/{id}")
    public Response getModel(@PathParam("id") Long id) {
        return Response.status(200).entity(adminService.getModel(id)).build();
    }

    // ---------------------- Tires season --------------------

    @POST
    @Path("/tires-season")
    public Response createTiresSeason(String name) {
        return Response.status(200).entity(adminService.createTiresSeason(name)).build();
    }

    @DELETE
    @Path("/tires-season")
    public Response deleteTiresSeason(Long id) {
        adminService.deleteTiresSeason(id);
        return Response.status(200).build();
    }

    @PUT
    @Path("/tires-season")
    public Response updateTiresSeason(Long id, String name) {
        return Response.status(200).entity(adminService.updateTiresSeason(id, name)).build();
    }

    @GET
    @Path("/tires-season")
    public Response getAllTiresSeasons() {
        return Response.status(200).entity(adminService.getAllTiresSeasons()).build();
    }

    @GET
    @Path("/tires-season/{id}")
    public Response getTiresSeason(@PathParam("id") Long id) {
        return Response.status(200).entity(adminService.getTiresSeason(id)).build();
    }
}