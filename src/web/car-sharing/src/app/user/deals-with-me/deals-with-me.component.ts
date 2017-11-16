import {Component, OnInit} from '@angular/core';
import {Deal} from "../domain/deal";
import {DealService} from "../service/deal.service";

@Component({
  selector: 'app-deals-with-me',
  templateUrl: './deals-with-me.component.html',
  styleUrls: ['./deals-with-me.component.css']
})
export class DealsWithMeComponent implements OnInit {
  deals: Deal[] = [];

  constructor(private dealService: DealService) {
  }

  ngOnInit() {
    this.getDealsWithMe()
  }

  getDealsWithMe() {
    this.dealService.getAllDealsWithMe().then(deals => this.deals = deals).catch()
  }
}
