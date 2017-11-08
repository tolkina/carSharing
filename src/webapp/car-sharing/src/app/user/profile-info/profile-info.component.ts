import {Component} from "@angular/core";
import {Profile} from "../domain/profile";
import {ProfileService} from "../service/profile.service";
import {clone} from "lodash";
import {Subscription} from "rxjs/Subscription";
import {ActivatedRoute} from "@angular/router";
import {DateFormatter} from "../../date-formatter";
import {NgbDateStruct} from "@ng-bootstrap/ng-bootstrap"

@Component({
  selector: 'app-profile-info',
  templateUrl: './profile-info.component.html',
  styleUrls: ['./profile-info.component.css']
})
export class ProfileInfoComponent {

  profileId: number;
  profile: Profile = new Profile();
  editedProfile: Profile = new Profile();
  birthday: NgbDateStruct;

  private subscription: Subscription;

  constructor(private profileService: ProfileService, private activateRoute: ActivatedRoute,
              private dateFormatter: DateFormatter) {
  }

  ngOnInit() {
    this.getProfile();
  }

  getProfile() {
    this.profileService.getProfile()
      .then(profile => this.profile = profile)
      .catch();
  }

  onChange(date: NgbDateStruct) {
    this.editedProfile.birthday = this.dateFormatter.toDate(date);
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
    this.birthday = this.dateFormatter.fromDate(this.profile.birthday);
  }
}
