import {Component, OnInit} from '@angular/core';
import {SecurityModel} from "./security/security-model";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: []
})
export class AppComponent implements OnInit {
  constructor(private securityModel: SecurityModel) {
  }

  ngOnInit() {
    this.securityModel.authenticate().then().catch();
  }
}
