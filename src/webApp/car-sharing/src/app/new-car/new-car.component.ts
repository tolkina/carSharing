import {Component, OnInit} from '@angular/core';
import {ProfileCarService} from "../service/profile-car.service";
import {Car} from "../domain/car";

@Component({
  selector: 'app-new-car',
  templateUrl: './new-car.component.html',
  styleUrls: ['./new-car.component.css']
})
export class NewCarComponent implements OnInit {
  car: Car;

  constructor(private carService: ProfileCarService) {
  }

  ngOnInit() {
  }

  addCar(car: Car) {
    this.carService.addCar(car).then()
      .then(car => this.car = car)
      .catch();
  }

}
