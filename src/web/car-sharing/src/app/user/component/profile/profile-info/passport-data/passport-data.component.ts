import {Component, OnInit} from "@angular/core";
import {PassportData} from "../../../../domain/passport-data";
import {PassportDataService} from "../../../../service/passport-data.service";
import {clone} from "lodash";
import {NgbDateStruct, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {DateFormatter} from "../../../../../date-formatter";
import {ProfileInfoComponent} from "../profile-info.component";

@Component({
  selector: 'app-passport-data',
  templateUrl: './passport-data.component.html',
  styleUrls: ['./passport-data.component.css']
})
export class PassportDataComponent implements OnInit {
  model;
  errorUpdate: String;
  modalRef: any;
  passport: PassportData = new PassportData();
  editedPassport: PassportData = new PassportData();
  dateOfIssue: NgbDateStruct;
  validUntil: NgbDateStruct;
  ngbValidUntil: NgbDateStruct[] = [];
  ngbDateOfIssue: NgbDateStruct[] = [];

  constructor(private passportService: PassportDataService, private dateFormatter: DateFormatter,
              private modalService: NgbModal, private profileInfoComponent: ProfileInfoComponent) {
  }

  ngOnInit() {
    this.putNgbBorders();
    this.getPassportData();
  }

  onChangeValidUntil(validUntil: NgbDateStruct) {
    this.editedPassport.validUntil = this.dateFormatter.toDate(validUntil);
  }

  onChangeDateOfIssue(dateOfIssue: NgbDateStruct) {
    this.editedPassport.dateOfIssue = this.dateFormatter.toDate(dateOfIssue);
  }

  updatePassport() {
    if (this.checkNgbDates()) {
      this.passportService.updatePassport(this.editedPassport)
        .then(passport => {
          this.modalRef.close();
          this.passport = passport;
          this.editedPassport = new PassportData();
          this.profileInfoComponent.profile.confirmProfile = this.profileInfoComponent.noConfirm;
        })
        .catch(err => this.errorUpdate = err);
    }
  }

  getPassportData() {
    this.passportService.getPassport()
      .then(passport => this.passport = passport)
      .catch();
  }

  showEdit(content) {
    this.errorUpdate = "";
    this.modalRef = this.modalService.open(content, {size: 'lg'});
    this.editedPassport = clone(this.passport);
    this.dateOfIssue = this.dateFormatter.fromDate(this.passport.dateOfIssue);
    this.validUntil = this.dateFormatter.fromDate(this.passport.validUntil);
    this.editedPassport.validUntil = this.dateFormatter.toDate(this.validUntil);
    this.editedPassport.dateOfIssue = this.dateFormatter.toDate(this.dateOfIssue);
  }

  private putNgbBorders() {
    const currentDate = new Date();
    this.ngbDateOfIssue.push({year: 1980, month: 1, day: 1}, {
      day: currentDate.getUTCDate(),
      month: currentDate.getUTCMonth() + 1,
      year: currentDate.getUTCFullYear()
    });
    this.ngbValidUntil.push({year: 1980, month: 1, day: 1}, {
      day: currentDate.getUTCDate(),
      month: currentDate.getUTCMonth() + 1,
      year: currentDate.getUTCFullYear() + 20
    });
  }

  private checkNgbDates() {
    if (this.editedPassport.dateOfIssue > this.editedPassport.validUntil) {
      this.errorUpdate = "Срок действия не может быть раньше даты выдачи";
      return false;
    }
    return true;
  }
}
