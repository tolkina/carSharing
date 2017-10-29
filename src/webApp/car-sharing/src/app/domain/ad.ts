import {Profile} from "./profile";
import {Car} from "./car";
export class Ad {
  id: number;
  carLocation: String;
  returnPlace: String;
  costPerHour: number;
  costPerDay: number;
  costPer3Days: number;
  owner: Profile;
  car: Car;
}
