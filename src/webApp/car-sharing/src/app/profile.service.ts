import 'rxjs/add/operator/toPromise';
import {Injectable} from '@angular/core';
import {Profile} from "./profile";
import {Http} from "@angular/http";

@Injectable()
export class ProfileService {

  private profileUrl = 'api/profile';

  constructor(private http: Http) {
  }

  getProfiles(): Promise<Profile[]> {
    return this.http.get(this.profileUrl)
      .toPromise()
      .then(response => response.json() as Profile[])
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occured', error);
    return Promise.reject(error.message || error)
  }
}
