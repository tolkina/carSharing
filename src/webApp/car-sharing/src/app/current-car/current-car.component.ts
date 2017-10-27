import {Component} from '@angular/core';
import {ProfileCarService} from "../service/profile-car.service";
import {Router} from '@angular/router'

@Component({
  selector: 'app-current-car',
  templateUrl: './current-car.component.html',
  styleUrls: ['./current-car.component.css']
})
export class CurrentCarComponent {
  carId: number;

  constructor(private carService: ProfileCarService,  private router: Router) {
  }

  ngOnInit() {
    this.carId = 3;
  }

  deleteCar() {
    this.carService.deleteCar(this.carId).then()
      .then(res =>
        this.router.navigateByUrl('profile/car/all')
      )
      .catch();
  }
}
