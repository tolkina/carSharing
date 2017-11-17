import {Component, OnInit} from '@angular/core';
import {SecurityModel} from "../../../security/security-model";
import {NgbModal, NgbModalRef} from "@ng-bootstrap/ng-bootstrap";
import {SecurityService} from "../../../security/security.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page-admin.component.html',
  styleUrls: ['./home-page-admin.component.css']
})
export class HomePageAdminComponent implements OnInit {
  errorLogout = "";
  private modalRef: NgbModalRef;

  constructor(private securityModel: SecurityModel, private securityService: SecurityService,
              private modalService: NgbModal, private router: Router) {
  }

  ngOnInit() {
  }

  logout() {
    this.securityService.logout().then(res => {
      this.modalRef.close();
      this.router.navigateByUrl('');
    }).catch();
  }

  showLogout(content) {
    this.errorLogout = "";
    this.modalRef = this.modalService.open(content);
  }
}
