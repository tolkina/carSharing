import {Component, OnInit} from '@angular/core';
import {ProfileCarService} from "../service/profile-car.service";
import {clone} from "lodash";
@Component({
  selector: 'app-technical-parameters',
  templateUrl: './technical-parameters.component.html',
  styleUrls: ['./technical-parameters.component.css']
})
export class TechnicalParametersComponent implements OnInit {
  carId: number;
  technicalParameters: any = {};
  editedTechnicalParameters: any = {};

  constructor(private carService: ProfileCarService) {
  }

  ngOnInit() {
    this.carId = 2;
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
