import 'rxjs/add/operator/toPromise';
import {Injectable} from '@angular/core';
import {Http} from "@angular/http";
import {User} from "../user/domain/user";
import {SecurityModel} from "./security-model";

@Injectable()
export class SecurityService {
  constructor(private http: Http, private securityModel: SecurityModel) {
  }

  login(credentials) {
    return this.http.post("login?username=" + credentials.email + "&password=" + credentials.password, {})
      .toPromise()
      .then(res => {
        this.authenticate()
          .then(user => {
            this.setAuthUser(user);
            this.checkAdmin(user);
          })
          .catch()
      })
      .catch(this.handleError);
  }

  logout() {
    return this.http.post('logout', {})
      .toPromise()
      .then(res => this.deleteAuthUser())
      .catch(this.handleError)
  }

  private authenticate() {
    return this.http.get("api/profile/principal")
      .toPromise()
      .then(res => res.json() as User)
      .catch(this.handleError)
  }

  private checkAdmin(user: User) {
    for (let i = 0; i < user.roles.length; i++) {
      if (user.roles[i].role == "ROLE_ADMIN") {
        window.localStorage.setItem("admin", user.id.toString());
        break;
      }
    }
  }

  private setAuthUser(user: User) {
    window.localStorage.setItem("id", user.id.toString());
    this.securityModel.setLogged()
  }

  private deleteAuthUser() {
    window.localStorage.removeItem("id");
    window.localStorage.removeItem("admin");
    this.securityModel.deleteLogged()
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occured', error);
    return Promise.reject(error.json().message || error)
  }
}
