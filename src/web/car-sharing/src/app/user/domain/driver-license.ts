import {Profile} from "./profile";

export class DriverLicense {
  id: number;
  seriesAndNumber: String;
  category: String;
  owner: Profile;
  frontSideImageUrl: String;
  backSideImageUrl: String;
}
