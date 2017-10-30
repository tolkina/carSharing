import {Component, OnInit} from '@angular/core';
import {ProfileCarService} from "../service/profile-car.service";
import {clone} from "lodash";
import {ActivatedRoute} from '@angular/router'

@Component({
  selector: 'app-general-parameters',
  templateUrl: './general-parameters.component.html',
  styleUrls: ['./general-parameters.component.css']
})
export class GeneralParametersComponent implements OnInit {
  carId: number;
  generalParameters: any = {};

  constructor(private carService: ProfileCarService, private activateRoute: ActivatedRoute) {
    this.carId = activateRoute.snapshot.parent.params['carId'];
  }

  ngOnInit() {
    this.getGeneralParameters(this.carId);
  }

  getGeneralParameters(carId: number) {
    this.carService.getGeneralParameters(carId).then()
      .then(res => this.generalParameters = res)
      .catch();
  }

  /*editedGeneralParameters: any = {};

  updateGeneralParameters() {
    this.carService.updateGeneralParameters(this.editedGeneralParameters, this.carId).then()
      .then(res => this.generalParameters = res)
      .catch();
  }

  editParams() {
    this.editedGeneralParameters = clone(this.generalParameters);
  }*/
}
