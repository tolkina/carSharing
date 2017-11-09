import {Component, OnInit} from '@angular/core';
import {clone} from "lodash";
import {DriverLicense} from "../domain/driver-license";
import {DriverLicenseService} from "../service/driver-license.service";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-driver-license',
  templateUrl: './driver-license.component.html',
  styleUrls: ['./driver-license.component.css']
})
export class DriverLicenseComponent implements OnInit {
  driverLicense: DriverLicense = new DriverLicense();
  editedDriverLicense: DriverLicense = new DriverLicense();
  errorUpdate: String;
  modalRef: any;

  constructor(private driverLicenseService: DriverLicenseService, private modalService: NgbModal) {
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
      })
      .catch(err => this.errorUpdate = err._body);
  }

  onSubmit() {
    this.updateDriverLicense()
  }

  showEdit(content) {
    this.errorUpdate = "";
    this.modalRef = this.modalService.open(content);
    this.editedDriverLicense = clone(this.driverLicense);
  }

  private getDriverLicense() {
    this.driverLicenseService.getDriverLicense()
      .then(driverLicense => this.driverLicense = driverLicense)
      .catch();
  }
}
