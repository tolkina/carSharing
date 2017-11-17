import {Component, OnInit} from '@angular/core';
import {SecurityService} from "./security/security.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: []
})
export class AppComponent implements OnInit {
  constructor(private securityService: SecurityService) {
  }

  ngOnInit() {
    this.securityService.authenticate();
  }
}
