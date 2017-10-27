import {Component, OnInit} from '@angular/core';
import {CurrentCondition} from "../domain/currentCondition";
import {ProfileCarService} from "../service/profile-car.service";

@Component({
  selector: 'app-current-condition',
  templateUrl: './current-condition.component.html',
  styleUrls: ['./current-condition.component.css']
})
export class CurrentConditionComponent implements OnInit {
  profileId: number;
  carId: number;
  currentCondition: any = {};

  constructor(private carService: ProfileCarService) {
  }

  ngOnInit() {
    this.carId = 1;
    this.getCurrentCondition(this.carId);
  }

  updateCurrentCondition(currentCondition: CurrentCondition, carId: number) {
    this.carService.updateCurrentCondition(currentCondition, carId).then()
      .then(res => this.currentCondition = res)
      .catch();
  }

  getCurrentCondition(carId: number) {
    this.carService.getCurrentCondition(carId).then()
      .then(res => this.currentCondition = res)
      .catch();
  }

}
