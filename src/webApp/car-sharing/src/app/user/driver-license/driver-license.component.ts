import {Component, OnInit} from '@angular/core';
import {clone} from "lodash";
import {DriverLicense} from "../domain/driver-license";
import {DriverLicenseService} from "../service/driver-license.service";
import {SecurityModel} from "../../security/security-model";

@Component({
  selector: 'app-driver-license',
  templateUrl: './driver-license.component.html',
  styleUrls: ['./driver-license.component.css']
})
export class DriverLicenseComponent implements OnInit {
  driverLicense: DriverLicense = new DriverLicense();
  editedDriverLicense: DriverLicense = new DriverLicense();

  constructor(private driverLicenseService: DriverLicenseService) {
  }

  ngOnInit() {
    this.getDriverLicense()
  }

  updateDriverLicense(): void {
    this.driverLicenseService.updateDriverLicense(this.editedDriverLicense)
      .then(driverLicense => {
        this.driverLicense = driverLicense;
        this.editedDriverLicense = new DriverLicense();
      })
      .catch();
  }

  showEdit() {
    this.editedDriverLicense = clone(this.driverLicense);
  }

  private getDriverLicense() {
    this.driverLicenseService.getDriverLicense()
      .then(driverLicense => this.driverLicense = driverLicense)
      .catch();
  }
}
