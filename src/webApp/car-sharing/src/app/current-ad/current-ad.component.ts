import {Component, OnInit} from "@angular/core";
import {Subscription} from "rxjs/Subscription";
import {ProfileAdService} from "../service/profile-ad.service";
import {Ad} from "../domain/ad";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-current-ad',
  templateUrl: './current-ad.component.html',
  styleUrls: ['./current-ad.component.css']
})
export class CurrentAdComponent implements OnInit {

  adId: number;
  ad: Ad = new Ad();

  private subscription: Subscription;

  constructor(private adService: ProfileAdService, private router: Router, private activateRoute: ActivatedRoute) {
    this.subscription = activateRoute.params.subscribe(params => this.adId = params['adId']);
  }

  ngOnInit() {
    this.adService.getAd(this.adId).then(ad => this.ad = ad);
  }

  deleteAd() {
    this.adService.deleteAd(this.adId).then(res =>
      this.router.navigateByUrl('profile/1/ad/all')
    )
      .catch();

  }

}
