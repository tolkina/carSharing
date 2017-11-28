import {Component, OnInit} from '@angular/core';
import {clone} from "lodash";
import {DriverLicense} from "../../../../domain/driver-license";
import {DriverLicenseService} from "../../../../service/driver-license.service";
import {NgbModal, NgbModalRef} from "@ng-bootstrap/ng-bootstrap";
import {ProfileInfoComponent} from "../profile-info.component";

@Component({
  selector: 'app-driver-license',
  templateUrl: './driver-license.component.html',
  styleUrls: ['./driver-license.component.css']
})
export class DriverLicenseComponent implements OnInit {
  driverLicense: DriverLicense = new DriverLicense();
  editedDriverLicense: DriverLicense = new DriverLicense();
  error: String;
  private modalRef: NgbModalRef;

  constructor(private driverLicenseService: DriverLicenseService, private modalService: NgbModal,
              private profileInfoComponent: ProfileInfoComponent) {
  }

  ngOnInit() {
    this.getDriverLicense()
  }

  updateDriverLicense(): void {
    this.driverLicenseService.updateDriverLicense(this.editedDriverLicense)
      .then(driverLicense => {
        this.modalRef.close();
        this.driverLicense = driverLicense;
        this.editedDriverLicense = new DriverLicense();
        this.profileInfoComponent.profile.confirmProfile = this.profileInfoComponent.confirm.no[0];
      })
      .catch(err => this.error = err._body);
  }

  showEdit(content) {
    this.error = "";
    this.modalRef = this.modalService.open(content);
    this.editedDriverLicense = clone(this.driverLicense);
  }

  private getDriverLicense() {
    this.driverLicenseService.getDriverLicense()
      .then(driverLicense => this.driverLicense = driverLicense)
      .catch();
  }
}
