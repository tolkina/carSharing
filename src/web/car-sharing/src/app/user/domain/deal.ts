import {Profile} from "./profile";
import {Ad} from "./ad";

export class Deal {
  id: number;
  status: string;
  bookingStartTime: number;
  rentalStartTime: number;
  estimatedRentalEndTime: number;
  rentalEndTime: number;
  deposit: number;
  price: number;
  owner = new Profile;
  customer = new Profile;
  ad = new Ad();
}
