import {Component, OnInit} from '@angular/core';
import {ProfileCarService} from "../service/profile-car.service";
import {clone} from "lodash";
import {ActivatedRoute} from '@angular/router'

@Component({
  selector: 'app-current-condition',
  templateUrl: './current-condition.component.html',
  styleUrls: ['./current-condition.component.css']
})
export class CurrentConditionComponent implements OnInit {
  carId: number;
  currentCondition: any = {};
  editedCurrentCondition: any = {};

  constructor(private carService: ProfileCarService, private activateRoute: ActivatedRoute) {
    this.carId = activateRoute.snapshot.parent.params['carId'];
  }

  ngOnInit() {
    this.getCurrentCondition(this.carId);
  }

  updateCurrentCondition() {
    this.carService.updateCurrentCondition(this.editedCurrentCondition, this.carId).then()
      .then(res => this.currentCondition = res)
      .catch();
  }

  getCurrentCondition(carId: number) {
    this.carService.getCurrentCondition(carId).then()
      .then(res => this.currentCondition = res)
      .catch();
  }

  editParams() {
    this.editedCurrentCondition = clone(this.currentCondition);
  }
}
