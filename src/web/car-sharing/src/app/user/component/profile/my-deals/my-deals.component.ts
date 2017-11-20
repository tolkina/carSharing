import {Component, OnInit} from '@angular/core';
import {Deal} from "../../../domain/deal";
import {DealService} from "../../../service/deal.service";
import {DealStatus} from "../../../domain/deal-status";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {SortDealsService} from "../../../service/sort-deals.service";

@Component({
  selector: 'app-my-deals',
  templateUrl: './my-deals.component.html',
  styleUrls: ['./my-deals.component.css']
})
export class MyDealsComponent implements OnInit {
  dealStatus = new DealStatus;
  deals: Deal[] = [];
  cloneDeal: Deal;
  errorDeal = "";
  modalRef: any;
  sortedByStatus = false;

  constructor(private dealService: DealService, private modalService: NgbModal, private sortDealsService: SortDealsService) {
  }

  ngOnInit() {
    this.getMyDeals()
  }

  getMyDeals() {
    this.dealService.getAllMyDeals().then(deals => this.deals = deals).catch()
  }

  showCancelBooking(deal: Deal, contentCancelBooking) {
    this.errorDeal = "";
    this.cloneDeal = deal;
    this.modalRef = this.modalService.open(contentCancelBooking);
  }

  cancelBooking() {
    this.dealService.cancelBooking(this.cloneDeal.id)
      .then(deal => {
        this.deals[this.deals.indexOf(this.cloneDeal)] = deal;
        this.modalRef.close();
      })
      .catch(err => this.errorDeal = err)
  }

  sortDeals() {
    this.sortedByStatus = this.sortedByStatus != true;
    this.deals = this.sortDealsService.sort(this.sortedByStatus, this.deals);
  }
}
