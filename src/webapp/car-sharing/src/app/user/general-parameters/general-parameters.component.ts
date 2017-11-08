import {Component, OnInit} from '@angular/core';
import {ProfileCarService} from "../service/profile-car.service";
import {clone} from "lodash";
import {ActivatedRoute, Router} from '@angular/router'

@Component({
  selector: 'app-general-parameters',
  templateUrl: './general-parameters.component.html',
  styleUrls: ['./general-parameters.component.css']
})
export class GeneralParametersComponent implements OnInit {
  carId: number;
  generalParameters: any = {};

  constructor(private carService: ProfileCarService, private activateRoute: ActivatedRoute,
              private router: Router) {
    this.carId = +activateRoute.snapshot.parent.params['carId'];
    if (isNaN(this.carId)) {
      this.router.navigateByUrl("profile/car")
    }
  }

  ngOnInit() {
    this.getGeneralParameters(this.carId);
  }

  getGeneralParameters(carId: number) {
    this.carService.getGeneralParameters(carId).then()
      .then(res => this.generalParameters = res)
      .catch(res => this.router.navigateByUrl("profile/car"));
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
