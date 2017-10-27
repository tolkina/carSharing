import {Component, OnInit} from '@angular/core';
import {ProfileCarService} from "../service/profile-car.service";
import {GeneralParameters} from "../domain/generalParameters";
import {Car} from "../domain/car";

@Component({
  selector: 'app-all-cars',
  templateUrl: './all-cars.component.html',
  styleUrls: ['./all-cars.component.css']
})
export class AllCarsComponent implements OnInit {
  cars: Car[];
  profileId: number;
  generalParameters: GeneralParameters;

  constructor(private carService: ProfileCarService) {
  }

  ngOnInit() {
    this.profileId = 1;
    this.getCarsByOwner(this.profileId);
  }

  getCarsByOwner(ownerId: number) {
    this.carService.getCarsByOwner(ownerId).then()
      .then(cars => this.cars = cars)
      .catch();
  }
}
