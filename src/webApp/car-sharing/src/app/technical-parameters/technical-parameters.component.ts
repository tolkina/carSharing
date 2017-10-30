import {Component, OnInit} from '@angular/core';
import {ProfileCarService} from "../service/profile-car.service";
import {clone} from "lodash";
import {ActivatedRoute} from '@angular/router'
import {CarParameter} from "../domain/car-parameter";
import {CarParameterService} from "../service/car-parameter.service";

@Component({
  selector: 'app-technical-parameters',
  templateUrl: './technical-parameters.component.html',
  styleUrls: ['./technical-parameters.component.css']
})
export class TechnicalParametersComponent implements OnInit {
  carId: number;
  technicalParameters: any = {};
  editedTechnicalParameters: any = {};
  gearboxes: CarParameter[] = [];
  fuelTypes: CarParameter[] = [];
  bodyTypes: CarParameter[] = [];
  driveUnits: CarParameter[] = [];
  tiresSeasons: CarParameter[] = [];
  interiorMaterials: CarParameter[] = [];
  colors: CarParameter[] = [];

  constructor(private carService: ProfileCarService, private activateRoute: ActivatedRoute,
              private carParameterService: CarParameterService) {
    this.carId = activateRoute.snapshot.parent.params['carId'];
  }

  ngOnInit() {
    this.getTechnicalParameters(this.carId);
  }

  updateTechnicalParameters() {
    this.carService.updateTechnicalParameters(this.editedTechnicalParameters, this.carId).then()
      .then(res => this.technicalParameters = res)
      .catch();
  }

  getTechnicalParameters(carId: number) {
    this.carService.getTechnicalParameters(carId).then()
      .then(res => this.technicalParameters = res)
      .catch();
  }

  editParams() {
    this.editedTechnicalParameters = clone(this.technicalParameters);
    this.getCarParams();
  }

  private getCarParams() {
    this.carParameterService.getGearboxes().then(param => {
      this.gearboxes = param;
      if (param.length && !this.editedTechnicalParameters.gearbox) {
        this.editedTechnicalParameters.gearbox = param[0].name
      }
    });
    this.carParameterService.getBodyTypes().then(param => {
      this.bodyTypes = param;
      if (param.length && !this.editedTechnicalParameters.bodyType) {
        this.editedTechnicalParameters.bodyType = param[0].name
      }
    });
    this.carParameterService.getColors().then(param => {
      this.colors = param;
      if (param.length && !this.editedTechnicalParameters.color) {
        this.editedTechnicalParameters.color = param[0].name
      }
    });
    this.carParameterService.getDriveUnits().then(param => {
      this.driveUnits = param;
      if (param.length && !this.editedTechnicalParameters.driveUnit) {
        this.editedTechnicalParameters.driveUnit = param[0].name
      }
    });
    this.carParameterService.getFuelTypes().then(param => {
      this.fuelTypes = param;
      if (param.length && !this.editedTechnicalParameters.fuelType) {
        this.editedTechnicalParameters.fuelType = param[0].name
      }
    });
    this.carParameterService.getInteriorMaterials().then(param => {
      this.interiorMaterials = param;
      if (param.length && !this.editedTechnicalParameters.interiorMaterial) {
        this.editedTechnicalParameters.interiorMaterial = param[0].name
      }
    });
    this.carParameterService.getTiresSeasons().then(param => {
      this.tiresSeasons = param;
      if (param.length && !this.editedTechnicalParameters.tiresSeason) {
        this.editedTechnicalParameters.tiresSeason = param[0].name
      }
    });
  }
}


