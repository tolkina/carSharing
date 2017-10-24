import {PassportData} from "./passport-data";
import {DriverLicense} from "./driver-license";
export class Profile {
  id: number;
  email: String;
  password: String;
  birthday: Date;
  drivingExperience: number;
  passportData: PassportData = new PassportData();
  driverLicense: DriverLicense = new DriverLicense();

}
