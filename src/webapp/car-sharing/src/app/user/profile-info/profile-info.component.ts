import {Component} from '@angular/core';
import {Profile} from "../domain/profile";
import {PassportData} from "../domain/passport-data";
import {DriverLicense} from "../domain/driver-license";
import {ProfileService} from "../service/profile.service";
import {PassportDataService} from "../service/passport-data.service";
import {DriverLicenseService} from "../service/driver-license.service";
import {ActivatedRoute, Router} from '@angular/router'
import {Subscription} from 'rxjs/Subscription';

@Component({
  selector: 'app-profile-info',
  templateUrl: './profile-info.component.html',
  styleUrls: ['./profile-info.component.css']
})
export class ProfileInfoComponent {
  profileId: number;
  profiles: Profile = new Profile();
  passport: PassportData = new PassportData();
  driverLicense: DriverLicense = new DriverLicense();
  private subscription: Subscription;

  constructor(private activateRoute: ActivatedRoute, private profileService: ProfileService,
              private passportService: PassportDataService,
              private driverLicenseService: DriverLicenseService) {
    // this.profileId = activateRoute.snapshot.parent.params['id'];
    this.profileId = 1;
  }

  ngOnInit() {
    this.profileService.getProfiles(this.profileId).then(profiles => this.profiles = profiles);
    this.passportService.getPassport(this.profileId).then(passport => this.passport = passport);
    this.driverLicenseService.getDriverLicense(this.profileId).then(driverLicense => this.driverLicense = driverLicense);
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


  createPassportData(): void {
    this.passportService.createPassportData(this.passport, 1);
  }

  updatePassport(): void {
    this.passportService.updatePassport(this.passport, 1);
  }

  deletePassport(): void {
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