import { Injectable } from '@angular/core';
import { Observable } from "rxjs/Observable";
import {Headers, RequestOptions, Http} from "@angular/http";
import { Card } from "app/shared/Card"
import 'rxjs/Rx';

@Injectable()
export class CardService {

  constructor(private _http: Http) { }

  public addOrUpdateCard(card: Card): Observable<any> {
    let headers: Headers = new Headers({'Content-Type': 'application/json'});
    let options = new RequestOptions({headers: headers, withCredentials: true});

    return this._http.post("http://localhost:8080/cards", card, options)
      .map((result) => result.json())
      .catch((error: any) => {
        if (error.status === 400) {
          return Observable.throw(error.json().error || 'Server error');
        }
   });
  }

  public search(cardNumber: String): Observable<any> {
    let headers: Headers = new Headers({'Content-Type': 'application/json'});
    let options = new RequestOptions({headers: headers, withCredentials: true});

    return this._http.get("http://localhost:8080/cards?cardNumber=" + cardNumber, options)
      .map((result) => result.json())
      .catch((error: any) => {
        if (error.status === 400) {
          return Observable.throw(error.json().error || 'Server error');
        }
   });
  }
}
