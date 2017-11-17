import {Component, OnInit} from '@angular/core';
import {Ad} from "../../../../domain/ad";
import {ProfileAdService} from "../../../../service/profile-ad.service";
import {ProfileCarService} from "../../../../service/profile-car.service";
import {AdStatus} from "../../../../domain/ad-status";

@Component({
  selector: 'app-all-ads',
  templateUrl: './all-ads.component.html',
  styleUrls: ['./all-ads.component.css']
})
export class AllAdsComponent implements OnInit {

  ads: Ad[];
  numberOfCars: number;
  adStatus = new AdStatus();

  constructor(private adService: ProfileAdService, private carService: ProfileCarService) {
  }

  ngOnInit() {
    this.getAllAds();
    this.getCarsWithoutAdOfPrincipal()
  }

  getAllAds() {
    this.adService.getAllAdsForPrincipal().then()
      .then(ads => this.ads = ads)
      .catch();
  }

  getCarsWithoutAdOfPrincipal() {
    this.carService.getCarsWithoutAdOfPrincipal()
      .then(cars => this.numberOfCars = cars.length)
      .catch();
  }
}
