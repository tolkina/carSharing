import {Component, Input, OnInit} from '@angular/core';
import {Car} from "../../domain/car";
import {NgbModal, NgbModalRef} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-car-modal',
  templateUrl: './car-modal.component.html',
  styleUrls: ['./car-modal.component.css']
})
export class CarModalComponent implements OnInit {
  @Input()
  car: Car;
  private modalRef: NgbModalRef;

  constructor(private modalService: NgbModal) {
  }

  showCar(content) {
    this.modalRef = this.modalService.open(content, {size: 'lg'})
  }

  ngOnInit() {
  }

}
