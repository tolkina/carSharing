import {Component, OnInit} from "@angular/core";
import {PassportData} from "../domain/passport-data";
import {PassportDataService} from "../service/passport-data.service";
import {clone} from "lodash";
import {NgbDatepickerConfig, NgbDateStruct} from "@ng-bootstrap/ng-bootstrap";
import {DateFormatter} from "../../date-formatter";

@Component({
  selector: 'app-passport-data',
  templateUrl: './passport-data.component.html',
  styleUrls: ['./passport-data.component.css']
})
export class PassportDataComponent implements OnInit {
  model;
  profileId: number;
  passport: PassportData = new PassportData();
  editedPassport: PassportData = new PassportData();
  dateOfIssue: NgbDateStruct;
  validUntil: NgbDateStruct;

  constructor(private passportService: PassportDataService, config: NgbDatepickerConfig,
              private dateFormatter: DateFormatter) {
    config.minDate = {year: 1980, month: 1, day: 1};
    config.maxDate = {year: 2099, month: 12, day: 31};
  }

  ngOnInit() {
    this.getPassportData();
  }

  onChangeValidUntil(validUntil: NgbDateStruct) {
    this.editedPassport.validUntil = this.dateFormatter.toDate(validUntil);
  }

  onChangeDateOfIssue(dateOfIssue: NgbDateStruct) {
    this.editedPassport.dateOfIssue = this.dateFormatter.toDate(dateOfIssue);
  }

  updatePassport() {
    this.passportService.updatePassport(this.editedPassport)
      .then(passport => {
        this.passport = passport;
        this.editedPassport = new PassportData()
      })
      .catch();
  }

  getPassportData() {
    this.passportService.getPassport()
      .then(passport => this.passport = passport)
      .catch();
  }

  showEdit() {
    this.editedPassport = clone(this.passport);
    this.dateOfIssue = this.dateFormatter.fromDate(this.passport.dateOfIssue);
    this.validUntil = this.dateFormatter.fromDate(this.passport.validUntil);
  }
}
