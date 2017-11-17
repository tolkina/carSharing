import {Component, OnInit} from "@angular/core";
import {Subscription} from "rxjs/Subscription";
import {ProfileAdService} from "../../../../service/profile-ad.service";
import {Ad} from "../../../../domain/ad";
import {ActivatedRoute, Router} from "@angular/router";
import {clone} from "lodash";
import {Car} from "../../../../domain/car";
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
  car = new Car;
  errorUpdate = "";
  errorDelete = "";
  errorStatus = "";
  flagCar = false;
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
      .catch(err => this.errorUpdate = err);
  }

  deleteAd() {
    this.adService.deleteAd(this.adId).then(res => {
      this.modalRef.close();
      this.router.navigateByUrl('profile/ad')
    })
      .catch(err => this.errorDelete = err);
  }

  showUpdate(content) {
    this.errorUpdate = "";
    this.modalRef = this.modalService.open(content);
    this.editedAd = clone(this.ad);
  }

  showDelete(content) {
    this.errorDelete = "";
    this.modalRef = this.modalService.open(content);
  }

  showCar() {
    this.flagCar = this.flagCar == false;
  }

  showSetActual(content) {
    this.errorStatus = "";
    this.modalRef = this.modalService.open(content)
  }

  showSetNotActual(content) {
    this.errorStatus = "";
    this.modalRef = this.modalService.open(content)
  }

  setActual() {
    this.adService.setActual(this.adId).then(ad => {
      this.ad = ad;
      this.modalRef.close()
    }).catch(err => this.errorStatus = err)
  }

  setNotActual() {
    this.adService.setNotActual(this.adId).then(ad => {
      this.ad = ad;
      this.modalRef.close()
    }).catch(err => this.errorStatus = err)
  }

}
