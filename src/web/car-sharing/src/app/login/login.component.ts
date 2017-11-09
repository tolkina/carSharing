import {Component, OnInit} from '@angular/core';
import {Http} from "@angular/http";
import {SecurityService} from "../security/security.service";
import {Router} from '@angular/router'
import {SecurityModel} from "../security/security-model";

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
    this.login(this.user)
  }



  login(credentials) {
    this.securityService.login(credentials).then(res => {
      this.securityModel.authenticate().then(res =>
        this.router.navigateByUrl('')
      ).catch(res => this.errorMessage = res);
    }).catch(res => {
      this.errorMessage = res;
      this.securityModel.authenticated = false;
    });
  }
}
