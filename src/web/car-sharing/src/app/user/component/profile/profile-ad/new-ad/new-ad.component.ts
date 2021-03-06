import {Component, OnInit} from '@angular/core';
import {Ad} from "../../../../domain/ad";
import {AdService} from "../../../../service/ad.service";
import {Router} from "@angular/router";
import {Car} from "../../../../domain/car";
import {CarService} from "../../../../service/car.service";

@Component({
  selector: 'app-new-ad',
  templateUrl: './new-ad.component.html',
  styleUrls: ['./new-ad.component.css']
})
export class NewAdComponent implements OnInit {

  ad: Ad = new Ad();
  cars: Car[] = [];
  selectedCar: number;
  error = "";

  constructor(private adService: AdService, private router: Router, private carService: CarService) {
  }

  ngOnInit() {
    this.getCarsWithoutAdOfPrincipal();
  }

  getCarsWithoutAdOfPrincipal() {
    this.carService.getCarsWithoutAdOfPrincipal()
      .then(cars => {
        this.cars = cars;
        if (cars[0]) {
          this.selectedCar = cars[0].id;
        }
      })
      .catch();
  }

  createAd() {
    this.error = "";
    this.adService.addAd(this.ad, this.selectedCar)
      .then(res => this.router.navigateByUrl('profile/ad'))
      .catch(err => this.error = err);
  }

}
