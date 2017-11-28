import 'rxjs/add/operator/toPromise';
import {Http} from "@angular/http";
import {Injectable} from '@angular/core';
import {DriverLicense} from "../domain/driver-license";

@Injectable()
export class DriverLicenseService {

  private driverLicenseUrl = '/api/driver-license/';

  constructor(private http: Http) {
  }

  getDriverLicense(): Promise<DriverLicense> {
    return this.http.get(this.driverLicenseUrl)
      .toPromise()
      .then(response => response.json() as DriverLicense)
      .catch(this.handleError);
  }

  updateDriverLicense(driverLicense: DriverLicense): Promise<DriverLicense> {
    return this.http
      .put(this.driverLicenseUrl, driverLicense)
      .toPromise()
      .then(response => response.json() as DriverLicense)
      .catch(this.handleError);
  }

  uploadFrontSidePhoto(file: FormData) {
    return this.http
      .put(this.driverLicenseUrl + "photo/front-side", file)
      .toPromise()
      .then(driverLicense => driverLicense.json() as DriverLicense)
      .catch(this.handleError)
  }

  uploadBackSidePhoto(file: FormData) {
    return this.http
      .put(this.driverLicenseUrl + "photo/back-side", file)
      .toPromise()
      .then(driverLicense => driverLicense.json() as DriverLicense)
      .catch(this.handleError)
  }

  deleteFrontSidePhoto() {
    return this.http
      .delete(this.driverLicenseUrl + "photo/front-side")
      .toPromise()
      .then(driverLicense => driverLicense.json() as DriverLicense)
      .catch(this.handleError)
  }

  deleteBackSidePhoto() {
    return this.http
      .delete(this.driverLicenseUrl + "photo/back-side")
      .toPromise()
      .then(driverLicense => driverLicense.json() as DriverLicense)
      .catch(this.handleError)
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occured', error);
    return Promise.reject(error.json().message || error)
  }
}
