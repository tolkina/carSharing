import {Component, OnInit} from '@angular/core';
import {Profile} from "../profile";
import {ProfileService} from "../profile.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-profiles',
  templateUrl: './profiles.component.html',
  styleUrls: ['./profiles.component.css']
})
export class ProfilesComponent implements OnInit {

  profiles: Profile[];

  constructor(private router: Router, private profileService: ProfileService) {
  }

  ngOnInit() {
    this.profileService.getProfiles().then(profiles => this.profiles = profiles);
  }
}
