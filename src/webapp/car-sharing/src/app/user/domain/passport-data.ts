import {Profile} from "./profile";
import {DatePipe} from "@angular/common";
export class PassportData{
  id: number;
  firstName: String;
  lastName: String;
  middleName: String;
  series: String;
  number: number;
  personalNumber: String;
  placeOfIssue: String;
  dateOfIssue: Date;
  validUntil: Date;
  owner: Profile;

}
