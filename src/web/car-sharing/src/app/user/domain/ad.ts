import {Profile} from "./profile";
import {Car} from "./car";

export class Ad {
  id: number;
  status: string;
  carLocation: String;
  returnPlace: String;
  costPerDay: number;
  owner = new Profile;
  car = new Car;
}
