import {Component, OnInit} from '@angular/core';
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {ConfirmProfileService} from "../../service/confirm-profile.service";
import {ConfirmProfile} from "../../domain/confirm-profile";
import {ConfirmProfileStatus} from "../../domain/confirm-profile-status";
import {Confirmation} from "../../domain/confirmation";
import {PageConfirmation} from "../../domain/page-confirmation";
import {PageConfirmProfile} from "../../domain/page-confirm-profile";

@Component({
  selector: 'app-confirm-profile',
  templateUrl: './confirm-profile.component.html',
  styleUrls: ['./confirm-profile.component.css']
})
export class ConfirmProfileComponent implements OnInit {
  profiles = new PageConfirmProfile;
  confirmations = new PageConfirmation;
  cloneProfile = new ConfirmProfile();
  modalRef: any;
  error = "";
  confirmProfileStatus = new ConfirmProfileStatus();
  confirmation = new Confirmation();
  profile = new ConfirmProfile();
  pageConfirmation: number = 1;
  pageConfirmProfile: number = 1;
  size: number = 5;

  constructor(private confirmProfileService: ConfirmProfileService, private modalService: NgbModal) {
  }

  ngOnInit() {
    this.getProfilesForConfirmation()
    this.getConfirmations()
  }

  onChangePageConfirmation() {
    this.getConfirmations()
  }

  onChangePageConfirmProfile() {
    this.getProfilesForConfirmation()
  }

  notConfirmProfile() {
    this.confirmProfileService.notConfirmProfile(this.cloneProfile.id)
      .then(res => {
        this.modalRef.close();
        this.profiles.content.splice(this.profiles.content.indexOf(this.cloneProfile), 1);
        this.confirmations.content.push(res)
      })
      .catch(err => this.error = err);
  }

  confirmProfile() {
    this.confirmProfileService.confirmProfile(this.cloneProfile.id)
      .then(res => {
        this.modalRef.close();
        this.profiles.content.splice(this.profiles.content.indexOf(this.cloneProfile), 1);
        this.confirmations.content.push(res)
      })
      .catch(err => this.error = err);
  }

  getProfilesForConfirmation() {
    this.confirmProfileService.getProfilesForConfirmation(this.pageConfirmProfile, this.size)
      .then(res => this.profiles = res)
      .catch();
  }

  getConfirmations() {
    this.confirmProfileService.getConfirmations(this.pageConfirmation, this.size)
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

  getStatus(confirmProfile: String) {
    if (confirmProfile == this.confirmProfileStatus.yes[0]) {
      return this.confirmProfileStatus.yes[1]
    }
    if (confirmProfile == this.confirmProfileStatus.no[0]) {
      return this.confirmProfileStatus.no[1]
    }
    return this.confirmProfileStatus.check[1]
  }

  showConfirmation(confirmation, content) {
    this.confirmation = confirmation;
    this.modalRef = this.modalService.open(content, {size: 'lg'})
  }

  showProfile(profile, content) {
    this.profile = profile;
    this.modalRef = this.modalService.open(content, {size: 'lg'})
  }
}
