import {Component, OnInit} from '@angular/core';
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {ConfirmProfileService} from "../../service/confirm-profile.service";
import {ConfirmProfile} from "../../domain/confirm-profile";
import {ConfirmProfileStatus} from "../../domain/confirm-profile-status";
import {Confirmation} from "../../domain/confirmation";

@Component({
  selector: 'app-confirm-profile',
  templateUrl: './confirm-profile.component.html',
  styleUrls: ['./confirm-profile.component.css']
})
export class ConfirmProfileComponent implements OnInit {
  profiles: ConfirmProfile[] = [];
  confirmations: Confirmation[] = [];
  cloneProfile = new ConfirmProfile();
  modalRef: any;
  error = "";
  confirmProfileStatus = new ConfirmProfileStatus();

  constructor(private confirmProfileService: ConfirmProfileService, private modalService: NgbModal) {
  }

  ngOnInit() {
    this.getProfilesForConfirmation()
    this.getConfirmations()
  }

  notConfirmProfile() {
    this.confirmProfileService.notConfirmProfile(this.cloneProfile.id)
      .then(res => {
        this.modalRef.close();
        this.profiles.splice(this.profiles.indexOf(this.cloneProfile), 1);
        this.confirmations.push(res)
      })
      .catch(err => this.error = err);
  }

  confirmProfile() {
    this.confirmProfileService.confirmProfile(this.cloneProfile.id)
      .then(res => {
        this.modalRef.close();
        this.profiles.splice(this.profiles.indexOf(this.cloneProfile), 1);
        this.confirmations.push(res)
      })
      .catch(err => this.error = err);
  }

  getProfilesForConfirmation() {
    this.confirmProfileService.getProfilesForConfirmation()
      .then(res => this.profiles = res)
      .catch();
  }

  getConfirmations() {
    this.confirmProfileService.getConfirmations()
      .then(res => this.confirmations = res)
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

  getStatus(profile: ConfirmProfile) {
    if (profile.confirmProfile == this.confirmProfileStatus.yes[0]) {
      return this.confirmProfileStatus.yes[1]
    }
    if (profile.confirmProfile == this.confirmProfileStatus.no[0]) {
      return this.confirmProfileStatus.no[1]
    }
    return this.confirmProfileStatus.check[1]
  }
}
