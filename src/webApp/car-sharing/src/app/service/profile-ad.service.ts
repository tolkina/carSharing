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


  private handleError(error: any): Promise<any> {
    console.error('An error occured', error);
    return Promise.reject(error.json().message || error)
  }
}
