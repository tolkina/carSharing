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
  generalParameters: GeneralParameters = new GeneralParameters;
  editedGeneralParameters: GeneralParameters = new GeneralParameters;
  errorUpdate: string = "";
  years: number[] = [];
  filesToUpload: Array<File> = [];
  photo: string;
  loading: boolean = false;
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

  getGeneralParameters(carId: number) {
    this.carService.getGeneralParameters(carId).then()
      .then(res => this.generalParameters = res)
      .catch(res => this.router.navigateByUrl("profile/car"));
  }

  showUpdate(content) {
    this.errorUpdate = "";
    this.modalRef = this.modalService.open(content);
    this.editedGeneralParameters = clone(this.generalParameters);
  }

  fileChangeEvent(fileInput: any) {
    this.filesToUpload = <Array<File>>fileInput.target.files;
  }

  showDeletePhoto(photo: string, content) {
    this.errorUpdate = "";
    this.photo = photo;
    this.modalRef = this.modalService.open(content);
  }

  showUploadPhotos(content) {
    this.errorUpdate = "";
    this.modalRef = this.modalService.open(content);
  }

  deletePhoto() {
    this.carService.deletePhotos(new CarPhotos(new Array(this.photo)), this.carId)
      .then(generalParameters => {
        this.generalParameters = generalParameters;
        this.modalRef.close()
      })
      .catch(err => this.errorUpdate = err);
  }

  uploadPhotos() {
    this.loading = true;
    this.carService.uploadPhotos(this.prepareFilesToUpload(), this.carId)
      .then(generalParameters => {
        this.generalParameters = generalParameters;
        this.loading = false
        this.modalRef.close()
      })
      .catch(err => this.errorUpdate = err)
  }

  updateGeneralParameters() {
    this.carService.updateGeneralParameters(this.editedGeneralParameters, this.carId)
      .then(generalParameters => {
        this.generalParameters = generalParameters;
        this.modalRef.close()
      })
      .catch(err => this.errorUpdate = err);
  }

  private prepareFilesToUpload() {
    const formData: any = new FormData();
    const files: Array<File> = this.filesToUpload;
    for (let i = 0; i < files.length; i++) {
      formData.append("files", files[i], files[i]['name']);
    }
    return formData;
  }

  private setYears() {
    for (let i = 2018; i >= 1900; i--) {
      this.years.push(i)
    }
  }
}
