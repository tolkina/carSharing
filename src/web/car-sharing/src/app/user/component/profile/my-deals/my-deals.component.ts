import {Component, OnInit} from '@angular/core';
import {Deal} from "../../../domain/deal";
import {DealService} from "../../../service/deal.service";
import {DealStatus} from "../../../domain/deal-status";
import {NgbModal, NgbModalRef} from "@ng-bootstrap/ng-bootstrap";
import {Sort} from "../../../domain/sort";
import {Direction} from "../../../domain/direction";
import {PageParameter} from "../../../domain/page-parameter";
import {PageDeal} from "../../../domain/page-deal";
import {Profile} from "../../../domain/profile";
import {ProfileService} from "../../../service/profile.service";

@Component({
  selector: 'app-my-deals',
  templateUrl: './my-deals.component.html',
  styleUrls: ['./my-deals.component.css']
})
export class MyDealsComponent implements OnInit {
  dealStatus = new DealStatus;
  deals = new PageDeal();
  cloneDeal = new Deal();
  error = "";
  sort = new Sort();
  direction = new Direction();
  pageParameter = new PageParameter(1, 4, this.sort.status, this.direction.asc);
  private modalRef: NgbModalRef;

  constructor(private dealService: DealService, private modalService: NgbModal, private profileService: ProfileService) {
  }

  ngOnInit() {
    this.getMyDeals()
  }

  getMyDeals() {
    this.dealService.getAllMyDeals(this.pageParameter).then(deals => this.deals = deals).catch()
  }

  showCancelBooking(deal: Deal, contentCancelBooking) {
    this.error = "";
    this.cloneDeal = deal;
    this.modalRef = this.modalService.open(contentCancelBooking);
  }

  cancelBooking() {
    this.dealService.cancelBooking(this.cloneDeal.id)
      .then(deal => {
        this.deals.content[this.deals.content.indexOf(this.cloneDeal)] = deal;
        this.modalRef.close();
      })
      .catch(err => this.error = err)
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

  sortDeal(sortType: string, direction: string) {
    this.pageParameter.sort = sortType;
    this.pageParameter.direction = direction;
    this.getMyDeals();
  }

  isConfirmed(profile: Profile) {
    return this.profileService.isConfirmed(profile)
  }
}
