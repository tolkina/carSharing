import { Component, OnInit } from '@angular/core';
import {clone} from "lodash";
import {DriverLicense} from "../domain/driver-license";
import {DriverLicenseService} from "../service/driver-license.service";

@Component({
  selector: 'app-driver-license',
  templateUrl: './driver-license.component.html',
  styleUrls: ['./driver-license.component.css']
})
export class DriverLicenseComponent implements OnInit {

  driverLicense: DriverLicense = new DriverLicense();
  editedDriverLicense: DriverLicense;

  constructor(private driverLicenseService: DriverLicenseService) {
  }

  ngOnInit() {
    this.driverLicenseService.getDriverLicense(1).then(driverLicense => this.driverLicense = driverLicense);
    this.editedDriverLicense = clone(this.driverLicense);
  }

  createDriverLicense(): void {
    this.driverLicenseService.createDriverLicense(this.driverLicense, 1);
  }

  updateDriverLicense(): void {
    this.driverLicenseService.updateDriverLicense(this.driverLicense, 1);
  }

  deleteDriverLicense(): void {
    this.driverLicenseService.deleteDriverLicense(this.driverLicense);
  }


  showEdit() {
    this.editedDriverLicense = clone(this.driverLicense);
  }
}
