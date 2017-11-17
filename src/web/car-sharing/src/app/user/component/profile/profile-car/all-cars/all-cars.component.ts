import {Component, OnInit} from '@angular/core';
import {ProfileCarService} from "../../../../service/profile-car.service";
import {GeneralParameters} from "../../../../domain/general-parameters";
import {Car} from "../../../../domain/car";

@Component({
  selector: 'app-all-cars',
  templateUrl: './all-cars.component.html',
  styleUrls: ['./all-cars.component.css']
})
export class AllCarsComponent implements OnInit {
  cars: Car[] = [];
  generalParameters: GeneralParameters;

  constructor(private carService: ProfileCarService) {
  }

  ngOnInit() {
    this.getCarsOfPrincipal();
  }

  getCarsOfPrincipal() {
    this.carService.getCarsOfPrincipal().then()
      .then(cars => this.cars = cars)
      .catch();
  }
}
