package com.exposit.carsharing.endpoint;

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
        return null;
    }

    @DELETE
    @Path("/body-type")
    public Response deleteBodyType(Long id) {
        return null;
    }

    @PUT
    @Path("/body-type")
    public Response updateBodyType(Long id, String name) {
        return null;
    }

    @GET
    @Path("/body-type")
    public Response getAllBodyTypes() {
        return null;
    }

    @GET
    @Path("/body-type/{id}")
    public Response getBodyType(@PathParam("id") Long id) {
        return null;
    }

    // ---------------------- Brand --------------------

    @POST
    @Path("/brand")
    public Response createBrand(String name) {
        return null;
    }

    @DELETE
    @Path("/brand")
    public Response deleteBrand(Long id) {
        return null;
    }

    @PUT
    @Path("/brand")
    public Response updateBrand(Long id, String name) {
        return null;
    }

    @GET
    @Path("/brand")
    public Response getAllBrands() {
        return null;
    }

    @GET
    @Path("brand{id}/model")
    public Response getAllModelsByBrand(@PathParam("id") Long brand_id) {
        return null;
    }

    @GET
    @Path("/brand/{id}")
    public Response getBrand(@PathParam("id") Long id) {
        return null;
    }

    // ---------------------- Color --------------------

    @POST
    @Path("/color")
    public Response createColor(String name) {
        return null;
    }

    @DELETE
    @Path("/color")
    public Response deleteColor(Long id) {
        return null;
    }

    @PUT
    @Path("/color")
    public Response updateColor(Long id, String name) {
        return null;
    }

    @GET
    @Path("/color")
    public Response getAllColors() {
        return null;
    }

    @GET
    @Path("/color/{id}")
    public Response getColor(@PathParam("id") Long id) {
        return null;
    }

    // ---------------------- Drive unit --------------------

    @POST
    @Path("/drive-unit")
    public Response createDriveUnit(String name) {
        return null;
    }

    @DELETE
    @Path("/drive-unit")
    public Response deleteDriveUnit(Long id) {
        return null;
    }

    @PUT
    @Path("/drive-unit")
    public Response updateDriveUnit(Long id, String name) {
        return null;
    }

    @GET
    @Path("/drive-unit")
    public Response getAllDriveUnits() {
        return null;
    }

    @GET
    @Path("/drive-unit/{id}")
    public Response getDriveUnit(@PathParam("id") Long id) {
        return null;
    }

    // ---------------------- Fuel type --------------------

    @POST
    @Path("/fuel-type")
    public Response createFuelType(String name) {
        return null;
    }

    @DELETE
    @Path("/fuel-type")
    public Response deleteFuelType(Long id) {
        return null;
    }

    @PUT
    @Path("/fuel-type")
    public Response updateFuelType(Long id, String name) {
        return null;
    }

    @GET
    @Path("/fuel-type")
    public Response getAllFuelTypes() {
        return null;
    }

    @GET
    @Path("/fuel-type/{id}")
    public Response getFuelType(@PathParam("id") Long id) {
        return null;
    }

    // ---------------------- Gearbox --------------------

    @POST
    @Path("/gearbox")
    public Response createGearbox(String name) {
        return null;
    }

    @DELETE
    @Path("/gearbox")
    public Response deleteGearbox(Long id) {
        return null;
    }

    @PUT
    @Path("/gearbox")
    public Response updateGearbox(Long id, String name) {
        return null;
    }

    @GET
    @Path("/gearbox")
    public Response getAllGearboxes() {
        return null;
    }

    @GET
    @Path("/gearbox/{id}")
    public Response getGearbox(@PathParam("id") Long id) {
        return null;
    }

    // ---------------------- Interior material --------------------

    @POST
    @Path("/interior-material")
    public Response createInteriorMaterial(String name) {
        return null;
    }

    @DELETE
    @Path("/interior-material")
    public Response deleteInteriorMaterial(Long id) {
        return null;
    }

    @PUT
    @Path("/interior-material")
    public Response updateInteriorMaterial(Long id, String name) {
        return null;
    }

    @GET
    @Path("/interior-material")
    public Response getAllInteriorMaterials() {
        return null;
    }

    @GET
    @Path("/interior-material/{id}")
    public Response getInteriorMaterial(@PathParam("id") Long id) {
        return null;
    }

    // ---------------------- Model --------------------

    @POST
    @Path("/model")
    public Response createModel(String name) {
        return null;
    }

    @DELETE
    @Path("/model")
    public Response deleteModel(Long id) {
        return null;
    }

    @PUT
    @Path("/model")
    public Response updateModel(Long id, String name) {
        return null;
    }

    @GET
    @Path("/model")
    public Response getAllModels() {
        return null;
    }

    @GET
    @Path("/model/{id}")
    public Response getModel(@PathParam("id") Long id) {
        return null;
    }

    // ---------------------- Tires season --------------------

    @POST
    @Path("/tires-season")
    public Response createTiresSeason(String name) {
        return null;
    }

    @DELETE
    @Path("/tires-season")
    public Response deleteTiresSeason(Long id) {
        return null;
    }

    @PUT
    @Path("/tires-season")
    public Response updateTiresSeason(Long id, String name) {
        return null;
    }

    @GET
    @Path("/tires-season")
    public Response getAllTiresSeasons() {
        return null;
    }

    @GET
    @Path("/tires-season/{id}")
    public Response getTiresSeason(@PathParam("id") Long id) {
        return null;
    }
}