import {Profile} from "./profile";
import {Car} from "./car";

export class Ad {
  id: number;
  status: string;
  carLocation: String;
  returnPlace: String;
  costPerHour: number;
  costPerDay: number;
  costPer3Days: number;
  owner = new Profile;
  car = new Car;
}
