import { Component, OnInit } from '@angular/core';
import {Ad} from "../domain/ad";
import {ProfileAdService} from "../service/profile-ad.service";

@Component({
  selector: 'app-profile-ad',
  templateUrl: './profile-ad.component.html',
  styleUrls: ['./profile-ad.component.css']
})
export class ProfileAdComponent implements OnInit {

  ads : Ad[];

  constructor(private adService: ProfileAdService) { }

  ngOnInit() {
    this.getAllAds();
  }

  getAllAds(){
    this.adService.getAllAds().then()
      .then(ads => this.ads = ads)
      .catch();
  }

}
