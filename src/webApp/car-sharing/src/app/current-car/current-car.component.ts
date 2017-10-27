import {Component} from '@angular/core';
import {ProfileCarService} from "../service/profile-car.service";
import {ActivatedRoute, Router} from '@angular/router'
import {Subscription} from 'rxjs/Subscription';

@Component({
  selector: 'app-current-car',
  templateUrl: './current-car.component.html',
  styleUrls: ['./current-car.component.css']
})
export class CurrentCarComponent {
  carId: number;

  private subscription: Subscription;

  constructor(private carService: ProfileCarService, private router: Router, private activateRoute: ActivatedRoute) {
    this.subscription = activateRoute.params.subscribe(params => this.carId = params['carId']);
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  ngOnInit() {
  }

  deleteCar() {
    this.carService.deleteCar(this.carId).then()
      .then(res =>
        this.router.navigateByUrl('profile/car/all')
      )
      .catch();
  }
}
