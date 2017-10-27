import {Component, OnInit} from '@angular/core';
import {ProfileCarService} from "../service/profile-car.service";
import {clone} from "lodash";
import {ActivatedRoute} from '@angular/router'

@Component({
  selector: 'app-technical-parameters',
  templateUrl: './technical-parameters.component.html',
  styleUrls: ['./technical-parameters.component.css']
})
export class TechnicalParametersComponent implements OnInit {
  carId: number;
  technicalParameters: any = {};
  editedTechnicalParameters: any = {};

  constructor(private carService: ProfileCarService, private activateRoute: ActivatedRoute) {
    this.carId = activateRoute.snapshot.parent.params['carId'];
  }

  ngOnInit() {
    this.getTechnicalParameters(this.carId);
  }

  updateTechnicalParameters() {
    console.log(this.editedTechnicalParameters);
    this.carService.updateTechnicalParameters(this.editedTechnicalParameters, this.carId).then()
      .then(res => this.technicalParameters = res)
      .catch();
  }

  getTechnicalParameters(carId: number) {
    this.carService.getTechnicalParameters(carId).then()
      .then(res => this.technicalParameters = res)
      .catch();
  }

  editParams() {
    this.editedTechnicalParameters = clone(this.technicalParameters);
  }
}
