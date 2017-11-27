import 'rxjs/add/operator/toPromise';
import {Http} from "@angular/http";
import {Injectable} from '@angular/core';
import {Deal} from "../domain/deal";
import {PageParameter} from "../domain/page-parameter";
import {PageDeal} from "../domain/page-deal";

@Injectable()
export class DealService {

  private dealUrl = '/api/deal/';

  constructor(private http: Http) {
  }

  createDeal(deal): Promise<Deal> {
    return this.http.post(this.dealUrl, deal)
      .toPromise()
      .then(response => response.json() as Deal)
      .catch(this.handleError);
  }

  getAllMyDeals(pageParameter: PageParameter): Promise<PageDeal> {
    return this.http.get(this.dealUrl + "my", {
      params: {
        page: pageParameter.page,
        size: pageParameter.size,
        sort: pageParameter.sort,
        direction: pageParameter.direction
      }
    })
      .toPromise()
      .then(response => response.json() as PageDeal)
      .catch(this.handleError);
  }

  getAllDealsWithMe(pageParameter: PageParameter): Promise<PageDeal> {
    return this.http.get(this.dealUrl + "by-me", {
      params: {
        page: pageParameter.page,
        size: pageParameter.size,
        sort: pageParameter.sort,
        direction: pageParameter.direction
      }
    })
      .toPromise()
      .then(response => response.json() as PageDeal)
      .catch(this.handleError);
  }

  getDeal(id: number): Promise<Deal> {
    return this.http.get(this.dealUrl + id)
      .toPromise()
      .then(response => response.json() as Deal)
      .catch(this.handleError);
  }

  startRental(id: number): Promise<Deal> {
    return this.http.put(this.dealUrl + id + "/start-rental", null)
      .toPromise()
      .then(response => response.json() as Deal)
      .catch(this.handleError);
  }

  stopRental(id: number): Promise<Deal> {
    return this.http.put(this.dealUrl + id + "/stop-rental", null)
      .toPromise()
      .then(response => response.json() as Deal)
      .catch(this.handleError);
  }

  cancelBooking(id: number): Promise<Deal> {
    return this.http.put(this.dealUrl + id + "/cancel-booking", null)
      .toPromise()
      .then(response => response.json() as Deal)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occured', error);
    return Promise.reject(error.json().message || error)
  }
}
