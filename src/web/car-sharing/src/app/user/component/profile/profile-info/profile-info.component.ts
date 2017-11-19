import {Component} from "@angular/core";
import {Profile} from "../../../domain/profile";
import {ProfileService} from "../../../service/profile.service";
import {clone} from "lodash";
import {DateFormatter} from "../../../../date-formatter";
import {NgbDateStruct, NgbModal} from "@ng-bootstrap/ng-bootstrap"

@Component({
  selector: 'app-profile-info',
  templateUrl: './profile-info.component.html',
  styleUrls: ['./profile-info.component.css']
})
export class ProfileInfoComponent {
  modalRef: any;
  profile: Profile = new Profile();
  editedProfile: Profile = new Profile();
  birthday: NgbDateStruct;
  errorUpdate: String;
  noConfirm = "NO";
  yesConfirm = "YES";
  checkConfirm = "CHECK";
  ngbBirthday: NgbDateStruct[] = [];

  constructor(private profileService: ProfileService, private dateFormatter: DateFormatter,
              private modalService: NgbModal) {
  }

  ngOnInit() {
    this.getProfile();
    this.putNgbBorders();
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
        this.modalRef.close();
        this.profile = profile;
        this.editedProfile = new Profile();
      })
      .catch(error => {
        this.errorUpdate = error._body
      });
  }

  deleteProfile() {
    this.profileService.deleteProfile()
      .then()
      .catch();
  }

  showEdit(content) {
    this.errorUpdate = "";
    this.modalRef = this.modalService.open(content);
    this.editedProfile = clone(this.profile);
    this.birthday = this.dateFormatter.fromDate(this.profile.birthday);
  }

  confirmProfile() {
    this.profileService.confirmProfile()
      .then(res => this.profile.confirmProfile = this.checkConfirm)
      .catch()
  }

  private putNgbBorders() {
    const currentDate = new Date();
    this.ngbBirthday.push({year: 1960, month: 1, day: 1}, {
      day: currentDate.getUTCDate(),
      month: currentDate.getUTCMonth() + 1,
      year: currentDate.getUTCFullYear() - 17
    });
  }
}
