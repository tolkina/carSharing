import {Component, OnInit} from '@angular/core';
import {SecurityModel} from "../security/security-model";
import {SecurityService} from "../security/security.service";
import {Router} from '@angular/router'

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(private securityModel: SecurityModel, private securityService: SecurityService,
              private router: Router) {
  }

  ngOnInit() {
  }

  logout() {
    this.securityService.logout().then(res => {
      this.securityModel.authenticated = false;
      this.securityModel.principal = null;
      this.router.navigateByUrl('')
    }).catch();
  }
  onCancel(){this.router.navigateByUrl('')}
}
