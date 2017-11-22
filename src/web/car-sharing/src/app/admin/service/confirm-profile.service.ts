import 'rxjs/add/operator/toPromise';
import {Injectable} from '@angular/core';
import {Http} from "@angular/http";
import {ConfirmProfile} from "../domain/confirm-profile";
import {Confirmation} from "../domain/confirmation";
import {PageConfirmProfile} from "../domain/page-confirm-profile";
import {PageConfirmation} from "../domain/page-confirmation";

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

  getProfilesForConfirmation(page: number, size: number) {
    return this.http.get(this.adminUrl + "/confirm-profile?page=" + page + "&size=" + size)
      .toPromise()
      .then(res => res.json() as PageConfirmProfile)
      .catch(this.handleError);
  }

  getConfirmations(page: number, size: number) {
    return this.http.get(this.adminUrl + "/confirmation?page=" + page + "&size=" + size)
      .toPromise()
      .then(res => res.json() as PageConfirmation)
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
