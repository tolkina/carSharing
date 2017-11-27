import {Component, OnInit} from '@angular/core';
import {ProfileCarService} from "../../../../../service/profile-car.service";
import {clone} from "lodash";
import {ActivatedRoute, Router} from '@angular/router'
import {NgbModal, NgbModalRef} from "@ng-bootstrap/ng-bootstrap";
import {GeneralParameters} from "../../../../../domain/general-parameters";
import {CarPhotos} from "../../../../../domain/car-photos";

@Component({
  selector: 'app-general-parameters',
  templateUrl: './general-parameters.component.html',
  styleUrls: ['./general-parameters.component.css']
})
export class GeneralParametersComponent implements OnInit {
  carId: number;
  generalParameters = new GeneralParameters();
  editedGeneralParameters = new GeneralParameters();
  error = "";
  years: number[] = [];
  filesToUpload: Array<File> = [];
  photo: string;
  loading: boolean = false;
  formData: any = new FormData();
  private modalRef: NgbModalRef;

  constructor(private carService: ProfileCarService, private activateRoute: ActivatedRoute,
              private router: Router, private modalService: NgbModal) {
    this.carId = +activateRoute.snapshot.parent.params['carId'];
    if (isNaN(this.carId)) {
      this.router.navigateByUrl("profile/car")
    }
  }

  ngOnInit() {
    this.setYears();
    this.getGeneralParameters(this.carId);
  }

  showUpdate(content) {
    this.error = "";
    this.modalRef = this.modalService.open(content);
    this.editedGeneralParameters = clone(this.generalParameters);
  }

  fileChangeEvent(fileInput: any) {
    this.filesToUpload = <Array<File>>fileInput.target.files;
  }

  showDeletePhoto(photo: string, content) {
    this.error = "";
    this.photo = photo;
    this.modalRef = this.modalService.open(content);
  }

  showUploadPhotos(content) {
    this.error = "";
    this.modalRef = this.modalService.open(content);
  }

  deletePhoto() {
    this.carService.deletePhotos(new CarPhotos(new Array(this.photo)), this.carId)
      .then(generalParameters => {
        this.generalParameters = generalParameters;
        this.modalRef.close()
      })
      .catch(err => this.error = err);
  }

  uploadPhotos() {
    if (this.prepareFilesToUpload()) {
      this.loading = true;
      this.carService.uploadPhotos(this.formData, this.carId)
        .then(generalParameters => {
          this.generalParameters = generalParameters;
          this.loading = false
          this.modalRef.close()
        })
        .catch(err => this.error = err)
    }
  }

  updateGeneralParameters() {
    this.carService.updateGeneralParameters(this.editedGeneralParameters, this.carId)
      .then(generalParameters => {
        this.generalParameters = generalParameters;
        this.modalRef.close()
      })
      .catch(err => this.error = err);
  }

  private getGeneralParameters(carId: number) {
    this.carService.getGeneralParameters(carId).then()
      .then(res => this.generalParameters = res)
      .catch(res => this.router.navigateByUrl("profile/car"));
  }

  private prepareFilesToUpload() {
    this.error = "";
    this.formData = new FormData();
    const files: Array<File> = this.filesToUpload;
    for (let i = 0; i < files.length; i++) {
      if (files[i].size > 2097152) // 2 mb for bytes.
      {
        this.error = "File size " + files[i]['name'] + " must under 2mb!";
        return false;
      }
      this.formData.append("files", files[i], files[i]['name']);
    }
    return true;
  }

  private setYears() {
    for (let i = 2018; i >= 1900; i--) {
      this.years.push(i)
    }
  }
}
