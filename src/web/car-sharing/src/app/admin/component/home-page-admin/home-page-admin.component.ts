import {Component, OnInit} from '@angular/core';
import {SecurityModel} from "../../../security/security-model";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page-admin.component.html',
  styleUrls: ['./home-page-admin.component.css']
})
export class HomePageAdminComponent implements OnInit {

  constructor(private securityModel: SecurityModel) {
  }

  ngOnInit() {
  }

}
