import {Component, OnInit} from '@angular/core';
import {CreditCardService} from "../../../service/credit-card.service";
import {CreditCard} from "../../../domain/credit-card";
import {NgbDateStruct, NgbModal} from "@ng-bootstrap/ng-bootstrap";
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
  errorMessage: String;
  errorDelete: String;
  modalRef: any;

  constructor(private creditCardService: CreditCardService, private dateFormatter: DateFormatter,
              private modalService: NgbModal) {
  }

  ngOnInit() {
    this.getCreditCards();
  }

  getCreditCards() {
    this.creditCardService.getCreditCards().then(cards => this.creditCards = cards).catch()
  }

  createCreditCard() {
    this.creditCardService.createCreditCard(this.newCreditCard).then(card => {
      this.creditCards.push(card);
      this.modalRef.close()
    })
      .catch(err => this.errorMessage = err)
  }

  deleteCreditCard() {
    this.creditCardService.deleteCreditCard(this.cloneCreditCard.id)
      .then(res => {
        this.creditCards.splice(this.creditCards.indexOf(this.cloneCreditCard), 1);
        this.modalRef.close()
      })
      .catch(err => this.errorDelete = err)
  }

  showDelete(creditCard: CreditCard, contentDelete) {
    this.errorDelete = "";
    this.modalRef = this.modalService.open(contentDelete);
    this.cloneCreditCard = creditCard;
  }

  showNew(contentCreate) {
    this.errorMessage = "";
    this.modalRef = this.modalService.open(contentCreate);
    this.newCreditCard = new CreditCard();
  }

  onChange(date: NgbDateStruct) {
    this.newCreditCard.validUntil = this.dateFormatter.toDate(date);
  }
}
