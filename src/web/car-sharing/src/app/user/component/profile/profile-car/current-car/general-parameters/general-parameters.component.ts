import {Component, OnInit} from '@angular/core';
import {ProfileCarService} from "../../../../../service/profile-car.service";
import {clone} from "lodash";
import {ActivatedRoute, Router} from '@angular/router'
import {NgbModal, NgbModalRef} from "@ng-bootstrap/ng-bootstrap";
import {GeneralParameters} from "../../../../../domain/general-parameters";

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

  updateGeneralParameters() {
    this.carService.updateGeneralParameters(this.editedGeneralParameters, this.carId).then()
      .then(res => {
        this.generalParameters = res;
        this.modalRef.close()
      })
      .catch(err => this.errorUpdate = err);
  }

  showUpdate(content) {
    this.errorUpdate = "";
    this.modalRef = this.modalService.open(content);
    this.editedGeneralParameters = clone(this.generalParameters);
  }

  private setYears() {
    for (let i = 2018; i >= 1900; i--) {
      this.years.push(i)
    }
  }
}
