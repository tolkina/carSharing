import {Component, OnInit} from '@angular/core';
import {SecurityModel} from "../../security/security-model";

@Component({
  selector: 'app-user-home-page',
  templateUrl: './user-home-page.component.html',
  styleUrls: ['./user-home-page.component.css']
})
export class UserHomePageComponent implements OnInit {
  authenticated = this.securityModel.authenticated;
  principal = this.securityModel.principal;

  constructor(private securityModel: SecurityModel) {
  }

  ngOnInit() {
  }
}
