import {Component, OnInit} from '@angular/core';
import {Deal} from "../../../domain/deal";
import {DealService} from "../../../service/deal.service";
import {DealStatus} from "../../../domain/deal-status";
import {NgbModal, NgbModalRef} from "@ng-bootstrap/ng-bootstrap";
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
  error = "";
  sortedByStatus = false;
  private modalRef: NgbModalRef;

  constructor(private dealService: DealService, private modalService: NgbModal,
              private sortDealsService: SortDealsService) {
  }

  ngOnInit() {
    this.getMyDeals()
  }

  getMyDeals() {
    this.dealService.getAllMyDeals().then(deals => this.deals = deals).catch()
  }

  showCancelBooking(deal: Deal, contentCancelBooking) {
    this.error = "";
    this.cloneDeal = deal;
    this.modalRef = this.modalService.open(contentCancelBooking);
  }

  cancelBooking() {
    this.dealService.cancelBooking(this.cloneDeal.id)
      .then(deal => {
        this.deals[this.deals.indexOf(this.cloneDeal)] = deal;
        this.modalRef.close();
      })
      .catch(err => this.error = err)
  }

  sortDeals() {
    this.sortedByStatus = this.sortedByStatus != true;
    this.deals = this.sortDealsService.sort(this.sortedByStatus, this.deals);
  }

  getStatus(dealStatus: string): string {
    if (dealStatus == this.dealStatus.booking[0]) {
      return this.dealStatus.booking[1]
    }
    if (dealStatus == this.dealStatus.cancelBooking[0]) {
      return this.dealStatus.cancelBooking[1]
    }
    if (dealStatus == this.dealStatus.overdueBooking[0]) {
      return this.dealStatus.overdueBooking[1]
    }
    if (dealStatus == this.dealStatus.rentalStart[0]) {
      return this.dealStatus.rentalStart[1]
    }
    if (dealStatus == this.dealStatus.rentalEnd[0]) {
      return this.dealStatus.rentalEnd[1]
    }
  }
}
