import {Component, OnInit} from "@angular/core";
import {PassportData} from "../domain/passport-data";
import {PassportDataService} from "../service/passport-data.service";
import {clone} from "lodash";

@Component({
  selector: 'app-passport-data',
  templateUrl: './passport-data.component.html',
  styleUrls: ['./passport-data.component.css']
})
export class PassportDataComponent implements OnInit {

  passport: PassportData = new PassportData();
  editedPassport: PassportData;

  constructor(private passportService: PassportDataService,) {
  }

  ngOnInit() {
    this.passportService.getPassport(1).then(passport => this.passport = passport);
    this.editedPassport = clone(this.passport);
  }

  createPassportData(): void {
    this.passportService.createPassportData(this.editedPassport, 1);
  }

  updatePassport(): void {
    this.passportService.updatePassport(this.editedPassport, 1);
  }

  deletePassport(): void {
    this.passportService.deletePassportData(this.passport);
  }

  showEdit() {
    this.editedPassport = clone(this.passport);
  }
}
