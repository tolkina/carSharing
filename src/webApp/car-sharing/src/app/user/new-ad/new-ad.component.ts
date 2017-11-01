import { Component, OnInit } from '@angular/core';
import {Ad} from "../domain/ad";
import {ProfileAdService} from "../service/profile-ad.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-new-ad',
  templateUrl: './new-ad.component.html',
  styleUrls: ['./new-ad.component.css']
})
export class NewAdComponent implements OnInit {

  ad: Ad = new Ad();

  constructor(private adService: ProfileAdService, private router:Router) { }

  ngOnInit() {
  }

  addAd(){
    this.adService.addAd(this.ad, 1,1);
    this.router.navigateByUrl('profile/1/ad/all');
  }
}
