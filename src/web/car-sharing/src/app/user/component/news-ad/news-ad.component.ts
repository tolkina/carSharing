import {Component, OnInit} from '@angular/core';
import {ProfileAdService} from "../../service/profile-ad.service";
import {Ad} from "../../domain/ad";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {CreditCard} from "../../domain/credit-card";
import {CreditCardService} from "../../service/credit-card.service";
import {DealService} from "../../service/deal.service";
import {Deal} from "../../domain/deal";
import {Router} from "@angular/router";

@Component({
  selector: 'app-news-ad',
  templateUrl: './news-ad.component.html',
  styleUrls: ['./news-ad.component.css']
})
export class NewsAdComponent implements OnInit {
  confirm = "YES";
  ads: Ad[] = [];
  dealError = "";
  creditCards: CreditCard[] = [];
  newDeal: any = {};
  currentDeal: Deal = new Deal;
  private modalRef: any;

  constructor(private adService: ProfileAdService, private modalService: NgbModal,
              private creditCardService: CreditCardService, private dealService: DealService, private router: Router) {
  }

  ngOnInit() {
    this.getAllNotMyActualAds();
    this.getCreditCards();
  }

  getAllNotMyActualAds() {
    this.adService.getAllNotMyActualAds().then()
      .then(ads => this.ads = ads)
      .catch();
  }

  showCreateDeal(ad: Ad, content) {
    this.dealError = "";
    this.modalRef = this.modalService.open(content);
    this.newDeal.adId = ad.id;
    this.newDeal.hoursOfRent = 1;
  }

  createDeal() {
    if (this.checkDays(this.newDeal.daysForRent)) {
      this.dealService.createDeal(this.newDeal)
        .then(res => {
          this.currentDeal = res;
          this.modalRef.close();
          this.router.navigateByUrl("/profile/deal/my")
        })
        .catch(err => this.dealError = err)
    }
  }

  getCreditCards() {
    this.creditCardService.getCreditCards().then(cards => {
      this.creditCards = cards;
      if (cards[0]) {
        this.newDeal.creditCardId = cards[0].id;
      }
    }).catch()
  }

  redirectToCreditCard() {
    this.modalRef.close();
    this.router.navigateByUrl('/profile/credit-card')
  }

  private checkDays(days: number) {
    this.dealError = "";
    if (days < 1 || days > 30) {
      this.dealError = "Количество суток должно быть в интервале от 1 до 30";
      return false;
    }
    return true;
  }
}
