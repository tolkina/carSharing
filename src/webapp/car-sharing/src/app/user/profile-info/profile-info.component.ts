import {Component} from "@angular/core";
import {Profile} from "../domain/profile";
import {ProfileService} from "../service/profile.service";
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
    this.profileService.getProfiles(this.profileId).then(profiles => this.profile = profiles);
    this.editedProfile = clone(this.profile);
  }


  updateProfile(): void {
    this.profileService.updateProfiles(this.profile, this.profileId).then(profile => this.profile = this.editedProfile).catch();
  }

  showEdit() {
    this.editedProfile = clone(this.profile);
  }

}
