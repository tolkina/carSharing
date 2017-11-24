import {Component} from "@angular/core";
import {Profile} from "../../../domain/profile";
import {ProfileService} from "../../../service/profile.service";
import {clone} from "lodash";
import {DateFormatter} from "../../../../date-formatter";
import {NgbDateStruct, NgbModal, NgbModalRef} from "@ng-bootstrap/ng-bootstrap"
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-profile-info',
  templateUrl: './profile-info.component.html',
  styleUrls: ['./profile-info.component.css']
})
export class ProfileInfoComponent {
  profile: Profile = new Profile();
  editedProfile: Profile = new Profile();
  birthday: NgbDateStruct;
  errorUpdate = "";
  errorAvatar = "";
  noConfirm = "NO";
  yesConfirm = "YES";
  checkConfirm = "CHECK";
  ngbBirthday: NgbDateStruct[] = [];
  form: FormGroup;
  loading: boolean = false;
  private modalRef: NgbModalRef;

  constructor(private profileService: ProfileService, private dateFormatter: DateFormatter,
              private modalService: NgbModal, private fb: FormBuilder) {
    this.createForm();
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

  showDeleteAvatar(content) {
    this.errorAvatar = "";
    this.modalRef = this.modalService.open(content);
  }

  showLoadAvatar(content) {
    this.errorAvatar = "";
    this.modalRef = this.modalService.open(content);
  }

  confirmProfile() {
    this.profileService.confirmProfile()
      .then(res => this.profile.confirmProfile = this.checkConfirm)
      .catch()
  }

  createForm() {
    this.form = this.fb.group({
      name: ['', Validators.required],
      avatar: null
    });
  }

  onFileChange(event) {
    if (event.target.files.length > 0) {
      let file = event.target.files[0];
      this.form.get('avatar').setValue(file);
    }
  }

  uploadAvatar() {
    const formModel = this.prepareSave();
    this.loading = true;
    this.profileService.uploadAvatar(formModel)
      .then(profile => {
        this.profile.avatarUrl = profile.avatarUrl;
        this.loading = false;
        this.modalRef.close()
      })
      .catch(err => {
        this.loading = false;
        this.errorAvatar = err;
      })
  }

  deleteAvatar() {
    this.profileService.deleteAvatar()
      .then(profile => {
        this.profile.avatarUrl = profile.avatarUrl;
        this.modalRef.close()
      })
      .catch(err => this.errorAvatar = err)
  }

  private putNgbBorders() {
    const currentDate = new Date();
    this.ngbBirthday.push({year: 1960, month: 1, day: 1}, {
      day: currentDate.getUTCDate(),
      month: currentDate.getUTCMonth() + 1,
      year: currentDate.getUTCFullYear() - 17
    });
  }

  private prepareSave(): any {
    let input = new FormData();
    input.append('file', this.form.get('avatar').value);
    return input;
  }
}
