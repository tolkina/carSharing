import 'rxjs/add/operator/toPromise';
import {Injectable} from '@angular/core';
import {Http} from "@angular/http";
import {CreditCard} from "../domain/credit-card";

@Injectable()
export class CreditCardService {

  private creditCardUrl = '/api/credit-card/';

  constructor(private http: Http) {
  }

  getCreditCards() {
    return this.http.get(this.creditCardUrl)
      .toPromise()
      .then(res => res.json() as CreditCard)
      .catch(this.handleError);
  }

  getCreditCard(cardId: number) {
    return this.http.get(this.creditCardUrl + cardId)
      .toPromise()
      .then(res => res.json() as CreditCard)
      .catch(this.handleError);
  }

  deleteCreditCard(cardId: number) {
    return this.http.delete(this.creditCardUrl + cardId)
      .toPromise()
      .then()
      .catch(this.handleError);
  }

  createCreditCard(card: CreditCard) {
    return this.http.post(this.creditCardUrl, card)
      .toPromise()
      .then(res => res.json() as CreditCard)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occured', error);
    return Promise.reject(error.json().message || error)
  }
}
