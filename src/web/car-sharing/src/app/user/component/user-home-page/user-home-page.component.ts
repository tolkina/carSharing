import {Component, OnInit} from '@angular/core';
import {SecurityModel} from "../../../security/security-model";

@Component({
  selector: 'app-user-home-page',
  templateUrl: './user-home-page.component.html',
  styleUrls: ['./user-home-page.component.css']
})
export class UserHomePageComponent implements OnInit {

  constructor(private securityModel: SecurityModel) {
  }

  ngOnInit() {
  }
}
