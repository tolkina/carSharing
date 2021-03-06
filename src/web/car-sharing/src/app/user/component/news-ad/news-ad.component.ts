import {Component, OnInit} from '@angular/core';
import {AdService} from "../../service/ad.service";
import {Ad} from "../../domain/ad";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {CreditCard} from "../../domain/credit-card";
import {CreditCardService} from "../../service/credit-card.service";
import {DealService} from "../../service/deal.service";
import {Deal} from "../../domain/deal";
import {Router} from "@angular/router";
import {Sort} from "../../domain/sort";
import {Direction} from "../../domain/direction";
import {PageParameter} from "../../domain/page-parameter";
import {PageAd} from "../../domain/page-ad";
import {Profile} from "../../domain/profile";
import {ProfileService} from "../../service/profile.service";

@Component({
  selector: 'app-news-ad',
  templateUrl: './news-ad.component.html',
  styleUrls: ['./news-ad.component.css']
})
export class NewsAdComponent implements OnInit {
  ads = new PageAd();
  dealError = "";
  creditCards: CreditCard[] = [];
  newDeal: any = {};
  currentDeal: Deal = new Deal;
  sort = new Sort();
  direction = new Direction();
  pageParameter = new PageParameter(1, 3, this.sort.status, this.direction.asc);
  private modalRef: any;

  constructor(private adService: AdService, private modalService: NgbModal, private profileService: ProfileService,
              private creditCardService: CreditCardService, private dealService: DealService, private router: Router) {
  }

  ngOnInit() {
    this.getAllNotMyActualAds();
    this.getCreditCards();
  }

  getAllNotMyActualAds() {
    this.adService.getAllNotMyActualAds(this.pageParameter).then()
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

  isConfirmed(profile: Profile) {
    return this.profileService.isConfirmed(profile)
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
