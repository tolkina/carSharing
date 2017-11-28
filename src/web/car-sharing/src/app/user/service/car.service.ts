import {Injectable} from '@angular/core';
import {Http, RequestOptions} from "@angular/http";
import {Car} from "../domain/car";
import {GeneralParameters} from "../domain/general-parameters";
import {TechnicalParameters} from "../domain/technical-parameters";
import {CurrentCondition} from "../domain/current-condition";
import {CarPhotos} from "../domain/car-photos";
import {PageParameter} from "../domain/page-parameter";
import {PageCar} from "../domain/page-car";

@Injectable()
export class CarService {

  private profileUrl = '/api/profile/';
  private carUrl = '/api/car/';
  private generalParametersUrl = "/general-parameters/";
  private technicalParametersUrl = "/technical-parameters/";
  private currentConditionUrl = "/current-condition/";

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

  getCarsOfPrincipal(pageParameter: PageParameter) {
    return this.http.get(this.profileUrl + "car", {
      params: {
        page: pageParameter.page,
        size: pageParameter.size,
        sort: pageParameter.sort,
        direction: pageParameter.direction
      }
    })
      .toPromise()
      .then(pageCar => pageCar.json() as PageCar)
      .catch(this.handleError);
  }

  getCarsWithoutAdOfPrincipal() {
    return this.http.get(this.profileUrl + "car-without-ad")
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

  uploadPhotos(files: FormData, carId: number) {
    return this.http.put(this.carUrl + carId + this.generalParametersUrl + "photos", files)
      .toPromise()
      .then(res => res.json() as GeneralParameters)
      .catch(this.handleError);
  }

  deletePhotos(photos: CarPhotos, carId: number) {
    return this.http.delete(this.carUrl + carId + this.generalParametersUrl + "photos",
      new RequestOptions({body: photos}))
      .toPromise()
      .then(res => res.json() as GeneralParameters)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occured', error);
    return Promise.reject(error.json().message || error)
  }
}
