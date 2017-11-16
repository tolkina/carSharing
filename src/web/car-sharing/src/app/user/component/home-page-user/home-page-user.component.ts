import {Component, OnInit} from '@angular/core';
import {SecurityModel} from "../../../security/security-model";

@Component({
  selector: 'app-user-home-page',
  templateUrl: './home-page-user.component.html',
  styleUrls: ['./home-page-user.component.css']
})
export class HomePageUserComponent implements OnInit {

  constructor(private securityModel: SecurityModel) {
  }

  ngOnInit() {
  }
}
