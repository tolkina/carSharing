import {Component, OnInit} from '@angular/core';
import {CreditCardService} from "../../../service/credit-card.service";
import {CreditCard} from "../../../domain/credit-card";
import {NgbDateStruct, NgbModal, NgbModalRef} from "@ng-bootstrap/ng-bootstrap";
import {DateFormatter} from "../../../../date-formatter";

@Component({
  selector: 'app-credit-card',
  templateUrl: './credit-card.component.html',
  styleUrls: ['./credit-card.component.css']
})
export class CreditCardComponent implements OnInit {

  creditCards: CreditCard[] = [];
  newCreditCard: CreditCard = new CreditCard();
  cloneCreditCard: CreditCard = new CreditCard();
  date: NgbDateStruct;
  error = "";
  ngbValidUntil: NgbDateStruct[] = [];
  touchedValidUntil = false;
  private modalRef: NgbModalRef;

  constructor(private creditCardService: CreditCardService, private dateFormatter: DateFormatter,
              private modalService: NgbModal) {
  }

  ngOnInit() {
    this.getCreditCards();
    this.putNgbBorders();
  }

  getCreditCards() {
    this.creditCardService.getCreditCards().then(cards => this.creditCards = cards).catch()
  }

  createCreditCard() {
    this.creditCardService.createCreditCard(this.newCreditCard).then(card => {
      this.creditCards.push(card);
      this.modalRef.close()
    })
      .catch(err => this.error = err)
  }

  deleteCreditCard() {
    this.creditCardService.deleteCreditCard(this.cloneCreditCard.id)
      .then(res => {
        this.creditCards.splice(this.creditCards.indexOf(this.cloneCreditCard), 1);
        this.modalRef.close()
      })
      .catch(err => this.error = err)
  }

  showDelete(creditCard: CreditCard, contentDelete) {
    this.error = "";
    this.modalRef = this.modalService.open(contentDelete);
    this.cloneCreditCard = creditCard;
  }

  showCreate(contentCreate) {
    this.error = "";
    this.modalRef = this.modalService.open(contentCreate);
    this.newCreditCard = new CreditCard();
    this.touchedValidUntil = false;
  }

  onChange(date: NgbDateStruct) {
    this.newCreditCard.validUntil = this.dateFormatter.toDate(date);
  }

  touchValidUntil() {
    this.touchedValidUntil = true
  }

  private putNgbBorders() {
    const currentDate = new Date();
    this.ngbValidUntil.push({
      day: currentDate.getUTCDate(),
      month: currentDate.getUTCMonth() + 1,
      year: currentDate.getUTCFullYear()
    });
  }
}
