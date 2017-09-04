import { Component, OnInit, ViewChild, ViewContainerRef } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { ToastsManager } from "ng2-toastr/ng2-toastr";
import { CardService } from "app/shared/card-service";
import { Card } from "app/shared/Card";
import { LoginServiceService } from "app/shared/login-service.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  @ViewChild('addCard') public addCardModal;

  private _addCardForm: FormGroup;
  private _error: boolean;

  constructor(private _router: Router,
              private _formBuilder: FormBuilder,
              private toastr: ToastsManager,
              private vcr: ViewContainerRef,
              private _cardService: CardService,
              private _authService: LoginServiceService) {
    this.toastr.setRootViewContainerRef(vcr);
  }

  ngOnInit() {
    this._addCardForm = this._formBuilder.group({
          cardNumber: ["", Validators.compose([Validators.required])],
          name: ["", Validators.required],
          expiryYear: ["", Validators.required],
          expiryMonth: ["", Validators.required]
        });
  }

  addcard() {
    this.addCardModal.show();
    this._addCardForm.reset();
  }

  searchcard() {
    this._router.navigate(['/search']);
  }

  logout() {
    this._authService.logout();
  }

  submitAddCard() {
    if (this._addCardForm.valid) {
      let card = new Card();
      card.cardNumber = this._addCardForm.value.cardNumber
      card.name = this._addCardForm.value.name;
      card.expiryDate = this._addCardForm.value.expiryYear + '/' + this._addCardForm.value.expiryMonth;

      this._cardService
        .addOrUpdateCard(card)
        .subscribe((res) => { this.toastr.success("Card added succesfully", "Add or Update Card");
                              this.addCardModal.hide(); },          
                   (err) => {this.toastr.error("Unable to add or update card", "Add or Update Card")});
    }
  }
}
