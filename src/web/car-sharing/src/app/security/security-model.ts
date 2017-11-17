import {Injectable} from '@angular/core';

@Injectable()

export class SecurityModel {
  authenticated: boolean = false;
  principalId: number;

  constructor() {
  }

  setLogged() {
    this.authenticated = !!window.localStorage.getItem("id");
    this.principalId = +window.localStorage.getItem("id");
  }

  deleteLogged(){
    this.authenticated = false;
    this.principalId = null;
  }
}
