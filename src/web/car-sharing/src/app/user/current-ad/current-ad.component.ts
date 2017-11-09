import {Component, OnInit} from "@angular/core";
import {Subscription} from "rxjs/Subscription";
import {ProfileAdService} from "../service/profile-ad.service";
import {Ad} from "../domain/ad";
import {ActivatedRoute, Router} from "@angular/router";
import {clone} from "lodash";
import {Car} from "../domain/car";
import {ProfileCarService} from "../service/profile-car.service";

@Component({
  selector: 'app-current-ad',
  templateUrl: './current-ad.component.html',
  styleUrls: ['./current-ad.component.css']
})
export class CurrentAdComponent implements OnInit {

  adId: number;
  ad: Ad = new Ad();
  editedAd: Ad = new Ad();
  car: Car = new Car();
  cars: Car[] = [];
  selectedCar: number;

  private subscription: Subscription;

  constructor(private adService: ProfileAdService, private carService: ProfileCarService, private router: Router, private activateRoute: ActivatedRoute) {
    this.subscription = activateRoute.params.subscribe(params => this.adId = params['adId']);
  }

  ngOnInit() {
    this.adService.getAd(this.adId)
      .then(ad => this.ad = ad)
      .catch();

    this.carService.getCarByAd(this.adId)
      .then(car => this.car = car)
      .catch();
  }

  getCarsOfOwner() {
    this.carService.getCarsOfPrincipal()
      .then(cars => this.cars = cars)
      .catch();
  }

  onChangeCar(newCar) {
    console.log(newCar);
    this.selectedCar = newCar;
  }


  updateAd() {
    this.adService.updateAd(this.editedAd, this.adId)
      .then(ad => this.ad = this.editedAd)
      .catch();
  }

  deleteAd() {
    this.adService.deleteAd(this.adId).then(res =>
      this.router.navigateByUrl('profile/ad/all')
    )
      .catch();
  }

  showEdit() {
    this.editedAd = clone(this.ad);
    this.getCarsOfOwner();
  }

}
