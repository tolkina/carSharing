import {Component, OnInit} from '@angular/core';
import {ProfileCarService} from "../../../../service/profile-car.service";
import {Sort} from "../../../../domain/sort";
import {Direction} from "../../../../domain/direction";
import {PageParameter} from "../../../../domain/page-parameter";
import {PageCar} from "../../../../domain/page-car";

@Component({
  selector: 'app-all-cars',
  templateUrl: './all-cars.component.html',
  styleUrls: ['./all-cars.component.css']
})
export class AllCarsComponent implements OnInit {
  cars = new PageCar();
  sort = new Sort();
  direction = new Direction();
  pageParameter = new PageParameter(1, 6, this.sort.id, this.direction.asc);

  constructor(private carService: ProfileCarService) {
  }

  ngOnInit() {
    this.getCarsOfPrincipal();
  }

  getCarsOfPrincipal() {
    this.carService.getCarsOfPrincipal(this.pageParameter).then()
      .then(cars => this.cars = cars)
      .catch();
  }
}
