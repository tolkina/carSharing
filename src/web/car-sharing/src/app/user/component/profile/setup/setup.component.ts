import {Component, OnInit} from '@angular/core';
import {ProfileService} from "../../../service/profile.service";
import {SecurityService} from "../../../../security/security.service";
import {Router} from "@angular/router";
import {NgbModal, NgbModalRef} from "@ng-bootstrap/ng-bootstrap";
import {NewPassword} from "../../../domain/new-password";

@Component({
  selector: 'app-setup',
  templateUrl: './setup.component.html',
  styleUrls: ['./setup.component.css']
})
export class SetupComponent implements OnInit {
  error = "";
  newPassword = new NewPassword();
  private modalRef: NgbModalRef;

  constructor(private profileService: ProfileService, private securityService: SecurityService, private router: Router,
              private modalService: NgbModal) {
  }

  ngOnInit() {
  }

  disableProfile() {
    this.profileService.disableProfile()
      .then(res => {
        this.securityService.logout().then(res => {
          this.modalRef.close();
          this.router.navigateByUrl('')
        }).catch(error => this.error = error);
      })
      .catch(error => this.error = error)
  }

  changePassword() {
    this.profileService.changePassword(this.newPassword)
      .then(res => {
        this.modalRef.close();
      }).catch(error => this.error = error);
  }

  showDisableProfile(content) {
    this.error = "";
    this.modalRef = this.modalService.open(content);
  }

  showChangePassword(content) {
    this.error = "";
    this.newPassword = new NewPassword();
    this.modalRef = this.modalService.open(content);
  }
}
