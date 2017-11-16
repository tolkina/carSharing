import {Component, OnInit} from '@angular/core';
import {clone} from "lodash";
import {ActivatedRoute, Router} from '@angular/router'
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {ProfileCarService} from "../../../../../service/profile-car.service";

@Component({
  selector: 'app-current-condition',
  templateUrl: './current-condition.component.html',
  styleUrls: ['./current-condition.component.css']
})
export class CurrentConditionComponent implements OnInit {
  carId: number;
  currentCondition: any = {};
  editedCurrentCondition: any = {};
  modalRef: any;
  errorUpdate: String;

  constructor(private carService: ProfileCarService, private activateRoute: ActivatedRoute,
              private router: Router, private modalService: NgbModal) {
    this.carId = +activateRoute.snapshot.parent.params['carId'];
    if (isNaN(this.carId)) {
      this.router.navigateByUrl("profile/car")
    }
  }

  onSubmit() {
    this.updateCurrentCondition()
  }

  showEdit(content) {
    this.errorUpdate = "";
    this.modalRef = this.modalService.open(content);
    this.editedCurrentCondition = clone(this.currentCondition);
  }

  ngOnInit() {
    this.getCurrentCondition(this.carId);
  }

  updateCurrentCondition() {
    this.carService.updateCurrentCondition(this.editedCurrentCondition, this.carId).then()
      .then(res => {
        this.currentCondition = res;
        this.modalRef.close()
      })
      .catch(err => this.errorUpdate = err._body);
  }

  getCurrentCondition(carId: number) {
    this.carService.getCurrentCondition(carId).then()
      .then(res => this.currentCondition = res)
      .catch(res => this.router.navigateByUrl("profile/car"));
  }
}
