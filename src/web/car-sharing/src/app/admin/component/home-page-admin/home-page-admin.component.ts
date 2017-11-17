import {Component, OnInit} from '@angular/core';
import {SecurityModel} from "../../../security/security-model";
import {NgbModal, NgbModalRef} from "@ng-bootstrap/ng-bootstrap";
import {SecurityService} from "../../../security/security.service";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page-admin.component.html',
  styleUrls: ['./home-page-admin.component.css']
})
export class HomePageAdminComponent implements OnInit {
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
