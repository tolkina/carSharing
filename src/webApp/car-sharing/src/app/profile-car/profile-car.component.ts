import {Component, OnInit} from '@angular/core';
import {ProfileCarService} from "../service/profile-car.service";
import {Car} from "../domain/car";
import {GeneralParameters} from "../domain/generalParameters";
import {TechnicalParameters} from "../domain/technicalParameters";
import {CurrentCondition} from "../domain/currentCondition";

@Component({
  selector: 'app-profile-car',
  templateUrl: './profile-car.component.html',
  styleUrls: ['./profile-car.component.css']
})
export class ProfileCarComponent implements OnInit {
  cars: Car[];
  car: Car;
  profileId: number;
  generalParameters: GeneralParameters;
  technicalParameters: TechnicalParameters;
  currentCondition: CurrentCondition;

  constructor(private carService: ProfileCarService) {
  }

  ngOnInit() {
    this.profileId = 1;
    this.getCarsByOwner(this.profileId);
  }

  getCarsByOwner(ownerId: number) {
    this.carService.getCarsByOwner(ownerId).then()
      .then(cars => this.cars = cars)
      .catch();
  }

  addCar(car: Car) {
    this.carService.addCar(car).then()
      .then(car => this.car = car)
      .catch();
  }

  deleteCar(carId: number) {
    this.carService.deleteCar(carId).then()
      .then()
      .catch();
  }

  getAllCars() {
    this.carService.getAllCars().then()
      .then(cars => this.cars = cars)
      .catch();
  }

  updateGeneralParameters(generalParameters: GeneralParameters, carId: number) {
    this.carService.updateGeneralParameters(generalParameters, carId).then()
      .then(res => this.generalParameters = res)
      .catch();
  }

  updateTechnicalParameters(technicalParameters: TechnicalParameters, carId: number) {
    this.carService.updateTechnicalParameters(technicalParameters, carId).then()
      .then(res => this.technicalParameters = res)
      .catch();
  }

  updateCurrentCondition(currentCondition: CurrentCondition, carId: number) {
    this.carService.updateCurrentCondition(currentCondition, carId).then()
      .then(res => this.currentCondition = res)
      .catch();
  }

  getGeneralParameters(carId: number) {
    this.carService.getGeneralParameters(carId).then()
      .then(res => this.generalParameters = res)
      .catch();
  }

  getTechnicalParameters(carId: number) {
    this.carService.getTechnicalParameters(carId).then()
      .then(res => this.technicalParameters = res)
      .catch();
  }

  getCurrentCondition(carId: number) {
    this.carService.getCurrentCondition(this.profileId).then()
      .then(res => this.currentCondition = res)
      .catch();
  }
}
