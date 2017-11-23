import {Component, OnInit} from '@angular/core';
import {ProfileCarService} from "../../../../service/profile-car.service";
import {Router} from '@angular/router'
import {CarParameterService} from "../../../../service/car-parameter.service";
import {Brand_} from "../../../../domain/brand_";
import {CarParameter} from "../../../../domain/car-parameter";
import {Car} from "../../../../domain/car";
import {Model} from "../../../../domain/model";

@Component({
  selector: 'app-new-car',
  templateUrl: './new-car.component.html',
  styleUrls: ['./new-car.component.css']
})
export class NewCarComponent implements OnInit {
  car: Car = new Car;
  models: Model[] = [];
  error = "";
  brands: Brand_[] = [];
  gearboxes: CarParameter[] = [];
  fuelTypes: CarParameter[] = [];
  bodyTypes: CarParameter[] = [];
  driveUnits: CarParameter[] = [];
  tiresSeasons: CarParameter[] = [];
  interiorMaterials: CarParameter[] = [];
  colors: CarParameter[] = [];
  years: number[] = [];

  constructor(private carService: ProfileCarService, private router: Router,
              private carParameterService: CarParameterService) {
  }

  ngOnInit() {
    this.getCarParams();
    this.setYears();
  }

  onSubmit() {
    this.saveCar()
  }

  saveCar() {
    this.carService.addCar(this.car).then()
      .then(res => this.router.navigateByUrl('profile/car/all'))
      .catch(error => this.error = error);
  }

  onChangeBrand(brandName: string) {
    this.getModelsByBrand(this.findBrand(brandName).id)
  }

  private findBrand(brandName: string) {
    for (let i = 0; i < this.brands.length; i++) {
      if (this.brands[i].name == brandName) {
        return this.brands[i]
      }
    }
  }

  private getModelsByBrand(brandId: number) {
    this.carParameterService.getModelsByBrand(brandId).then(models => {
      this.models = models;
      if (models.length > 0) {
        this.car.generalParameters.model = models[0].name;
      }
      else {
        this.car.generalParameters.model = ""
      }
    });
  }

  private getCarParams() {
    this.carParameterService.getBrands().then(brands => {
      this.brands = brands;
      if (brands.length > 0) {
        this.car.generalParameters.brand = brands[0].name;
        this.getModelsByBrand(brands[0].id);
      }
    });

    this.carParameterService.getGearboxes().then(param => {
      this.gearboxes = param;
      if (param.length > 0) {
        this.car.technicalParameters.gearbox = param[0].name
      }
    });
    this.carParameterService.getBodyTypes().then(param => {
      this.bodyTypes = param;
      if (param.length > 0) {
        this.car.technicalParameters.bodyType = param[0].name
      }
    });
    this.carParameterService.getColors().then(param => {
      this.colors = param;
      if (param.length > 0) {
        this.car.technicalParameters.color = param[0].name
      }
    });
    this.carParameterService.getDriveUnits().then(param => {
      this.driveUnits = param;
      if (param.length > 0) {
        this.car.technicalParameters.driveUnit = param[0].name
      }
    });
    this.carParameterService.getFuelTypes().then(param => {
      this.fuelTypes = param;
      if (param.length > 0) {
        this.car.technicalParameters.fuelType = param[0].name
      }
    });
    this.carParameterService.getInteriorMaterials().then(param => {
      this.interiorMaterials = param;
      if (param.length > 0) {
        this.car.technicalParameters.interiorMaterial = param[0].name
      }
    });
    this.carParameterService.getTiresSeasons().then(param => {
      this.tiresSeasons = param;
      if (param.length > 0) {
        this.car.technicalParameters.tiresSeason = param[0].name
      }
    });
  }

  private setYears() {
    for (let i = 2018; i >= 1900; i--) {
      this.years.push(i)
    }
    this.car.generalParameters.yearOfIssue = this.years[0];
  }
}
