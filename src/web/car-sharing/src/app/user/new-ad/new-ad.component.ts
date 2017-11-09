import {Component, OnInit} from '@angular/core';
import {Ad} from "../domain/ad";
import {ProfileAdService} from "../service/profile-ad.service";
import {Router} from "@angular/router";
import {SecurityModel} from "../../security/security-model";
import {Car} from "../domain/car";
import {ProfileCarService} from "../service/profile-car.service";

@Component({
  selector: 'app-new-ad',
  templateUrl: './new-ad.component.html',
  styleUrls: ['./new-ad.component.css']
})
export class NewAdComponent implements OnInit {

  ad: Ad = new Ad();
  cars: Car[] = [];
  selectedCar: number;

  constructor(private adService: ProfileAdService, private router: Router, private securityModel: SecurityModel,
              private carService: ProfileCarService) {
  }

  ngOnInit() {
    this.getCarsOfOwner();
  }

  getCarsOfOwner() {
    this.carService.getCarsOfPrincipal()
      .then(cars => this.cars = cars)
      .catch();
  }

  onChangeCar(newCar) {
    console.log(newCar);
    this.selectedCar = newCar;
  }

  addAd() {
    this.adService.addAd(this.ad, 1, this.selectedCar)
      .then(res => this.router.navigateByUrl('profile/ad/all'))
      .catch();
  }

}
