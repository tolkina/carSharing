import { Injectable } from '@angular/core';
import {Http} from "@angular/http";
import {Ad} from "../domain/ad";

@Injectable()
export class ProfileAdService {

  private profileUrl = '/api/profile/';
  private adUrl = '/api/ad/';
  private carUrl = '/api/car';
  constructor(private http:Http) { }

  addAd(ad: Ad, ownerId: number, carId: number) {
    const url = `${this.adUrl}/${ownerId}/${carId}`;
    return this.http
      .post(url, ad)
      .toPromise()
      .then(res => res.json() as Ad)
      .catch(this.handleError);
  }

  updateAd(ad:Ad, id:number): Promise<Ad>{
    const url = `${this.adUrl}${id}`;
    return this.http
      .put(url, ad)
      .toPromise()
      .then(() => ad)
      .catch(this.handleError)
  }

  getAd(id:number): Promise<Ad> {
    const url = `${this.adUrl}ad-${id}`;
    return this.http
      .get(url)
      .toPromise()
      .then(response => {return response.json() as Ad})
      .catch(this.handleError)
  }

  getAllAdsForProfile(profileId:number) {
    const url = `${this.adUrl}/${profileId}`;
    return this.http
      .get(url)
      .toPromise()
      .then(res => res.json() as Ad[])
      .catch(this.handleError)
  }

  deleteAd(id:number) {
    const url = `${this.adUrl}/${id}`;
    return this.http
      .delete(url)
      .toPromise()
      .then()
      .catch(this.handleError)
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occured', error);
    return Promise.reject(error.json().message || error)
  }
}
