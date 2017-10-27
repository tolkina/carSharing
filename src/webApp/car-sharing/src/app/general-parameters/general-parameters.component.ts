import {Component, OnInit} from '@angular/core';
import {GeneralParameters} from "../domain/generalParameters";
import {ProfileCarService} from "../service/profile-car.service";

@Component({
  selector: 'app-general-parameters',
  templateUrl: './general-parameters.component.html',
  styleUrls: ['./general-parameters.component.css']
})
export class GeneralParametersComponent implements OnInit {
  profileId: number;
  carId: number;
  generalParameters: any = {};

  constructor(private carService: ProfileCarService) {
  }

  ngOnInit() {
    this.carId = 1;
    this.getGeneralParameters(this.carId);
  }

  updateGeneralParameters(generalParameters: GeneralParameters, carId: number) {
    this.carService.updateGeneralParameters(generalParameters, carId).then()
      .then(res => this.generalParameters = res)
      .catch();
  }

  getGeneralParameters(carId: number) {
    this.carService.getGeneralParameters(carId).then()
      .then(res => this.generalParameters = res)
      .catch();
  }

}
