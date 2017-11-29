import {Component, OnInit} from '@angular/core';
import {ProfileService} from "../../../service/profile.service";
import {SecurityService} from "../../../../security/security.service";
import {Router} from "@angular/router";
import {NgbModal, NgbModalRef} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-setup',
  templateUrl: './setup.component.html',
  styleUrls: ['./setup.component.css']
})
export class SetupComponent implements OnInit {
  error = "";
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

  showDisableProfile(content) {
    this.error = "";
    this.modalRef = this.modalService.open(content);
  }
}
