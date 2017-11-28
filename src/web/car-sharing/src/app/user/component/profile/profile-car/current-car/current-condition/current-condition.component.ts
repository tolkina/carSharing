import {Component, OnInit} from '@angular/core';
import {clone} from "lodash";
import {ActivatedRoute, Router} from '@angular/router'
import {NgbModal, NgbModalRef} from "@ng-bootstrap/ng-bootstrap";
import {CarService} from "../../../../../service/profile-car.service";
import {CurrentCondition} from "../../../../../domain/current-condition";

@Component({
  selector: 'app-current-condition',
  templateUrl: './current-condition.component.html',
  styleUrls: ['./current-condition.component.css']
})
export class CurrentConditionComponent implements OnInit {
  carId: number;
  currentCondition = new CurrentCondition();
  editedCurrentCondition = new CurrentCondition();
  error = "";
  private modalRef: NgbModalRef;

  constructor(private carService: CarService, private activateRoute: ActivatedRoute,
              private router: Router, private modalService: NgbModal) {
    this.carId = +activateRoute.snapshot.parent.params['carId'];
    if (isNaN(this.carId)) {
      this.router.navigateByUrl("profile/car")
    }
  }

  ngOnInit() {
    this.getCurrentCondition(this.carId);
  }

  showEdit(content) {
    this.error = "";
    this.modalRef = this.modalService.open(content);
    this.editedCurrentCondition = clone(this.currentCondition);
  }

  updateCurrentCondition() {
    this.carService.updateCurrentCondition(this.editedCurrentCondition, this.carId).then()
      .then(res => {
        this.currentCondition = res;
        this.modalRef.close()
      })
      .catch(err => this.error = err);
  }

  private getCurrentCondition(carId: number) {
    this.carService.getCurrentCondition(carId).then()
      .then(res => this.currentCondition = res)
      .catch(res => this.router.navigateByUrl("profile/car"));
  }
}
