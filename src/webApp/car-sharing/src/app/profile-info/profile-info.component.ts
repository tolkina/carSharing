import {Component} from "@angular/core";
import {Profile} from "../domain/profile";
import {DriverLicense} from "../domain/driver-license";
import {ProfileService} from "../service/profile.service";
import {DriverLicenseService} from "../service/driver-license.service";
import {clone} from "lodash";
import {ActivatedRoute} from "@angular/router";
import {Subscription} from "rxjs/Subscription";

@Component({
  selector: 'app-profile-info',
  templateUrl: './profile-info.component.html',
  styleUrls: ['./profile-info.component.css']
})
export class ProfileInfoComponent {

  profileId: number;
  profile: Profile = new Profile();
  editedProfile: Profile;

  private subscription: Subscription;

  constructor(private profileService: ProfileService, private activateRoute: ActivatedRoute) {
    this.subscription = activateRoute.params.subscribe(params => this.profileId = params['profileId']);
  }


  ngOnInit() {
    this.profileService.getProfiles(1).then(profiles => this.profile = profiles);
    this.editedProfile = clone(this.profile);
  }


  updateProfile(): void {
    this.profileService.updateProfiles(this.profile, 1).then(profile => this.profile = this.editedProfile).catch();
  }

  createProfile(): void {
    this.profileService.createProfile(this.profile);
  }

  /*deleteProfile(): void {
    this.profileService.deleteProfile(this.profile);
  }*/


  showEdit() {
 this.editedProfile = clone(this.profile);
 }

}
