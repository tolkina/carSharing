import {Component, OnInit} from "@angular/core";
import {Subscription} from "rxjs/Subscription";
import {ProfileAdService} from "../service/profile-ad.service";
import {Ad} from "../domain/ad";
import {ActivatedRoute, Router} from "@angular/router";
import {clone} from "lodash";

@Component({
  selector: 'app-current-ad',
  templateUrl: './current-ad.component.html',
  styleUrls: ['./current-ad.component.css']
})
export class CurrentAdComponent implements OnInit {

  adId: number;
  ad: Ad = new Ad();
  editedAd: Ad;

  private subscription: Subscription;

  constructor(private adService: ProfileAdService, private router: Router, private activateRoute: ActivatedRoute) {
    this.subscription = activateRoute.params.subscribe(params => this.adId = params['adId']);
  }

  ngOnInit() {
    this.adService.getAd(this.adId).then(ad => this.ad = ad);
    this.editedAd = clone(this.ad);
  }

  updateAd(){
    this.adService.updateAd(this.editedAd, this.adId)
      .then(ad => this.ad = this.editedAd)
      .catch();
  }

  deleteAd() {
    this.adService.deleteAd(this.adId).then(res =>
      this.router.navigateByUrl('profile/1/ad/all')
    )
      .catch();
  }

  showEdit() {
    this.editedAd = clone(this.ad);
  }

}
