import {Component, OnInit} from '@angular/core';
import {Ad} from "../domain/ad";
import {ProfileAdService} from "../service/profile-ad.service";
import {Router} from "@angular/router";
import {SecurityModel} from "../../security/security-model";

@Component({
  selector: 'app-new-ad',
  templateUrl: './new-ad.component.html',
  styleUrls: ['./new-ad.component.css']
})
export class NewAdComponent implements OnInit {

  ad: Ad = new Ad();
  profileId = this.securityModel.principal.id;

  constructor(private adService: ProfileAdService, private router: Router, private securityModel: SecurityModel) {
  }

  ngOnInit() {
  }

  addAd() {
    this.adService.addAd(this.ad, 1, 1);
    this.router.navigateByUrl('profile/' + this.profileId + '/ad/all');
  }
}
