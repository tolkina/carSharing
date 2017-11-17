import {Component, OnInit} from '@angular/core';
import {SecurityModel} from "../../../security/security-model";
import {SecurityService} from "../../../security/security.service";
import {NgbModal, NgbModalRef} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-user-home-page',
  templateUrl: './home-page-user.component.html',
  styleUrls: ['./home-page-user.component.css']
})
export class HomePageUserComponent implements OnInit {
  errorLogout = "";
  private modalRef: NgbModalRef;

  constructor(private securityModel: SecurityModel, private securityService: SecurityService,
              private modalService: NgbModal) {
  }

  ngOnInit() {
  }

  logout() {
    this.securityService.logout().then(res => {
      this.securityModel.authenticated = false;
      this.securityModel.principal = null;
      this.modalRef.close();
    }).catch();
  }

  showLogout(content) {
    this.errorLogout = "";
    this.modalRef = this.modalService.open(content);
  }
}
