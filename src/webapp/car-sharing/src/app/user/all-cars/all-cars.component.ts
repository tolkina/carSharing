import {Component, OnInit} from '@angular/core';
import {ProfileCarService} from "../service/profile-car.service";
import {GeneralParameters} from "../domain/generalParameters";
import {Car} from "../domain/car";
import {SecurityModel} from "../../security/security-model";

@Component({
  selector: 'app-all-cars',
  templateUrl: './all-cars.component.html',
  styleUrls: ['./all-cars.component.css']
})
export class AllCarsComponent implements OnInit {
  cars: Car[];
  profileId: number;
  generalParameters: GeneralParameters;

  constructor(private carService: ProfileCarService, private securityModel: SecurityModel) {
  }

  ngOnInit() {
    this.profileId = this.securityModel.principal.id;
    this.getCarsByOwner(this.profileId);
  }

  getCarsByOwner(ownerId: number) {
    this.carService.getCarsByOwner(ownerId).then()
      .then(cars => this.cars = cars)
      .catch();
  }
}
