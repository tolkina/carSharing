import {Component, OnInit} from '@angular/core';
import {ConfirmProfile} from "../domain/confirm-profile";
import {ConfirmProfileService} from "../service/confirm-profile.service";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-confirm-profile',
  templateUrl: './confirm-profile.component.html',
  styleUrls: ['./confirm-profile.component.css']
})
export class ConfirmProfileComponent implements OnInit {
  profiles: ConfirmProfile[] = [];
  cloneProfile = new ConfirmProfile();
  modalRef: any;
  error = "";

  constructor(private confirmProfileService: ConfirmProfileService, private modalService: NgbModal) {
  }

  ngOnInit() {
    this.getProfilesForConfirmation()
  }

  notConfirmProfile() {
    this.confirmProfileService.notConfirmProfile(this.cloneProfile.id)
      .then(res => {
        this.modalRef.close();
        this.profiles.splice(this.profiles.indexOf(this.cloneProfile), 1)
      })
      .catch(err => this.error = err);
  }

  confirmProfile() {
    this.confirmProfileService.confirmProfile(this.cloneProfile.id)
      .then(res => {
        this.modalRef.close();
        this.profiles.splice(this.profiles.indexOf(this.cloneProfile), 1)
      })
      .catch(err => this.error = err);
  }

  getProfilesForConfirmation() {
    this.confirmProfileService.getProfilesForConfirmation()
      .then(res => this.profiles = res)
      .catch();
  }

  showConfirmProfile(profile: ConfirmProfile, content) {
    this.error = "";
    this.cloneProfile = profile;
    this.modalRef = this.modalService.open(content);
  }

  showNotConfirmProfile(profile: ConfirmProfile, content) {
    this.error = "";
    this.cloneProfile = profile;
    this.modalRef = this.modalService.open(content);
  }
}
