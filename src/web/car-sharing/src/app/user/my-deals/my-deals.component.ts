import {Component, OnInit} from '@angular/core';
import {Deal} from "../domain/deal";
import {DealService} from "../service/deal.service";

@Component({
  selector: 'app-my-deals',
  templateUrl: './my-deals.component.html',
  styleUrls: ['./my-deals.component.css']
})
export class MyDealsComponent implements OnInit {

  deals: Deal[] = [];

  constructor(private dealService: DealService) {
  }

  ngOnInit() {
    this.getMyDeals()
  }

  getMyDeals() {
    this.dealService.getAllMyDeals().then(deals => this.deals = deals).catch()
  }
}
