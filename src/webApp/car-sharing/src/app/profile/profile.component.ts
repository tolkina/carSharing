import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {Profile} from "../service/profile";
import {ProfileService} from "../service/profile.service";
import {PassportData} from "../service/passport-data";
import {PassportDataService} from "../service/passport-data.service";
import {DriverLicense} from "../service/driver-license";
import {DriverLicenseService} from "../service/driver-license.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})

export class ProfileComponent implements OnInit {

  profiles:Profile = new Profile();
  passport: PassportData = new PassportData();
  driverLicense: DriverLicense = new DriverLicense();
  /*modalProfile;*/
  constructor(private router: Router,
              private profileService: ProfileService,
              private passportService: PassportDataService,
              private driverLicenseService: DriverLicenseService) { }


  ngOnInit() {
    this.profileService.getProfiles(1).then(profiles => this.profiles = profiles);
    this.passportService.getPassport(1).then(passport => this.passport = passport);
    this.driverLicenseService.getDriverLicense(1).then(driverLicense => this.driverLicense = driverLicense);
  }


  updateProfile(): void {
    this.profileService.updateProfiles(this.profiles, 1);
  }

  createProfile(): void {
    this.profileService.createProfile(this.profiles);
  }
  deleteProfile(): void {
    this.profileService.deleteProfile(this.profiles);
  }


  createPassportData():void {
    this.passportService.createPassportData(this.passport, 1);
  }
  updatePassport(): void {
    this.passportService.updatePassport(this.passport, 1);
  }
  deletePassport():void {
    this.passportService.deletePassportData(this.passport);
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




  /*clone() {
    this.modalProfile = Object.assign({}, this.profiles);
  }*/


}
