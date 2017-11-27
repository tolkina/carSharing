import {Component} from '@angular/core';
import {ProfileCarService} from "../../../../service/profile-car.service";
import {ActivatedRoute, Router} from '@angular/router'
import {Subscription} from 'rxjs/Subscription';
import {NgbModal, NgbModalRef} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-current-car',
  templateUrl: './current-car.component.html',
  styleUrls: ['./current-car.component.css']
})
export class CurrentCarComponent {
  carId: number;
  error = "";

  private subscription: Subscription;
  private modalRef: NgbModalRef;

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
      .catch(err => this.error = err);
  }

  showDelete(content) {
    this.error = "";
    this.modalRef = this.modalService.open(content);
  }
}
