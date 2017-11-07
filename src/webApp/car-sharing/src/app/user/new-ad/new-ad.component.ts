import { Component, OnInit } from '@angular/core';
import {Ad} from "../domain/ad";
import {ProfileAdService} from "../service/profile-ad.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Car} from "../domain/car";
import {ProfileCarService} from "../service/profile-car.service";
import {Subscription} from "rxjs/Subscription";
import {log} from "util";

@Component({
  selector: 'app-new-ad',
  templateUrl: './new-ad.component.html',
  styleUrls: ['./new-ad.component.css']
})
export class NewAdComponent implements OnInit {

  profileId: number;
  ad: Ad = new Ad();
  cars: Car[] = [];
  selectedCar:number;

  private subscription: Subscription;
  constructor(private adService: ProfileAdService, private carService: ProfileCarService, private router:Router, private activateRoute: ActivatedRoute) {
    this.subscription = activateRoute.params.subscribe(params => this.profileId = params['profileId']);
  }

  ngOnInit() {
    this.getCarsOfOwner();
  }

  getCarsOfOwner(){
    this.carService.getCarsByOwner(1)
      .then(cars => this.cars = cars)
      .catch();
  }

  onChangeCar(newCar){
    console.log(newCar);
    this.selectedCar = newCar;
  }

  addAd(){
    this.adService.addAd(this.ad, 1, this.selectedCar)
      .then(res => this.router.navigateByUrl('profile/1/ad/all'))
      .catch();
  }
}
