import 'rxjs/add/operator/toPromise';
import {Http} from "@angular/http";
import { Injectable } from '@angular/core';
import {DriverLicense} from "./driver-license";

@Injectable()
export class DriverLicenseService {

  private driverLicenseUrl = '/api/driver-license';

  constructor(private http: Http) { }

  getDriverLicense(id: number): Promise<DriverLicense> {
    const url = `${this.driverLicenseUrl}/${id}`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as DriverLicense)
      .catch(this.handleError);
  }

  updateDriverLicense(driverLicense:DriverLicense, id:number): Promise<DriverLicense> {
    const url = `${this.driverLicenseUrl}/${id}`;
    return this.http
      .put(url, driverLicense)
      .toPromise()
      .then(() => driverLicense)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occured', error);
    return Promise.reject(error.message || error)
  }
}
