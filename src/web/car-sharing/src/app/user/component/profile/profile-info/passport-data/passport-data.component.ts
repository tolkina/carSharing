import {Component, OnInit} from "@angular/core";
import {PassportData} from "../../../../domain/passport-data";
import {PassportDataService} from "../../../../service/passport-data.service";
import {clone} from "lodash";
import {NgbDateStruct, NgbModal, NgbModalRef} from "@ng-bootstrap/ng-bootstrap";
import {DateFormatter} from "../../../../../date-formatter";
import {ProfileInfoComponent} from "../profile-info.component";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-passport-data',
  templateUrl: './passport-data.component.html',
  styleUrls: ['./passport-data.component.css']
})
export class PassportDataComponent implements OnInit {
  error: String;
  passport = new PassportData();
  editedPassport = new PassportData();
  dateOfIssue: NgbDateStruct;
  validUntil: NgbDateStruct;
  ngbValidUntil: NgbDateStruct[] = [];
  ngbDateOfIssue: NgbDateStruct[] = [];
  touchedValidUntil = false;
  touchedDateOfIssue = false;
  form: FormGroup;
  loading: boolean = false;
  input: any = new FormData();
  private modalRef: NgbModalRef;

  constructor(private passportService: PassportDataService, private dateFormatter: DateFormatter,
              private modalService: NgbModal, private profileInfoComponent: ProfileInfoComponent,
              private fb: FormBuilder) {
    this.createForm();
  }

  ngOnInit() {
    this.putNgbBorders();
    this.getPassportData();
  }

  onChangeValidUntil(validUntil: NgbDateStruct) {
    this.editedPassport.validUntil = this.dateFormatter.toDate(validUntil);
  }

  onChangeDateOfIssue(dateOfIssue: NgbDateStruct) {
    this.ngbValidUntil[0] = dateOfIssue;
    this.editedPassport.dateOfIssue = this.dateFormatter.toDate(dateOfIssue);
  }

  updatePassport() {
    if (this.checkNgbDates()) {
      this.passportService.updatePassport(this.editedPassport)
        .then(passport => {
          this.modalRef.close();
          this.passport = passport;
          this.editedPassport = new PassportData();
          this.profileInfoComponent.setConfirmNo();
        })
        .catch(err => this.error = err);
    }
  }

  showEdit(content) {
    this.error = "";
    this.modalRef = this.modalService.open(content, {size: 'lg'});
    this.editedPassport = clone(this.passport);
    this.dateOfIssue = this.dateFormatter.fromDate(this.passport.dateOfIssue);
    this.validUntil = this.dateFormatter.fromDate(this.passport.validUntil);
    this.editedPassport.validUntil = this.dateFormatter.toDate(this.validUntil);
    this.editedPassport.dateOfIssue = this.dateFormatter.toDate(this.dateOfIssue);
    this.touchedDateOfIssue = false;
    this.touchedValidUntil = false;
  }

  showEditPhoto(content) {
    this.error = "";
    this.modalRef = this.modalService.open(content);
  }

  createForm() {
    this.form = this.fb.group({
      name: ['', Validators.required],
      photo: null
    });
  }

  onFileChange(event) {
    if (event.target.files.length > 0) {
      let file = event.target.files[0];
      this.form.get('photo').setValue(file);
    }
  }

  uploadPhoto() {
    this.prepareSave();
    if (!this.error) {
      this.loading = true;
      this.passportService.uploadPhoto(this.input)
        .then(passport => {
          this.passport.photoUrl = passport.photoUrl;
          this.modalRef.close();
          this.loading = false;
          this.profileInfoComponent.setConfirmNo();
        })
        .catch(err => {
          this.error = err;
          this.loading = false;
        })
    }
  }

  uploadRegistrationPhoto() {
    this.prepareSave();
    if (!this.error) {
      this.loading = true;
      this.passportService.uploadRegistrationPhoto(this.input)
        .then(passport => {
          this.passport.registrationPhotoUrl = passport.registrationPhotoUrl;
          this.modalRef.close();
          this.loading = false;
          this.profileInfoComponent.setConfirmNo();
        })
        .catch(err => {
          this.error = err;
          this.loading = false;
        })
    }
  }

  deletePhoto() {
    this.passportService.deletePhoto()
      .then(passport => {
        this.passport.photoUrl = passport.photoUrl;
        this.modalRef.close();
        this.profileInfoComponent.setConfirmNo();
      })
      .catch(err => this.error = err)
  }

  deleteRegistrationPhoto() {
    this.passportService.deleteRegistrationPhoto()
      .then(passport => {
        this.passport.registrationPhotoUrl = passport.registrationPhotoUrl;
        this.modalRef.close();
        this.profileInfoComponent.setConfirmNo();
      })
      .catch(err => this.error = err)
  }

  private getPassportData() {
    this.passportService.getPassport()
      .then(passport => this.passport = passport)
      .catch();
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
      this.error = "Срок действия не может быть раньше даты выдачи";
      return false;
    }
    return true;
  }

  private prepareSave() {
    this.error = "";
    this.input = new FormData();
    if (this.form.get('photo').value.size > 2097152) // 2 mb for bytes.
    {
      this.error = "File size must under 2mb!";
      return false;
    }
    this.input.append('file', this.form.get('photo').value);
    return true;
  }
}
