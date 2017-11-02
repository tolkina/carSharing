import 'rxjs/add/operator/toPromise';
import {Injectable} from '@angular/core';
import {Http} from "@angular/http";
import {Profile} from "../user/domain/profile";

@Injectable()
export class SecurityService {
  constructor(private http: Http) {
  }

  login(credentials) {
    return this.http.post("login?username=" + credentials.email + "&password=" + credentials.password, {})
      .toPromise()
      .then()
      .catch(this.handleError);
  }

  authenticate() {
    return this.http.get("api/profile/principal")
      .toPromise()
      .then(res => res.json() as Profile)
      .catch(this.handleError)
  }

  logout() {
    return this.http.post('logout', {})
      .toPromise()
      .then()
      .catch(this.handleError)
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occured', error);
    return Promise.reject(error.json().message || error)
  }
}
