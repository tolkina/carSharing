import {Injectable} from '@angular/core';
import {Profile} from "../user/domain/profile";
import {SecurityService} from "./security.service";

@Injectable()

export class SecurityModel {
  constructor(private securityService: SecurityService) {
  }

  private _errorMessage: String = "";

  get errorMessage(): String {
    return this._errorMessage;
  }

  set errorMessage(value: String) {
    this._errorMessage = value;
  }

  private _principal: Profile = new Profile();

  get principal(): Profile {
    return this._principal;
  }

  set principal(value: Profile) {
    this._principal = value;
  }

  private _authenticated: boolean = false;

  get authenticated(): boolean {
    return this._authenticated;
  }

  set authenticated(value: boolean) {
    this._authenticated = value;
  }

  authenticate() {
    return this.securityService.authenticate().then(res => {
      this.authenticated = true;
      this.principal = res;
    }).catch(err => {
      this.errorMessage = err;
    })
  }
}
