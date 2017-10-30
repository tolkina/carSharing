import {Component, OnInit} from '@angular/core';
import {ProfileCarService} from "../service/profile-car.service";
import {Router} from '@angular/router'
import {CarParameterService} from "../service/car-parameter.service";
import {Brand_} from "../domain/brand_";
import {CarParameter} from "../domain/car-parameter";
import {Car} from "../domain/car";
import {GeneralParameters} from "../domain/generalParameters";
import {TechnicalParameters} from "../domain/technicalParameters";
import {CurrentCondition} from "../domain/currentCondition";

@Component({
  selector: 'app-new-car',
  templateUrl: './new-car.component.html',
  styleUrls: ['./new-car.component.css']
})
export class NewCarComponent implements OnInit {
  car: Car = {
    id: null,
    currentCondition: new CurrentCondition(),
    generalParameters: new GeneralParameters(),
    technicalParameters: new TechnicalParameters(),
    owner: null
  };
  error = "";
  brands: Brand_[] = [];
  models: CarParameter[] = [];
  gearboxes: CarParameter[] = [];
  fuelTypes: CarParameter[] = [];
  bodyTypes: CarParameter[] = [];
  driveUnits: CarParameter[] = [];
  tiresSeasons: CarParameter[] = [];
  interiorMaterials: CarParameter[] = [];
  colors: CarParameter[] = [];

  constructor(private carService: ProfileCarService, private router: Router,
              private carParameterService: CarParameterService) {
  }

  ngOnInit() {
    this.getCarParams();
  }

  saveCar() {
    this.carService.addCar(this.car).then()
      .then(res => this.router.navigateByUrl('profile/car/all'))
      .catch(error => this.error = error);
  }

  private getCarParams() {
    this.carParameterService.getBrands().then(param => {
      this.brands = param;
      // if (param.length > 0) {
      //   this.generalParameters.brand = param[0]
      // }
    });
    this.carParameterService.getModels().then(param => {
      this.models = param;
      // if (param.length > 0) {
      //   this.generalParameters.model = param[0]
      // }
    });
    this.carParameterService.getGearboxes().then(param => {
      this.gearboxes = param;
      // if (param.length > 0) {
      //   this.technicalParameters.gearbox = param[0]
      // }
    });
    this.carParameterService.getBodyTypes().then(param => {
      this.bodyTypes = param;
      // if (param.length > 0) {
      //   this.technicalParameters.bodyType = param[0]
      // }
    });
    this.carParameterService.getColors().then(param => {
      this.colors = param;
      // if (param.length > 0) {
      //   this.technicalParameters.color = param[0]
      // }
    });
    this.carParameterService.getDriveUnits().then(param => {
      this.driveUnits = param;
      // if (param.length > 0) {
      //   this.technicalParameters.driveUnit = param[0];
      // }
    });
    this.carParameterService.getFuelTypes().then(param => {
      this.fuelTypes = param;
      // if (param.length > 0) {
      //   this.technicalParameters.fuelType = param[0]
      // }
    });
    this.carParameterService.getInteriorMaterials().then(param => {
      this.interiorMaterials = param;
      // if (param.length > 0) {
      //   this.technicalParameters.interiorMaterial = param[0]
      // }
    });
    this.carParameterService.getTiresSeasons().then(param => {
      this.tiresSeasons = param;
      // if (param.length > 0) {
      //   this.technicalParameters.tiresSeason = param[0]
      // }
    });
  }
}
