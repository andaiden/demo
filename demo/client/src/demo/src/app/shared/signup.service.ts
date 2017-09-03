import { Injectable } from '@angular/core';
import { Observable } from "rxjs/Observable";
import {Headers, RequestOptions, Http} from "@angular/http";
import { Router } from "@angular/router";
import { User } from "app/shared/User";

@Injectable()
export class SignupService {

  constructor(private _http: Http,
              private _router: Router) { }

  public signup(user: User): Observable<any> {
    let headers: Headers = new Headers({'Content-Type': 'application/json'});
    let options = new RequestOptions({headers: headers});

    return this._http.post("http://localhost:8080/users/signup", JSON.stringify(user), options)
      .map((result) => {
        localStorage.setItem("authenticated", "true");

        result.json();        
      });
  }
}
