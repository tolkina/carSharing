import {Component, OnInit} from '@angular/core';
import {NgbModal, NgbModalRef} from "@ng-bootstrap/ng-bootstrap";
import {ConfirmProfileService} from "../../service/confirm-profile.service";
import {ConfirmProfile} from "../../domain/confirm-profile";
import {ConfirmProfileStatus} from "../../domain/confirm-profile-status";
import {Confirmation} from "../../domain/confirmation";
import {PageConfirmation} from "../../domain/page-confirmation";
import {PageConfirmProfile} from "../../domain/page-confirm-profile";
import {PageParameter} from "../../domain/page-parameter";
import {Direction} from "../../domain/direction";
import {Sort} from "../../domain/sort";

@Component({
  selector: 'app-confirm-profile',
  templateUrl: './confirm-profile.component.html',
  styleUrls: ['./confirm-profile.component.css']
})
export class ConfirmProfileComponent implements OnInit {
  profiles = new PageConfirmProfile;
  confirmations = new PageConfirmation;
  cloneProfile = new ConfirmProfile();
  error = "";
  confirmProfileStatus = new ConfirmProfileStatus();
  confirmation = new Confirmation();
  profile = new ConfirmProfile();
  sort = new Sort();
  direction = new Direction();
  pageParameterConfirmation = new PageParameter(1, 5, this.sort.dateConfirm, this.direction.desc);
  pageParameterConfirmProfile = new PageParameter(1, 5, this.sort.id, this.direction.asc);
  isCollapsedConfirmProfile = true;
  isCollapsedConfirmations = true;
  private modalRef: NgbModalRef;

  constructor(private confirmProfileService: ConfirmProfileService, private modalService: NgbModal) {
    this.getProfilesForConfirmation();
    this.getConfirmations()
  }

  ngOnInit() {
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
    this.confirmProfileService.getProfilesForConfirmation(this.pageParameterConfirmProfile)
      .then(res => this.profiles = res)
      .catch();
  }

  getConfirmations() {
    this.confirmProfileService.getConfirmations(this.pageParameterConfirmation)
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

  sortConfirmation(sortType: string, direction: string) {
    this.pageParameterConfirmation.sort = sortType;
    this.pageParameterConfirmation.direction = direction;
    this.getConfirmations();
  }

  sortConfirmProfile(sortType: string, direction: string) {
    this.pageParameterConfirmProfile.sort = sortType;
    this.pageParameterConfirmProfile.direction = direction;
    this.getProfilesForConfirmation();
  }
}
