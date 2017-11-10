import 'rxjs/add/operator/toPromise';
import {Injectable} from '@angular/core';
import {Http} from "@angular/http";
import {ConfirmProfile} from "../domain/confirm-profile";

@Injectable()
export class ConfirmProfileService {

  private adminUrl = '/api/admin';

  constructor(private http: Http) {
  }

  notConfirmProfile(id: number) {
    return this.http.put(this.adminUrl + "/profile/" + id + "/not-confirm", {})
      .toPromise()
      .then()
      .catch(this.handleError);
  }

  confirmProfile(id: number) {
    return this.http.put(this.adminUrl + "/profile/" + id + "/confirm", {})
      .toPromise()
      .then()
      .catch(this.handleError);
  }

  getProfilesForConfirmation() {
    return this.http.get(this.adminUrl + "/confirm-profile")
      .toPromise()
      .then(this.extractData)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occured', error);
    return Promise.reject(error.json().message || error)
  }

  private extractData(res) {
    let body = res.json();
    return body as ConfirmProfile || {};
  }
}
