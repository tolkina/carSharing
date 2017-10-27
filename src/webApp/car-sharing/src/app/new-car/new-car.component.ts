import {Component, OnInit} from '@angular/core';
import {ProfileCarService} from "../service/profile-car.service";
import {Router} from '@angular/router'

@Component({
  selector: 'app-new-car',
  templateUrl: './new-car.component.html',
  styleUrls: ['./new-car.component.css']
})
export class NewCarComponent implements OnInit {
  car: any = {};
  carId: number;
  technicalParametersId: number;
  generalParametersId: number;
  currentConditionId: number;
  generalParameters: any = {};
  technicalParameters: any = {};
  currentCondition: any = {};
  error: string;

  constructor(private carService: ProfileCarService, private router: Router) {
  }

  ngOnInit() {
    this.addCar();
  }

  addCar() {
    this.carService.addCar(this.car).then()
      .then(car => {
        this.carId = car.id;
        this.technicalParametersId = car.technicalParameters.id;
        this.generalParametersId = car.generalParameters.id;
        this.currentConditionId = car.currentCondition.id;
      })
      .catch();
  }

  updateGeneralParameters() {
    this.generalParameters.id = this.generalParametersId;
    this.carService.updateGeneralParameters(this.generalParameters, this.carId).then()
      .then(res => this.generalParameters = res)
      .catch(error => this.error = error);
  }

  updateCurrentCondition() {
    this.currentCondition.id = this.currentConditionId;
    this.carService.updateCurrentCondition(this.currentCondition, this.carId).then()
      .then(res => this.currentCondition = res)
      .catch(error => this.error = error);
  }

  updateTechnicalParameters() {
    this.technicalParameters.id = this.technicalParametersId;
    this.carService.updateTechnicalParameters(this.technicalParameters, this.carId).then()
      .then(res => this.technicalParameters = res)
      .catch(error => this.error = error);
  }

  saveCar() {
    this.updateGeneralParameters();
    this.updateTechnicalParameters();
    this.updateCurrentCondition();
    if (!this.error) {
      this.router.navigateByUrl('profile/car/all');
    }
  }
}
