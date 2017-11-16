import {Component, OnInit} from '@angular/core';
import {Deal} from "../domain/deal";
import {DealService} from "../service/deal.service";
import {NgbModal, NgbModalRef} from "@ng-bootstrap/ng-bootstrap";
import {DealStatus} from "../domain/deal-status";

@Component({
  selector: 'app-deals-with-me',
  templateUrl: './deals-with-me.component.html',
  styleUrls: ['./deals-with-me.component.css']
})
export class DealsWithMeComponent implements OnInit {
  dealStatus = new DealStatus;
  deals: Deal[] = [];
  errorDeal = "";
  private cloneDeal = new Deal;
  private modalRef: NgbModalRef;

  constructor(private dealService: DealService, private modalService: NgbModal) {
  }

  ngOnInit() {
    this.getDealsWithMe()
  }

  getDealsWithMe() {
    this.dealService.getAllDealsWithMe().then(deals => this.deals = deals).catch()
  }

  showStopRental(deal: Deal, content) {
    this.errorDeal = "";
    this.cloneDeal = deal;
    this.modalRef = this.modalService.open(content);
  }

  stopRental() {
    this.dealService.stopRental(this.cloneDeal.id)
      .then(deal => {
        this.deals[this.deals.indexOf(this.cloneDeal)] = deal;
        this.modalRef.close();
      })
      .catch(err => this.errorDeal = err)
  }

  showStartRental(deal: Deal, content) {
    this.errorDeal = "";
    this.cloneDeal = deal;
    this.modalRef = this.modalService.open(content);
  }

  startRental() {
    this.dealService.startRental(this.cloneDeal.id)
      .then(deal => {
        this.deals[this.deals.indexOf(this.cloneDeal)] = deal;
        this.modalRef.close();
      })
      .catch(err => this.errorDeal = err)
  }
}
