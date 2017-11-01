import { Component, OnInit } from '@angular/core';
import {clone} from "lodash";
import {DriverLicense} from "../domain/driver-license";
import {DriverLicenseService} from "../service/driver-license.service";
import {Subscription} from "rxjs/Subscription";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-driver-license',
  templateUrl: './driver-license.component.html',
  styleUrls: ['./driver-license.component.css']
})
export class DriverLicenseComponent implements OnInit {

  profileId: number;
  driverLicense: DriverLicense = new DriverLicense();
  editedDriverLicense: DriverLicense;

  subsription: Subscription;

  constructor(private driverLicenseService: DriverLicenseService, private activateRoute:ActivatedRoute) {
    this.subsription = activateRoute.params.subscribe(params => this.profileId = params['profileId'])
  }

  ngOnInit() {
    this.driverLicenseService.getDriverLicense(this.profileId).then(driverLicense => this.driverLicense = driverLicense);
    this.editedDriverLicense = clone(this.driverLicense);
  }

  createDriverLicense(): void {
    this.driverLicenseService.createDriverLicense(this.driverLicense, this.profileId);
  }

  updateDriverLicense(): void {
    this.driverLicenseService.updateDriverLicense(this.driverLicense, this.profileId);
  }

  deleteDriverLicense(): void {
    this.driverLicenseService.deleteDriverLicense(this.driverLicense);
  }


  showEdit() {
    this.editedDriverLicense = clone(this.driverLicense);
  }
}
