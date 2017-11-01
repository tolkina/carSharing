import {Component, OnInit} from "@angular/core";
import {PassportData} from "../domain/passport-data";
import {PassportDataService} from "../service/passport-data.service";
import {clone} from "lodash";
import {Subscription} from "rxjs/Subscription";
import {ActivatedRoute} from "@angular/router";
import {NgbDatepickerConfig} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-passport-data',
  templateUrl: './passport-data.component.html',
  styleUrls: ['./passport-data.component.css']
})
export class PassportDataComponent implements OnInit {
  model;
  profileId: number;
  passport: PassportData = new PassportData();
  editedPassport: PassportData;

  private subscription: Subscription;

  constructor(private passportService: PassportDataService, private activateRoute: ActivatedRoute, config: NgbDatepickerConfig) {
    this.subscription = activateRoute.params.subscribe(params => this.profileId = params['profileId'])

    // customize default values of datepickers used by this component tree
    config.minDate = {year: 1980, month: 1, day: 1};
    config.maxDate = {year: 2099, month: 12, day: 31};
  }

  ngOnInit() {
    this.passportService.getPassport(this.profileId).then(passport => this.passport = passport);
    this.editedPassport = clone(this.passport);
  }

  createPassportData(): void {
    this.passportService.createPassportData(this.editedPassport, this.profileId)
      .then(res => this.passport = res)
      .catch();
  }

  updatePassport(): void {
    this.passportService.updatePassport(this.editedPassport, this.profileId);
  }

  deletePassport(): void {
    this.passportService.deletePassportData(this.passport, this.profileId);
  }

  showEdit() {
    this.editedPassport = clone(this.passport);
  }
}


