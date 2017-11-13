import {Component, OnInit} from '@angular/core';
import {Ad} from "../domain/ad";
import {ProfileAdService} from "../service/profile-ad.service";

@Component({
  selector: 'app-all-ads',
  templateUrl: './all-ads.component.html',
  styleUrls: ['./all-ads.component.css']
})
export class AllAdsComponent implements OnInit {

  ads: Ad[];

  constructor(private adService: ProfileAdService) {
  }

  ngOnInit() {
    this.getAllAds();
  }

  getAllAds() {
    this.adService.getAllAdsForPrincipal().then()
      .then(ads => this.ads = ads)
      .catch();
  }
}
