import {Injectable} from '@angular/core';
import {Http} from "@angular/http";
import {Car} from "../domain/car";
import {GeneralParameters} from "../domain/generalParameters";
import {TechnicalParameters} from "../domain/technicalParameters";
import {CurrentCondition} from "../domain/currentCondition";

@Injectable()
export class ProfileCarService {

  private profileUrl = '/api/profile/';
  private carUrl = '/api/car/';
  private generalParametersUrl = "/general-parameters";
  private technicalParametersUrl = "/technical-parameters";
  private currentConditionUrl = "/current-condition";

  constructor(private http: Http) {
  }

  addCar(car: Car) {
    return this.http.post(this.carUrl, car)
      .toPromise()
      .then(res => res.json() as Car)
      .catch(this.handleError);
  }

  deleteCar(carId: number) {
    return this.http.delete(this.carUrl + carId)
      .toPromise()
      .then()
      .catch(this.handleError);
  }

  getCar(carId: number) {
    return this.http.get(this.carUrl + carId)
      .toPromise()
      .then(res => res.json() as Car)
      .catch(this.handleError);
  }

  getCarsOfPrincipal() {
    return this.http.get(this.profileUrl + "/car")
      .toPromise()
      .then(res => res.json() as Car[])
      .catch(this.handleError);
  }

  getAllCars() {
    return this.http.get(this.carUrl)
      .toPromise()
      .then(res => res.json() as Car[])
      .catch(this.handleError);
  }

  updateGeneralParameters(generalParameters: GeneralParameters, carId: number) {
    return this.http.put(this.carUrl + carId + this.generalParametersUrl, generalParameters)
      .toPromise()
      .then(res => res.json() as GeneralParameters)
      .catch(this.handleError);
  }

  updateTechnicalParameters(technicalParameters: TechnicalParameters, carId: number) {
    return this.http.put(this.carUrl + carId + this.technicalParametersUrl, technicalParameters)
      .toPromise()
      .then(res => res.json() as TechnicalParameters)
      .catch(this.handleError);
  }

  updateCurrentCondition(currentCondition: CurrentCondition, carId: number) {
    return this.http.put(this.carUrl + carId + this.currentConditionUrl, currentCondition)
      .toPromise()
      .then(res => res.json() as CurrentCondition)
      .catch(this.handleError);
  }

  getGeneralParameters(carId: number) {
    return this.http.get(this.carUrl + carId + this.generalParametersUrl)
      .toPromise()
      .then(res => res.json() as GeneralParameters)
      .catch(this.handleError);
  }

  getTechnicalParameters(carId: number) {
    return this.http.get(this.carUrl + carId + this.technicalParametersUrl)
      .toPromise()
      .then(res => res.json() as TechnicalParameters)
      .catch(this.handleError);
  }

  getCurrentCondition(carId: number) {
    return this.http.get(this.carUrl + carId + this.currentConditionUrl)
      .toPromise()
      .then(res => res.json() as CurrentCondition)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occured', error);
    return Promise.reject(error.json().message || error)
  }
}
