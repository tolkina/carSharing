import {Component, OnInit} from '@angular/core';
import {clone} from "lodash";
import {DriverLicense} from "../../../../domain/driver-license";
import {DriverLicenseService} from "../../../../service/driver-license.service";
import {NgbModal, NgbModalRef} from "@ng-bootstrap/ng-bootstrap";
import {ProfileInfoComponent} from "../profile-info.component";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-driver-license',
  templateUrl: './driver-license.component.html',
  styleUrls: ['./driver-license.component.css']
})
export class DriverLicenseComponent implements OnInit {
  driverLicense: DriverLicense = new DriverLicense();
  editedDriverLicense: DriverLicense = new DriverLicense();
  error: String;
  form: FormGroup;
  loading: boolean = false;
  input: any = new FormData();
  private modalRef: NgbModalRef;

  constructor(private driverLicenseService: DriverLicenseService, private modalService: NgbModal,
              private profileInfoComponent: ProfileInfoComponent, private fb: FormBuilder) {
    this.createForm();
  }

  ngOnInit() {
    this.getDriverLicense()
  }

  updateDriverLicense(): void {
    this.driverLicenseService.updateDriverLicense(this.editedDriverLicense)
      .then(driverLicense => {
        this.modalRef.close();
        this.driverLicense = driverLicense;
        this.editedDriverLicense = new DriverLicense();
        this.profileInfoComponent.setConfirmNo();
      })
      .catch(err => this.error = err._body);
  }

  showEdit(content) {
    this.error = "";
    this.modalRef = this.modalService.open(content);
    this.editedDriverLicense = clone(this.driverLicense);
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

  uploadFrontSidePhoto() {
    this.prepareSave();
    if (!this.error) {
      this.loading = true;
      this.driverLicenseService.uploadFrontSidePhoto(this.input)
        .then(driverLicense => {
          this.driverLicense.frontSideImageUrl = driverLicense.frontSideImageUrl;
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

  uploadBackSidePhoto() {
    this.prepareSave();
    if (!this.error) {
      this.loading = true;
      this.driverLicenseService.uploadBackSidePhoto(this.input)
        .then(driverLicense => {
          this.driverLicense.backSideImageUrl = driverLicense.backSideImageUrl;
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

  deleteFrontSidePhoto() {
    this.driverLicenseService.deleteFrontSidePhoto()
      .then(driverLicense => {
        this.driverLicense.frontSideImageUrl = driverLicense.frontSideImageUrl;
        this.modalRef.close();
        this.profileInfoComponent.setConfirmNo();
      })
      .catch(err => this.error = err)
  }

  deleteBackSidePhoto() {
    this.driverLicenseService.deleteBackSidePhoto()
      .then(driverLicense => {
        this.driverLicense.backSideImageUrl = driverLicense.backSideImageUrl;
        this.modalRef.close();
        this.profileInfoComponent.setConfirmNo();
      })
      .catch(err => this.error = err)
  }

  private getDriverLicense() {
    this.driverLicenseService.getDriverLicense()
      .then(driverLicense => this.driverLicense = driverLicense)
      .catch();
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
