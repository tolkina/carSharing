import {Injectable} from '@angular/core';
import {Http} from "@angular/http";
import {Ad} from "../domain/ad";
import {PageParameter} from "../domain/page-parameter";
import {PageAd} from "../domain/page-ad";

@Injectable()
export class ProfileAdService {

  private adUrl = '/api/ad/';
  private adsOfPrincipalUrl = '/api/profile/ad';

  constructor(private http: Http) {
  }

  addAd(ad: Ad, carId: number) {
    return this.http
      .post(this.adUrl + carId, ad)
      .toPromise()
      .then(res => res.json() as Ad)
      .catch(this.handleError);
  }

  updateAd(ad: Ad, adId: number): Promise<Ad> {
    return this.http
      .put(this.adUrl + adId, ad)
      .toPromise()
      .then(res => res.json() as Ad)
      .catch(this.handleError)
  }

  getAd(adId: number): Promise<Ad> {
    return this.http
      .get(this.adUrl + adId)
      .toPromise()
      .then(res => res.json() as Ad)
      .catch(this.handleError)
  }

  getAllAdsForPrincipal(pageParameter: PageParameter) {
    return this.http
      .get(this.adsOfPrincipalUrl, {
        params: {
          page: pageParameter.page,
          size: pageParameter.size,
          sort: pageParameter.sort,
          direction: pageParameter.direction
        }
      })
      .toPromise()
      .then(res => res.json() as PageAd)
      .catch(this.handleError)
  }

  getAllNotMyActualAds(pageParameter: PageParameter) {
    return this.http
      .get(this.adUrl + "all-not-my", {
        params: {
          page: pageParameter.page,
          size: pageParameter.size,
          sort: pageParameter.sort,
          direction: pageParameter.direction
        }
      })
      .toPromise()
      .then(res => res.json() as PageAd)
      .catch(this.handleError)
  }

  deleteAd(adId: number) {
    return this.http
      .delete(this.adUrl + adId)
      .toPromise()
      .then()
      .catch(this.handleError)
  }

  setActual(adId: number) {
    return this.http
      .put(this.adUrl + adId + "/actual", {})
      .toPromise()
      .then(ad => ad.json() as Ad)
      .catch(this.handleError)
  }

  setNotActual(adId: number) {
    return this.http
      .put(this.adUrl + adId + "/not-actual", {})
      .toPromise()
      .then(ad => ad.json() as Ad)
      .catch(this.handleError)
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occured', error);
    return Promise.reject(error.json().message || error)
  }
}
