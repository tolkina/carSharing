import {Component} from '@angular/core';

@Component({
  selector: 'app-profile-info',
  templateUrl: './profile-info.component.html',
  styleUrls: ['./profile-info.component.css']
})
export class ProfileInfoComponent {
  /*

    profiles:Profile;
    passport: PassportData;
    driverLicense: DriverLicense;
    /!*modalProfile;*!/
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

    updatePassport(): void {
      this.passportService.updatePassport(this.passport, 1);
    }

    updateDriverLicense(): void {
      this.driverLicenseService.updateDriverLicense(this.driverLicense, 1);
    }

    /!*clone() {
      this.modalProfile = Object.assign({}, this.profiles);
    }*!/
  */


}
