import { Component, OnInit } from '@angular/core';
import {Ad} from "../domain/ad";
import {ProfileAdService} from "../service/profile-ad.service";

@Component({
  selector: 'app-all-ads',
  templateUrl: './all-ads.component.html',
  styleUrls: ['./all-ads.component.css']
})
export class AllAdsComponent implements OnInit {

  profileId: number;
  ads : Ad[];
  constructor(private adService: ProfileAdService) { }

  ngOnInit() {
    this.profileId = 1;
    this.getAllAds();
  }

  getAllAds(){
    this.adService.getAllAdsForProfile(this.profileId).then()
      .then(ads => this.ads = ads)
      .catch();
  }
}
