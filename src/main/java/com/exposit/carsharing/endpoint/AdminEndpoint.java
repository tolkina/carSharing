package com.exposit.carsharing.endpoint;

import com.exposit.carsharing.dto.CarParameterRequest;
import com.exposit.carsharing.exception.*;
import com.exposit.carsharing.service.AdminService;
import com.exposit.carsharing.service.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Slf4j
@Component
@Path("/admin")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class AdminEndpoint {
    private final AdminService adminService;
    private final SecurityService securityService;

    public AdminEndpoint(AdminService adminService, SecurityService securityService) {
        this.adminService = adminService;
        this.securityService = securityService;
    }

    private void checkAdmin() throws UnauthorizedException, EntityNotFoundException, PrivilegeException {
        Long principalId = securityService.getPrincipalId();
        adminService.checkAdmin(principalId);
    }

    // ---------------------- Body type --------------------

    @POST
    @Path("/body-type")
    public Response createBodyType(@Valid CarParameterRequest carParameterRequest)
            throws EntityAlreadyExistException, EntityNotFoundException, PrivilegeException, UnauthorizedException {
        checkAdmin();
        log.debug("Create bodyType {}", carParameterRequest.getName());
        return Response.status(201).entity(adminService.createBodyType(carParameterRequest)).build();
    }

    @DELETE
    @Path("/body-type/{id}")
    public Response deleteBodyType(@PathParam("id") Long id)
            throws EntityNotFoundException, UnauthorizedException, PrivilegeException {
        checkAdmin();
        log.debug("Delete bodyType with id {}", id);
        adminService.deleteBodyType(id);
        return Response.status(200).build();
    }

    @PUT
    @Path("/body-type/{id}")
    public Response updateBodyType(@PathParam("id") Long id, @Valid CarParameterRequest carParameterRequest)
            throws EntityAlreadyExistException, EntityNotFoundException, UnauthorizedException, PrivilegeException {
        checkAdmin();
        log.debug("Update bodyType with id {}", id);
        return Response.status(200).entity(adminService.updateBodyType(id, carParameterRequest)).build();
    }

    @GET
    @Path("/body-type")
    public Response getAllBodyTypes(@DefaultValue("1") @QueryParam(value = "page") Integer page,
                                    @DefaultValue("100") @QueryParam(value = "size") Integer size,
                                    @DefaultValue("name") @QueryParam(value = "sort") String sort,
                                    @DefaultValue("asc") @QueryParam(value = "direction") String direction)
            throws EntityNotFoundException {
        log.debug("Get all bodyTypes");
        return Response.status(200).entity(adminService.getAllBodyTypes(page, size, sort, direction)).build();
    }

    @GET
    @Path("/body-type/{id}")
    public Response getBodyType(@PathParam("id") Long id) throws EntityNotFoundException {
        log.debug("Get bodyType with id {}", id);
        return Response.status(200).entity(adminService.getBodyType(id)).build();
    }

    // ---------------------- Brand --------------------

    @POST
    @Path("/brand")
    public Response createBrand(@Valid CarParameterRequest carParameterRequest)
            throws EntityAlreadyExistException, EntityNotFoundException, PrivilegeException, UnauthorizedException {
        checkAdmin();
        log.debug("Create brand {}", carParameterRequest.getName());
        return Response.status(200).entity(adminService.createBrand(carParameterRequest)).build();
    }

    @DELETE
    @Path("/brand/{id}")
    public Response deleteBrand(@PathParam("id") Long id)
            throws EntityNotFoundException, UnauthorizedException, PrivilegeException {
        checkAdmin();
        log.debug("Delete brand with id {}", id);
        adminService.deleteBrand(id);
        return Response.status(200).build();
    }

    @PUT
    @Path("/brand/{id}")
    public Response updateBrand(@PathParam("id") Long id, @Valid CarParameterRequest carParameterRequest)
            throws EntityAlreadyExistException, EntityNotFoundException, UnauthorizedException, PrivilegeException {
        checkAdmin();
        log.debug("Update brand with id {}", id);
        return Response.status(200).entity(adminService.updateBrand(id, carParameterRequest)).build();
    }

    @GET
    @Path("/brand")
    public Response getAllBrands(@DefaultValue("1") @QueryParam(value = "page") Integer page,
                                 @DefaultValue("100") @QueryParam(value = "size") Integer size,
                                 @DefaultValue("name") @QueryParam(value = "sort") String sort,
                                 @DefaultValue("asc") @QueryParam(value = "direction") String direction) {
        log.debug("Get all brands");
        return Response.status(200).entity(adminService.getAllBrands(page, size, sort, direction)).build();
    }

    @GET
    @Path("/brand/{id}")
    public Response getBrand(@PathParam("id") Long id) throws EntityNotFoundException {
        log.debug("Get brand with id {}", id);
        return Response.status(200).entity(adminService.getBrandResponse(id)).build();
    }

    // ---------------------- Model --------------------

    @POST
    @Path("brand/{brand_id}/model")
    public Response createModel(@PathParam("brand_id") Long brandId, @Valid CarParameterRequest carParameterRequest)
            throws EntityAlreadyExistException, EntityNotFoundException, UnauthorizedException, PrivilegeException {
        checkAdmin();
        log.debug("Create model {} for brand with id", carParameterRequest.getName(), brandId);
        return Response.status(200).entity(adminService.createModel(brandId, carParameterRequest)).build();
    }

    @DELETE
    @Path("/model/{id}")
    public Response deleteModel(@PathParam("id") Long id)
            throws EntityNotFoundException, UnauthorizedException, PrivilegeException {
        checkAdmin();
        log.debug("Delete model with id {}", id);
        adminService.deleteModel(id);
        return Response.status(200).build();
    }

    @PUT
    @Path("/model/{id}")
    public Response updateModel(@PathParam("id") Long id, @Valid CarParameterRequest carParameterRequest)
            throws EntityAlreadyExistException, EntityNotFoundException, UnauthorizedException, PrivilegeException {
        checkAdmin();
        log.debug("Update model with id {}", id);
        return Response.status(200).entity(adminService.updateModel(id, carParameterRequest)).build();
    }

    @GET
    @Path("/model")
    public Response getAllModels(@DefaultValue("1") @QueryParam(value = "page") Integer page,
                                 @DefaultValue("100") @QueryParam(value = "size") Integer size,
                                 @DefaultValue("name") @QueryParam(value = "sort") String sort,
                                 @DefaultValue("asc") @QueryParam(value = "direction") String direction) {
        log.debug("Get all models");
        return Response.status(200).entity(adminService.getAllModels(page, size, sort, direction)).build();
    }

    @GET
    @Path("brand/{id}/model")
    public Response getAllModelsByBrand(@DefaultValue("1") @QueryParam(value = "page") Integer page,
                                        @DefaultValue("100") @QueryParam(value = "size") Integer size,
                                        @DefaultValue("name") @QueryParam(value = "sort") String sort,
                                        @DefaultValue("asc") @QueryParam(value = "direction") String direction,
                                        @PathParam("id") Long brandId) throws EntityNotFoundException {
        log.debug("Get all models for brand with id {}", brandId);
        return Response.status(200)
                .entity(adminService.getAllModelsByBrand(brandId, page, size, sort, direction)).build();
    }

    @GET
    @Path("/model/{id}")
    public Response getModel(@PathParam("id") Long id) throws EntityNotFoundException {
        log.debug("Get model with id {}", id);
        return Response.status(200).entity(adminService.getModelResponse(id)).build();
    }

    // ---------------------- Color --------------------

    @POST
    @Path("/color")
    public Response createColor(@Valid CarParameterRequest carParameterRequest)
            throws UnauthorizedException, PrivilegeException, EntityAlreadyExistException, EntityNotFoundException {
        checkAdmin();
        log.debug("Create color {}", carParameterRequest.getName());
        return Response.status(200).entity(adminService.createColor(carParameterRequest)).build();
    }

    @DELETE
    @Path("/color/{id}")
    public Response deleteColor(@PathParam("id") Long id)
            throws UnauthorizedException, PrivilegeException, EntityNotFoundException {
        adminService.deleteColor(id);
        checkAdmin();
        log.debug("Delete color with id {}", id);
        return Response.status(200).build();
    }

    @PUT
    @Path("/color/{id}")
    public Response updateColor(@PathParam("id") Long id, @Valid CarParameterRequest carParameterRequest)
            throws UnauthorizedException, PrivilegeException, EntityAlreadyExistException, EntityNotFoundException {
        checkAdmin();
        log.debug("Update color with id {}", id);
        return Response.status(200).entity(adminService.updateColor(id, carParameterRequest)).build();
    }

    @GET
    @Path("/color")
    public Response getAllColors(@DefaultValue("1") @QueryParam(value = "page") Integer page,
                                 @DefaultValue("100") @QueryParam(value = "size") Integer size,
                                 @DefaultValue("name") @QueryParam(value = "sort") String sort,
                                 @DefaultValue("asc") @QueryParam(value = "direction") String direction) {
        log.debug("Get all colors");
        return Response.status(200).entity(adminService.getAllColors(page, size, sort, direction)).build();
    }

    @GET
    @Path("/color/{id}")
    public Response getColor(@PathParam("id") Long id) throws EntityNotFoundException {
        log.debug("Get color with id {}", id);
        return Response.status(200).entity(adminService.getColor(id)).build();
    }

    // ---------------------- Drive unit --------------------

    @POST
    @Path("/drive-unit")
    public Response createDriveUnit(@Valid CarParameterRequest carParameterRequest)
            throws UnauthorizedException, PrivilegeException, EntityAlreadyExistException, EntityNotFoundException {
        checkAdmin();
        log.debug("Create driveUnit {}", carParameterRequest.getName());
        return Response.status(200).entity(adminService.createDriveUnit(carParameterRequest)).build();
    }

    @DELETE
    @Path("/drive-unit/{id}")
    public Response deleteDriveUnit(@PathParam("id") Long id)
            throws UnauthorizedException, PrivilegeException, EntityNotFoundException {
        checkAdmin();
        log.debug("Delete driveUnit with id {}", id);
        adminService.deleteDriveUnit(id);
        return Response.status(200).build();
    }

    @PUT
    @Path("/drive-unit/{id}")
    public Response updateDriveUnit(@PathParam("id") Long id, @Valid CarParameterRequest carParameterRequest)
            throws UnauthorizedException, PrivilegeException, EntityAlreadyExistException, EntityNotFoundException {
        checkAdmin();
        log.debug("Update driveUnit with id {}", id);
        return Response.status(200).entity(adminService.updateDriveUnit(id, carParameterRequest)).build();
    }

    @GET
    @Path("/drive-unit")
    public Response getAllDriveUnits(@DefaultValue("1") @QueryParam(value = "page") Integer page,
                                     @DefaultValue("100") @QueryParam(value = "size") Integer size,
                                     @DefaultValue("name") @QueryParam(value = "sort") String sort,
                                     @DefaultValue("asc") @QueryParam(value = "direction") String direction) {
        log.debug("Get all driveUnits");
        return Response.status(200).entity(adminService.getAllDriveUnits(page, size, sort, direction)).build();
    }

    @GET
    @Path("/drive-unit/{id}")
    public Response getDriveUnit(@PathParam("id") Long id) throws EntityNotFoundException {
        log.debug("Get driveUnit with id {}", id);
        return Response.status(200).entity(adminService.getDriveUnit(id)).build();
    }

    // ---------------------- Fuel type --------------------

    @POST
    @Path("/fuel-type")
    public Response createFuelType(@Valid CarParameterRequest carParameterRequest)
            throws UnauthorizedException, PrivilegeException, EntityAlreadyExistException, EntityNotFoundException {
        checkAdmin();
        log.debug("Create fuelType {}", carParameterRequest.getName());
        return Response.status(200).entity(adminService.createFuelType(carParameterRequest)).build();
    }

    @DELETE
    @Path("/fuel-type/{id}")
    public Response deleteFuelType(@PathParam("id") Long id)
            throws UnauthorizedException, PrivilegeException, EntityNotFoundException {
        checkAdmin();
        log.debug("Delete fuelType with id {}", id);
        adminService.deleteFuelType(id);
        return Response.status(200).build();
    }

    @PUT
    @Path("/fuel-type/{id}")
    public Response updateFuelType(@PathParam("id") Long id, @Valid CarParameterRequest carParameterRequest)
            throws UnauthorizedException, PrivilegeException, EntityAlreadyExistException, EntityNotFoundException {
        checkAdmin();
        log.debug("Update fuelType with id {}", id);
        return Response.status(200).entity(adminService.updateFuelType(id, carParameterRequest)).build();
    }

    @GET
    @Path("/fuel-type")
    public Response getAllFuelTypes(@DefaultValue("1") @QueryParam(value = "page") Integer page,
                                    @DefaultValue("100") @QueryParam(value = "size") Integer size,
                                    @DefaultValue("name") @QueryParam(value = "sort") String sort,
                                    @DefaultValue("asc") @QueryParam(value = "direction") String direction) {
        log.debug("Get all fuelTypes");
        return Response.status(200).entity(adminService.getAllFuelTypes(page, size, sort, direction)).build();
    }

    @GET
    @Path("/fuel-type/{id}")
    public Response getFuelType(@PathParam("id") Long id) throws EntityNotFoundException {
        log.debug("Get fuelType with id {}", id);
        return Response.status(200).entity(adminService.getFuelType(id)).build();
    }

    // ---------------------- Gearbox --------------------

    @POST
    @Path("/gearbox")
    public Response createGearbox(@Valid CarParameterRequest carParameterRequest)
            throws UnauthorizedException, PrivilegeException, EntityAlreadyExistException, EntityNotFoundException {
        checkAdmin();
        log.debug("Create gearbox {}", carParameterRequest.getName());
        return Response.status(200).entity(adminService.createGearbox(carParameterRequest)).build();
    }

    @DELETE
    @Path("/gearbox/{id}")
    public Response deleteGearbox(@PathParam("id") Long id)
            throws UnauthorizedException, PrivilegeException, EntityNotFoundException {
        checkAdmin();
        log.debug("Delete gearbox with id {}", id);
        adminService.deleteGearbox(id);
        return Response.status(200).build();
    }

    @PUT
    @Path("/gearbox/{id}")
    public Response updateGearbox(@PathParam("id") Long id, @Valid CarParameterRequest carParameterRequest)
            throws UnauthorizedException, PrivilegeException, EntityAlreadyExistException, EntityNotFoundException {
        checkAdmin();
        log.debug("Update gearbox with id {}", id);
        return Response.status(200).entity(adminService.updateGearbox(id, carParameterRequest)).build();
    }

    @GET
    @Path("/gearbox")
    public Response getAllGearboxes(@DefaultValue("1") @QueryParam(value = "page") Integer page,
                                    @DefaultValue("100") @QueryParam(value = "size") Integer size,
                                    @DefaultValue("name") @QueryParam(value = "sort") String sort,
                                    @DefaultValue("asc") @QueryParam(value = "direction") String direction) {
        log.debug("Get all gearboxs");
        return Response.status(200).entity(adminService.getAllGearboxes(page, size, sort, direction)).build();
    }

    @GET
    @Path("/gearbox/{id}")
    public Response getGearbox(@PathParam("id") Long id) throws EntityNotFoundException {
        log.debug("Get gearbox with id {}", id);
        return Response.status(200).entity(adminService.getGearbox(id)).build();
    }

    // ---------------------- Interior material --------------------

    @POST
    @Path("/interior-material")
    public Response createInteriorMaterial(@Valid CarParameterRequest carParameterRequest)
            throws UnauthorizedException, PrivilegeException, EntityAlreadyExistException, EntityNotFoundException {
        checkAdmin();
        log.debug("Create interiorMaterial {}", carParameterRequest.getName());
        return Response.status(200).entity(adminService.createInteriorMaterial(carParameterRequest)).build();
    }

    @DELETE
    @Path("/interior-material/{id}")
    public Response deleteInteriorMaterial(@PathParam("id") Long id)
            throws UnauthorizedException, PrivilegeException, EntityNotFoundException {
        checkAdmin();
        log.debug("Delete interiorMaterial with id {}", id);
        adminService.deleteInteriorMaterial(id);
        return Response.status(200).build();
    }

    @PUT
    @Path("/interior-material/{id}")
    public Response updateInteriorMaterial(@PathParam("id") Long id, @Valid CarParameterRequest carParameterRequest)
            throws UnauthorizedException, PrivilegeException, EntityAlreadyExistException, EntityNotFoundException {
        checkAdmin();
        log.debug("Update interiorMaterial with id {}", id);
        return Response.status(200).entity(adminService.updateInteriorMaterial(id, carParameterRequest)).build();
    }

    @GET
    @Path("/interior-material")
    public Response getAllInteriorMaterials(@DefaultValue("1") @QueryParam(value = "page") Integer page,
                                            @DefaultValue("100") @QueryParam(value = "size") Integer size,
                                            @DefaultValue("name") @QueryParam(value = "sort") String sort,
                                            @DefaultValue("asc") @QueryParam(value = "direction") String direction) {
        log.debug("Get all interiorMaterials");
        return Response.status(200).entity(adminService.getAllInteriorMaterials(page, size, sort, direction)).build();
    }

    @GET
    @Path("/interior-material/{id}")
    public Response getInteriorMaterial(@PathParam("id") Long id) throws EntityNotFoundException {
        log.debug("Get interiorMaterial with id {}", id);
        return Response.status(200).entity(adminService.getInteriorMaterial(id)).build();
    }

    // ---------------------- Tires season --------------------

    @POST
    @Path("/tires-season")
    public Response createTiresSeason(@Valid CarParameterRequest carParameterRequest)
            throws UnauthorizedException, PrivilegeException, EntityAlreadyExistException, EntityNotFoundException {
        checkAdmin();
        log.debug("Create tiresSeason {}", carParameterRequest.getName());
        return Response.status(200).entity(adminService.createTiresSeason(carParameterRequest)).build();
    }

    @DELETE
    @Path("/tires-season/{id}")
    public Response deleteTiresSeason(@PathParam("id") Long id)
            throws UnauthorizedException, PrivilegeException, EntityNotFoundException {
        checkAdmin();
        log.debug("Delete tiresSeason with id {}", id);
        adminService.deleteTiresSeason(id);
        return Response.status(200).build();
    }

    @PUT
    @Path("/tires-season/{id}")
    public Response updateTiresSeason(@PathParam("id") Long id, @Valid CarParameterRequest carParameterRequest)
            throws UnauthorizedException, PrivilegeException, EntityAlreadyExistException, EntityNotFoundException {
        checkAdmin();
        log.debug("Update tiresSeason with id {}", id);
        return Response.status(200).entity(adminService.updateTiresSeason(id, carParameterRequest)).build();
    }

    @GET
    @Path("/tires-season")
    public Response getAllTiresSeasons(@DefaultValue("1") @QueryParam(value = "page") Integer page,
                                       @DefaultValue("100") @QueryParam(value = "size") Integer size,
                                       @DefaultValue("name") @QueryParam(value = "sort") String sort,
                                       @DefaultValue("asc") @QueryParam(value = "direction") String direction) {
        log.debug("Get all tiresSeasons");
        return Response.status(200).entity(adminService.getAllTiresSeasons(page, size, sort, direction)).build();
    }

    @GET
    @Path("/tires-season/{id}")
    public Response getTiresSeason(@PathParam("id") Long id) throws EntityNotFoundException {
        log.debug("Get tiresSeason with id {}", id);
        return Response.status(200).entity(adminService.getTiresSeason(id)).build();
    }

    // ------------------------- Confirm Profile --------------------

    @GET
    @Path("/confirm-profile")
    public Response getProfilesToConfirm(@DefaultValue("1") @QueryParam(value = "page") Integer page,
                                         @DefaultValue("100") @QueryParam(value = "size") Integer size,
                                         @DefaultValue("id") @QueryParam(value = "sort") String sort,
                                         @DefaultValue("asc") @QueryParam(value = "direction") String direction)
            throws EntityNotFoundException, UnauthorizedException, PrivilegeException {
        checkAdmin();
        log.debug("Get profiles to confirm. Page {}, size {}, sort {}, direction {}", page, size, sort, direction);
        return Response.status(200).entity(adminService.getProfilesToConfirm(page, size, sort, direction)).build();
    }

    @PUT
    @Path("/profile/{id}/confirm")
    public Response setConfirmProfileYes(@PathParam("id") Long id)
            throws EntityNotFoundException, UnauthorizedException, PrivilegeException, ConfirmProfileException {
        checkAdmin();
        log.debug("Confirm profile with id {}", id);
        return Response.status(200).entity(adminService.setConfirmProfileYes(id)).build();
    }

    @PUT
    @Path("/profile/{id}/not-confirm")
    public Response setConfirmProfileNo(@PathParam("id") Long id)
            throws EntityNotFoundException, UnauthorizedException, PrivilegeException, ConfirmProfileException {
        checkAdmin();
        log.debug("Not confirm profile with id {}", id);
        return Response.status(200).entity(adminService.setConfirmProfileNo(id)).build();
    }

    @GET
    @Path("confirmation")
    public Response getConfirmations(@DefaultValue("1") @QueryParam(value = "page") Integer page,
                                     @DefaultValue("100") @QueryParam(value = "size") Integer size,
                                     @DefaultValue("dateConfirm") @QueryParam(value = "sort") String sort,
                                     @DefaultValue("desc") @QueryParam(value = "direction") String direction)
            throws EntityNotFoundException, PrivilegeException, UnauthorizedException {
        checkAdmin();
        log.debug("Get confirmations. Page {}, size {}, sort {}, direction {}", page, size, sort, direction);
        return Response.status(200).entity(adminService.getConfirmations(page, size, sort, direction)).build();
    }
}