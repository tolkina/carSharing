import {Component, OnInit} from '@angular/core';
import {TechnicalParameters} from "../domain/technicalParameters";
import {ProfileCarService} from "../service/profile-car.service";

@Component({
  selector: 'app-technical-parameters',
  templateUrl: './technical-parameters.component.html',
  styleUrls: ['./technical-parameters.component.css']
})
export class TechnicalParametersComponent implements OnInit {
  carId: number;
  technicalParameters: TechnicalParameters;

  constructor(private carService: ProfileCarService) {
  }

  ngOnInit() {
    this.carId = 1;
    this.getTechnicalParameters(this.carId);
  }

  updateTechnicalParameters(technicalParameters: TechnicalParameters, carId: number) {
    this.carService.updateTechnicalParameters(technicalParameters, carId).then()
      .then(res => this.technicalParameters = res)
      .catch();
  }

  getTechnicalParameters(carId: number) {
    this.carService.getTechnicalParameters(carId).then()
      .then(res => this.technicalParameters = res)
      .catch();
  }

}
