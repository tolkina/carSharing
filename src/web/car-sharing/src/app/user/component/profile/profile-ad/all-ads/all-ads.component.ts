import {Component, OnInit} from '@angular/core';
import {Ad} from "../../../../domain/ad";
import {AdService} from "../../../../service/ad.service";
import {CarService} from "../../../../service/car.service";
import {AdStatus} from "../../../../domain/ad-status";
import {NgbModal, NgbModalRef} from "@ng-bootstrap/ng-bootstrap";
import {clone} from "lodash";
import {Sort} from "../../../../domain/sort";
import {Direction} from "../../../../domain/direction";
import {PageParameter} from "../../../../domain/page-parameter";
import {PageAd} from "../../../../domain/page-ad";

@Component({
  selector: 'app-all-ads',
  templateUrl: './all-ads.component.html',
  styleUrls: ['./all-ads.component.css']
})
export class AllAdsComponent implements OnInit {

  ads = new PageAd();
  numberOfCars: number;
  adStatus = new AdStatus();
  error = "";
  ad = new Ad();
  editedAd = new Ad();
  sort = new Sort();
  direction = new Direction();
  pageParameter = new PageParameter(1, 4, this.sort.status, this.direction.asc);
  private modalRef: NgbModalRef;

  constructor(private adService: AdService, private carService: CarService,
              private modalService: NgbModal) {
  }

  ngOnInit() {
    this.getAllAds();
    this.getCarsWithoutAdOfPrincipal()
  }

  getAllAds() {
    this.adService.getAllAdsForPrincipal(this.pageParameter).then()
      .then(ads => this.ads = ads)
      .catch();
  }

  getCarsWithoutAdOfPrincipal() {
    this.carService.getCarsWithoutAdOfPrincipal()
      .then(cars => this.numberOfCars = cars.length)
      .catch();
  }

  getStatus(adStatus: string): string {
    if (adStatus == this.adStatus.actual[0]) {
      return this.adStatus.actual[1]
    }
    if (adStatus == this.adStatus.taken[0]) {
      return this.adStatus.taken[1]
    }
    if (adStatus == this.adStatus.notRelevant[0]) {
      return this.adStatus.notRelevant[1]
    }
  }

  updateAd() {
    this.adService.updateAd(this.editedAd, this.ad.id)
      .then(ad => {
        this.ads.content[this.ads.content.indexOf(this.ad)] = ad;
        this.modalRef.close()
      })
      .catch(err => this.error = err);
  }

  deleteAd() {
    this.adService.deleteAd(this.ad.id).then(res => {
      this.ads.content.splice(this.ads.content.indexOf(this.ad), 1);
      this.modalRef.close();
    })
      .catch(err => this.error = err);
  }

  showUpdate(ad: Ad, content) {
    this.error = "";
    this.ad = ad;
    this.editedAd = clone(ad);
    this.modalRef = this.modalService.open(content);
  }

  showDelete(ad: Ad, content) {
    this.error = "";
    this.ad = ad;
    this.modalRef = this.modalService.open(content);
  }

  showSetActual(ad: Ad, content) {
    this.error = "";
    this.ad = ad;
    this.modalRef = this.modalService.open(content)
  }

  showSetNotActual(ad: Ad, content) {
    this.error = "";
    this.ad = ad;
    this.modalRef = this.modalService.open(content)
  }

  setActual() {
    this.adService.setActual(this.ad.id).then(ad => {
      this.ads.content[this.ads.content.indexOf(this.ad)] = ad;
      this.modalRef.close()
    }).catch(err => this.error = err)
  }

  setNotActual() {
    this.adService.setNotActual(this.ad.id).then(ad => {
      this.ads.content[this.ads.content.indexOf(this.ad)] = ad;
      this.modalRef.close()
    }).catch(err => this.error = err)
  }

  sortAd(sortType: string, direction: string) {
    this.pageParameter.sort = sortType;
    this.pageParameter.direction = direction;
    this.getAllAds();
  }
}
