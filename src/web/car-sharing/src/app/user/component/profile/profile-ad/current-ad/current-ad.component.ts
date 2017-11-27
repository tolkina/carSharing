import {Component, OnInit} from "@angular/core";
import {Subscription} from "rxjs/Subscription";
import {ProfileAdService} from "../../../../service/profile-ad.service";
import {Ad} from "../../../../domain/ad";
import {ActivatedRoute, Router} from "@angular/router";
import {clone} from "lodash";
import {NgbModal, NgbModalRef} from "@ng-bootstrap/ng-bootstrap";
import {AdStatus} from "../../../../domain/ad-status";

@Component({
  selector: 'app-current-ad',
  templateUrl: './current-ad.component.html',
  styleUrls: ['./current-ad.component.css']
})
export class CurrentAdComponent implements OnInit {
  adId: number;
  ad = new Ad;
  editedAd = new Ad;
  error = "";
  adStatus = new AdStatus();
  private subscription: Subscription;
  private modalRef: NgbModalRef;

  constructor(private adService: ProfileAdService, private router: Router, private activateRoute: ActivatedRoute,
              private modalService: NgbModal) {
    this.subscription = activateRoute.params.subscribe(params => this.adId = params['adId']);
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  ngOnInit() {
    this.getAd();
  }

  getAd() {
    this.adService.getAd(this.adId)
      .then(ad => this.ad = ad)
      .catch();
  }

  updateAd() {
    this.adService.updateAd(this.editedAd, this.adId)
      .then(ad => {
        this.ad = ad;
        this.modalRef.close()
      })
      .catch(err => this.error = err);
  }

  deleteAd() {
    this.adService.deleteAd(this.adId).then(res => {
      this.modalRef.close();
      this.router.navigateByUrl('profile/ad')
    })
      .catch(err => this.error = err);
  }

  showUpdate(content) {
    this.error = "";
    this.modalRef = this.modalService.open(content);
    this.editedAd = clone(this.ad);
  }

  showDelete(content) {
    this.error = "";
    this.modalRef = this.modalService.open(content);
  }

  showSetActual(content) {
    this.error = "";
    this.modalRef = this.modalService.open(content)
  }

  showSetNotActual(content) {
    this.error = "";
    this.modalRef = this.modalService.open(content)
  }

  setActual() {
    this.adService.setActual(this.adId).then(ad => {
      this.ad = ad;
      this.modalRef.close()
    }).catch(err => this.error = err)
  }

  setNotActual() {
    this.adService.setNotActual(this.adId).then(ad => {
      this.ad = ad;
      this.modalRef.close()
    }).catch(err => this.error = err)
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
}
