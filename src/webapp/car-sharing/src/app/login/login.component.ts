import {Component, OnInit} from '@angular/core';
import {Http} from "@angular/http";
import {SecurityService} from "../service/security.service";
import {Router} from '@angular/router'
import {SecurityModel} from "../security-model";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  errorMessage = "";
  user: any = {};

  constructor(private http: Http, private securityService: SecurityService, private router: Router,
              private securityModel: SecurityModel) {
  }

  ngOnInit() {
  }

  onSubmit() {
    console.log(this.user);
    this.login(this.user)
  }

  authenticate() {
    this.securityService.authenticate().then(res => {
      this.securityModel.authenticated = true;
      this.securityModel.principal = res;
      this.router.navigateByUrl('')
    }).catch(err => {
      this.errorMessage = err;
    })
  }

  login(credentials) {
    this.securityService.login(credentials).then(res => {
      this.authenticate();
    }).catch(res => {
      this.errorMessage = res;
      this.securityModel.authenticated = false;
    });
  }
}
