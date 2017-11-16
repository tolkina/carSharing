import {Component} from '@angular/core';
import {ProfileCarService} from "../../../../service/profile-car.service";
import {ActivatedRoute, Router} from '@angular/router'
import {Subscription} from 'rxjs/Subscription';
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-current-car',
  templateUrl: './current-car.component.html',
  styleUrls: ['./current-car.component.css']
})
export class CurrentCarComponent {
  carId: number;
  errorDelete: String;
  modalRef: any;

  private subscription: Subscription;

  constructor(private carService: ProfileCarService, private router: Router, private activateRoute: ActivatedRoute,
              private modalService: NgbModal) {
    this.subscription = activateRoute.params.subscribe(params => this.carId = params['carId']);
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  ngOnInit() {
  }

  deleteCar() {
    this.carService.deleteCar(this.carId).then()
      .then(res => {
          this.modalRef.close();
          this.router.navigateByUrl('profile/car')
        }
      )
      .catch(err => this.errorDelete = err._body);
  }

  showDelete(content) {
    this.errorDelete = "";
    this.modalRef = this.modalService.open(content);
  }
}
