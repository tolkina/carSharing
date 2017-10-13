import {Component, OnInit} from '@angular/core';
import {Profile} from "./profile";
import {ProfileService} from "./profile.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: []
})
export class AppComponent implements OnInit {
  ngOnInit() {
  }
}
