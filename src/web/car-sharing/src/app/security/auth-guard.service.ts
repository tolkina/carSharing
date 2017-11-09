import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';

@Injectable()
export class AuthGuardService implements CanActivate {
  constructor(private router: Router) {
  }

  canActivate(): boolean {
    console.log(window.localStorage.getItem("id"))
    if (!window.localStorage.getItem("id")) {
      this.router.navigate(['login']);
      return false;
    }
    return true;
  }
}
