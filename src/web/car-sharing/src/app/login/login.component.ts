import {Component, OnInit} from '@angular/core';
import {SecurityService} from "../security/security.service";
import {Router} from '@angular/router'
import {User} from "../user/domain/user";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  errorMessage = "";
  user = new User;

  constructor(private securityService: SecurityService, private router: Router) {
  }

  ngOnInit() {
  }

  login() {
    this.securityService.login(this.user)
      .then(res => this.router.navigateByUrl(''))
      .catch(res => this.errorMessage = res)
  }
}
