import {Profile} from "./profile";
import {Ad} from "./ad";
import {CreditCard} from "./credit-card";

export class Deal {
  id: number;
  status: string;
  bookingStartTime: Date;
  rentalStartTime: Date;
  estimatedRentalEndTime: Date;
  rentalEndTime: Date;
  deposit: number;
  price: number;
  owner = new Profile;
  customer = new Profile;
  ad = new Ad();
  creditCard = new CreditCard();
  daysForRent: number;
}
