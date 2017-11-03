import {Component} from "@angular/core";
import {Profile} from "../domain/profile";
import {ProfileService} from "../service/profile.service";
import {clone} from "lodash";
import {Subscription} from "rxjs/Subscription";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-profile-info',
  templateUrl: './profile-info.component.html',
  styleUrls: ['./profile-info.component.css']
})
export class ProfileInfoComponent {

  profileId: number;
  profile: Profile = new Profile();
  editedProfile: Profile = new Profile();

  private subscription: Subscription;

  constructor(private profileService: ProfileService, private activateRoute: ActivatedRoute) {
  }

  ngOnInit() {
    this.getProfile();
  }

  getProfile() {
    this.profileService.getProfile()
      .then(profile => this.profile = profile)
      .catch();
  }

  updateProfile() {
    this.profileService.updateProfile(this.editedProfile)
      .then(profile => {
        this.profile = profile;
        this.editedProfile = new Profile();
      })
      .catch();
  }

  deleteProfile() {
    this.profileService.deleteProfile()
      .then()
      .catch();
  }

  showEdit() {
    this.editedProfile = clone(this.profile);
  }
}
