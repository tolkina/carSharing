import 'rxjs/add/operator/toPromise';
import {Injectable} from '@angular/core';
import {Http} from "@angular/http";
import {PassportData} from "../domain/passport-data";

@Injectable()
export class PassportDataService {

  private passportUrl = '/api/passport-data';

  constructor(private http: Http) {
  }

  getPassport(): Promise<PassportData> {
    return this.http.get(this.passportUrl)
      .toPromise()
      .then(response => response.json() as PassportData)
      .catch(this.handleError);
  }

  updatePassport(passportData: PassportData): Promise<PassportData> {
    return this.http
      .put(this.passportUrl, passportData)
      .toPromise()
      .then(response => response.json() as PassportData)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occured', error);
    return Promise.reject(error._body || error)
  }
}
