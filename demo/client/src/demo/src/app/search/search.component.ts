import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { CardService } from "app/shared/card-service";
import { Card } from "app/shared/Card";
import { ToastsManager } from "ng2-toastr/ng2-toastr";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent implements OnInit {

  private _searchForm: FormGroup;
  private _cards: Card[];
  
  constructor(private _formBuilder: FormBuilder,
              private _cardService: CardService,
              private toastr: ToastsManager,
              private vcr: ViewContainerRef) {
    this.toastr.setRootViewContainerRef(vcr);
  }

  ngOnInit() {
    this._searchForm = this._formBuilder.group({
          cardNumber: ["", Validators.compose([Validators.required, Validators.pattern("^[0-9]+$")])]
        });
  }

  search() {
    if (this._searchForm.valid) {
      this._cardService
      .search(this._searchForm.value.cardNumber)
      .subscribe((res) => { this._cards = res;
                    if (this._cards.length == 0) {
                      this.toastr.warning("No cards found with given search criteria", "Warning");
                    }
                  },
                 (err) => {this.toastr.error("Unable to find cards matching criteria", "Error")})
    }
  }
}
