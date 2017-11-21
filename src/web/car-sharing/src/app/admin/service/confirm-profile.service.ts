import 'rxjs/add/operator/toPromise';
import {Injectable} from '@angular/core';
import {Http} from "@angular/http";
import {ConfirmProfile} from "../domain/confirm-profile";
import {Confirmation} from "../domain/confirmation";

@Injectable()
export class ConfirmProfileService {

  private adminUrl = '/api/admin';

  constructor(private http: Http) {
  }

  notConfirmProfile(id: number) {
    return this.http.put(this.adminUrl + "/profile/" + id + "/not-confirm", {})
      .toPromise()
      .then(res => res.json() as Confirmation)
      .catch(this.handleError);
  }

  confirmProfile(id: number) {
    return this.http.put(this.adminUrl + "/profile/" + id + "/confirm", {})
      .toPromise()
      .then(res => res.json() as Confirmation)
      .catch(this.handleError);
  }

  getProfilesForConfirmation() {
    return this.http.get(this.adminUrl + "/confirm-profile")
      .toPromise()
      .then(this.extractData)
      .catch(this.handleError);
  }

  getConfirmations() {
    return this.http.get(this.adminUrl + "/confirmation")
      .toPromise()
      .then(res => res.json() as Confirmation[])
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occured', error);
    return Promise.reject(error.json().message || error.json())
  }

  private extractData(res) {
    let body = res.json();
    return body as ConfirmProfile || {};
  }
}
